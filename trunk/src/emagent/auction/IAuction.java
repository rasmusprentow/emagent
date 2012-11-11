package emagent.auction;

import java.util.Stack;

import emagent.agent.brp.IBrp;


public interface IAuction {
	public boolean addBid(IBid e) throws Exception;
	public AuctionStatus getStatus();
	public void setStatus(AuctionStatus status);
	public long getQuantity();
	public long getStartingPrice();
	public long getMinimumBidPrice();
	public IBrp getSeller();
	public Stack<IBid> getBids();
	public void close();
	public IAuctionResult getResult();
	public void newBiddingRound();
	public boolean bidAdded();
	public int getMaximumBidPrice();
	public AuctionType getAuctionType();
	public IBrp getLeadingBidder();

}
