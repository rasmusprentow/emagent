package emagent.auction;


import emagent.agent.brp.*;

public class AuctionResult implements IAuctionResult {
	protected IAuction auction;
	
	protected long price;
	protected IBrp buyer;
	
	public AuctionResult(IAuction auction, long l, IBrp buyer)
	{
		this.auction = auction;
		this.price = l;
		this.buyer = buyer;
	}
	
	public IAuction getAuction() {
		return auction;
	}
	public long getPrice() {
		return price;
	}
	public IBrp getBuyer() {
		return buyer;
	}
	
	public AuctionStatus getStatus() {
		return auction.getStatus();
	}

	public long getQuantity() {
		return auction.getQuantity();
	}

	public long getStartingPrice() {
		return auction.getStartingPrice();
	}

	public IBrp getSeller() {
		return auction.getSeller();
	}
}
