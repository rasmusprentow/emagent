package emagent.agent;

import java.util.ArrayList;
import java.util.Collection;

import emagent.auction.AuctionFactory;
import emagent.auction.AuctionType;
import emagent.auction.Bid;
import emagent.auction.IAuction;
import emagent.auction.IAuctionFactory;
import emagent.auction.IAuctionResult;
import emagent.environment.IFine;


public class Brp extends AbstractAgent implements IBrp{
	
	protected int electricalBalance = 0;
	protected int monetaryBalance;
	protected Collection<IProsumer> prosumers;
	protected IAuctionFactory auctionFactory;
	
	
	public Brp( int monetaryBalance)
	{
		this.monetaryBalance = monetaryBalance;
		prosumers = new ArrayList<IProsumer>();
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
		//update();
	}

	@Override
	public Collection<IAuction> notifyPostRound(AuctionType auctionType) {
		Collection<IAuction> auctions = new ArrayList<IAuction>();
		if(getTotalConsumption() < 0)
		{
			IAuction auction = AuctionFactory.getFactory().create(auctionType, - getTotalConsumption(), 1, this);
			auctions.add(auction);
		}
		
		return auctions;
	}

	@Override
	public void notifyAuctionResult(IAuctionResult auctionResult) {
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
		update();
	}

	@Override
	public void notifyAuctionAvailable(IAuction auction) throws Exception {
		if(getTotalConsumption() > 0)
		{
			
			auction.add(new Bid( (int) (Math.random()* 4 +1),this) );
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
