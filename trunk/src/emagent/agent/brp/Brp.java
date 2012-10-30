package emagent.agent.brp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import emagent.agent.*;
import emagent.agent.prosumer.IProsumer;
import emagent.auction.*;
import emagent.environment.IFine;


public class Brp extends AbstractAgent implements IBrp{
	
	protected long electricalBalance = 0;
	protected long monetaryBalance;
	protected Collection<IProsumer> prosumers;
	protected IAuctionFactory auctionFactory;
	protected HashMap<AuctionType,IBrpSellingStrategy> sellingStrategies;
	protected HashMap<AuctionType,IBrpBiddingStrategy> biddingStrategies;
	private String name;
	private long fineAmountThisRound;
	
	public Brp(String name, long l)
	{
		this.name = name;
		this.monetaryBalance = l;
		prosumers = new ArrayList<IProsumer>();
		sellingStrategies = new HashMap<AuctionType, IBrpSellingStrategy>();
		sellingStrategies.put(
				new AuctionType(BidPrice.FIRST, BidType.SEALED, BidOrder.ONE_SHOT), 
				new FirstPriceSealedBidOneShotSellingStrategy());
		sellingStrategies.put(
				new AuctionType(BidPrice.FIRST, BidType.OPEN_CRY, BidOrder.ASCENDING), 
				new FirstPriceSealedBidOneShotSellingStrategy());
		biddingStrategies = new HashMap<AuctionType, IBrpBiddingStrategy>();
		biddingStrategies.put(
				new AuctionType(BidPrice.FIRST, BidType.SEALED, BidOrder.ONE_SHOT), 
				new FirstPriceSealedBidOneShotBiddingStrategy());
		biddingStrategies.put(
				new AuctionType(BidPrice.FIRST,  BidType.OPEN_CRY, BidOrder.ASCENDING), 
				new FirstPriceSealedBidOneShotBiddingStrategy());
	}
	
	public boolean addProsumer(IProsumer e) {
		return prosumers.add(e);
	}

	public boolean addAllProsumers(Collection<? extends IProsumer> c) {
		return prosumers.addAll(c);
	}

	@Override
	public void notifyTick(long newTick) {
		this.electricalBalance = 0;
		this.fineAmountThisRound = 0;
		for(IProsumer prosumer : this.prosumers){
			this.monetaryBalance += prosumer.payElectricalBill();
		}
	
		//update();
	}

	@Override
	public Collection<IAuction> notifyPostRound(AuctionType auctionType) {
		IBrpSellingStrategy sellingStrategy = sellingStrategies.get(auctionType);
		if(sellingStrategy == null)
		{
			throw new Error();
		}
		return sellingStrategy.postAuctions( - getTotalConsumption(),auctionType,this);
	}

	@Override
	public void notifyAuctionsAvailable(AuctionList auctions) throws Exception {
		if(auctions.isEmpty())
		{
			return;
		}
		IBrpBiddingStrategy biddingStrategy = biddingStrategies.get(auctions.get(0).getAuctionType());
		if(biddingStrategy == null)
		{
			throw new Error();
		}
		biddingStrategy.bidOnAuctions(auctions, monetaryBalance, getCurrentElectricalBalance(), this);
		update();
	}

	@Override
	public void notifyAuctionResult(IAuctionResult auctionResult) {
		if(!(auctionResult instanceof NotSoldResult))
		{
			if(auctionResult.getBuyer() == this)
			{
				this.monetaryBalance -= auctionResult.getPrice();
				this.electricalBalance += auctionResult.getQuantity();
			}
			if(auctionResult.getSeller() == this)
			{
				this.monetaryBalance += auctionResult.getPrice();
				this.electricalBalance -= auctionResult.getQuantity();
			}
		}
		update();
	}

	@Override
	public void notifyFine(IFine fine) {
		this.monetaryBalance -= fine.amount();
		fineAmountThisRound += fine.amount();
		update();
	}

	@Override
	public long getCurrentMonetaryBalance() {
		return monetaryBalance;
	}

	@Override
	public long getCurrentElectricalBalance() {
		return electricalBalance - getTotalConsumption();
	}
	
	public long getTotalConsumption(){
		int total = 0;
		for (IProsumer p : prosumers)
		{
			total += p.getTotalConsumption();
		}
		return total;
	}

	
	public String toString()
	{
		return name;
	}

	@Override
	public long getFineAmountThisRound() {
		return this.fineAmountThisRound;
	}
	
	
}
