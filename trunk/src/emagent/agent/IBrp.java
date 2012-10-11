package emagent.agent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EventListener;
import emagent.auction.*;
import emagent.environment.IFine;

public interface IBrp extends IAgent, EventListener {
	public Collection<IAuction> notifyPostRound(AuctionType auctionType);
	public void notifyAuctionResult(IAuctionResult auctionResult);
	public void notifyAuctionsAvailable(AuctionList auctions) throws Exception;
	public void notifyFine(IFine fine);
	
	public int getCurrentMonetaryBalance();
	public int getCurrentElectricalBalance();
	public boolean addProsumer(IProsumer e);

	public boolean addAllProsumers(Collection<? extends IProsumer> c);
	public int getTotalConsumption();

}
