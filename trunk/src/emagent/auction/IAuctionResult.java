package emagent.auction;


import emagent.agent.brp.*;

public interface IAuctionResult {
	public IAuction getAuction();
	public int getPrice();
	public IBrp getBuyer();
	public AuctionStatus getStatus();

	public int getQuantity();

	public int getStartingPrice();

	public IBrp getSeller();
}
