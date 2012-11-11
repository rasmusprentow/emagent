package emagent.auction;


import emagent.agent.brp.IBrp;

public class Bid implements IBid {
	protected long price;
	protected IBrp buyer;
	public Bid(long finalBidPrice, IBrp buyer)
	{
		this.price = finalBidPrice;
		this.buyer = buyer;
	}
	
	public long getPrice() {
		return price;
	}
	
	public IBrp getBuyer() {
		return buyer;
	} 
}
