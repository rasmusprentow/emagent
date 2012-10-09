package emagent.auction;

import java.util.Stack;

import emagent.agent.IBrp;

public interface IAuction {
	public boolean add(IBid e);

	public AuctionStatus getStatus();

	public void setStatus(AuctionStatus status);

	public int getQuantity();

	public int getStartingPrice();

	public IBrp getSeller();

	public Stack<IBid> getBids();
}
