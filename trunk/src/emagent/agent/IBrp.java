package emagent.agent;

import java.util.Collection;
import java.util.EventListener;
import emagent.auction.*;
import emagent.environment.IFine;

public interface IBrp extends IAgent, EventListener {
	public Collection<IAuction> notifyPostRound();
	public void notifyAuctionResult(IAuctionResult auctionResult);
	public void notifyAuctionAvailable(IAuction auction);
	public void notifyFine(IFine fine);
	
	public int currentMonetaryBalance();
	public int currentElectricalBalance();
}
