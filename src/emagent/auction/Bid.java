package emagent.auction;


import emagent.agent.brp.*;

public class Bid implements IBid {
	protected int price;
	protected IBrp buyer;
	public Bid(int price, IBrp buyer)
	{
		this.price = price;
		this.buyer = buyer;
	}
	
	public int getPrice() {
		return price;
	}
	
	public IBrp getBuyer() {
		return buyer;
	} 
}
