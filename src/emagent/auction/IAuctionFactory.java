package emagent.auction;


import emagent.agent.brp.IBrp;

public interface IAuctionFactory {
	public IAuction create(AuctionType auctionType, long curBid, long l, IBrp seller);
}
