package emagent.auction;


import emagent.agent.brp.*;

public interface IAuctionResult {
	public IAuction getAuction();
	public long getPrice();
	public IBrp getBuyer();
	public AuctionStatus getStatus();

	public long getQuantity();

	public long getStartingPrice();

	public IBrp getSeller();
}
