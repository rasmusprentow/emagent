package emagent.agent.brp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import emagent.agent.*;
import emagent.auction.AuctionFactory;
import emagent.auction.AuctionList;
import emagent.auction.AuctionType;
import emagent.auction.Bid;
import emagent.auction.BidOrder;
import emagent.auction.BidPrice;
import emagent.auction.BidType;
import emagent.auction.IAuction;
import emagent.auction.IAuctionFactory;
import emagent.auction.IAuctionResult;
import emagent.auction.NotSoldResult;
import emagent.environment.IFine;


public class Brp extends AbstractAgent implements IBrp{
	
	protected int electricalBalance = 0;
	protected int monetaryBalance;
	protected Collection<IProsumer> prosumers;
	protected IAuctionFactory auctionFactory;
	private int biddedThisRound = 0;
	protected HashMap<AuctionType,IBrpSellingStrategy> sellingStrategies;
	protected HashMap<AuctionType,IBrpBiddingStrategy> biddingStrategies;
	
	public Brp( int monetaryBalance)
	{
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
		
		IBrpBiddingStrategy biddingStrategy = biddingStrategies.get(auctions.get(0).);
		if(biddingStrategy == null)
		{
			throw new Error();
		}
		if(getTotalConsumption() > 0)
		{	
			auctions.sortByPrice();
			for(IAuction auction : auctions)
			{
			
				int bidPrice =  (int) (Math.random()* 1 + auction.getStartingPrice()) * auction.getQuantity();
				
				if(bidPrice <= this.monetaryBalance - biddedThisRound)
				{
					biddedThisRound += bidPrice;
					auction.addBid(new Bid( bidPrice,this) );
				}
			}
		}
		
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

	

	
	
}
