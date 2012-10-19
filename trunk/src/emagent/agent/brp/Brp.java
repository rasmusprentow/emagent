package emagent.agent.brp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import emagent.agent.*;
import emagent.auction.*;
import emagent.environment.IFine;


public class Brp extends AbstractAgent implements IBrp{
	
	protected int electricalBalance = 0;
	protected int monetaryBalance;
	protected Collection<IProsumer> prosumers;
	protected IAuctionFactory auctionFactory;
	protected HashMap<AuctionType,IBrpSellingStrategy> sellingStrategies;
	protected HashMap<AuctionType,IBrpBiddingStrategy> biddingStrategies;
	private String name;
	
	public Brp(String name, int monetaryBalance)
	{
		this.name = name;
		this.monetaryBalance = monetaryBalance;
		prosumers = new ArrayList<IProsumer>();
		sellingStrategies = new HashMap<AuctionType, IBrpSellingStrategy>();
		sellingStrategies.put(
				new AuctionType(BidPrice.FIRST, BidType.SEALED, BidOrder.ONE_SHOT), 
				new FirstPriceSealedBidOneShotSellingStrategy());
		biddingStrategies = new HashMap<AuctionType, IBrpBiddingStrategy>();
		biddingStrategies.put(
				new AuctionType(BidPrice.FIRST, BidType.SEALED, BidOrder.ONE_SHOT), 
				new FirstPriceSealedBidOneShotBiddingStrategy());
	}
	
	public boolean addProsumer(IProsumer e) {
		return prosumers.add(e);
	}

	public boolean addAllProsumers(Collection<? extends IProsumer> c) {
		return prosumers.addAll(c);
	}



	@Override
	public void notifyTick(int newTick) {
		this.electricalBalance = 0;
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
		return sellingStrategy.postAuctions(-getTotalConsumption(),auctionType,this);
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
		
	}

	@Override
	public int getCurrentMonetaryBalance() {
		return monetaryBalance;
	}

	@Override
	public int getCurrentElectricalBalance() {
		return electricalBalance - getTotalConsumption();
	}
	
	public int getTotalConsumption(){
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
	
	
}
