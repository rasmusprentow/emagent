package emagent.agent.brp;

import java.util.Collection;
import java.util.EventListener;

import emagent.agent.IAgent;
import emagent.agent.prosumer.IProsumer;
import emagent.auction.AuctionList;
import emagent.auction.AuctionType;
import emagent.auction.IAuction;
import emagent.auction.IAuctionResult;
import emagent.environment.IFine;

public interface IBrp extends IAgent, EventListener {
	public Collection<IAuction> notifyPostRound(AuctionType auctionType);
	public void notifyAuctionResult(IAuctionResult auctionResult);
	public void notifyAuctionsAvailable(AuctionList auctions) throws Exception;
	public void notifyFine(IFine fine);
	
	public long getCurrentMonetaryBalance();
	public long getCurrentElectricalBalance();
	public boolean addProsumer(IProsumer e);

	public boolean addAllProsumers(Collection<? extends IProsumer> c);
	public long getTotalConsumption();
	public long getFineAmountThisRound();

}
