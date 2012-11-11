package emagent.agent;

import java.util.Collection;

import emagent.agent.brp.IBrp;
import emagent.auction.AuctionLog;



public interface IMarket extends IAgent {
	public void subscribeToAuctions(IBrp l);
	
	public  void unsubscribeToAuctions(IBrp l);


	public void notifyTick(long newTick) throws Exception;

	public abstract void startRound() throws Exception;

	Collection<IBrp> getAuctionListeners();

	public AuctionLog getAuctionHistory();

}
