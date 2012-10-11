package emagent.auction;


import emagent.agent.brp.*;

public class AuctionResult implements IAuctionResult {
	protected IAuction auction;
	
	protected int price;
	protected IBrp buyer;
	
	public AuctionResult(IAuction auction, int price, IBrp buyer)
	{
		this.auction = auction;
		this.price = price;
		this.buyer = buyer;
	}
	
	public IAuction getAuction() {
		return auction;
	}
	public int getPrice() {
		return price;
	}
	public IBrp getBuyer() {
		return buyer;
	}
	
	public AuctionStatus getStatus() {
		return auction.getStatus();
	}

	public int getQuantity() {
		return auction.getQuantity();
	}

	public int getStartingPrice() {
		return auction.getStartingPrice();
	}

	public IBrp getSeller() {
		return auction.getSeller();
	}
}
