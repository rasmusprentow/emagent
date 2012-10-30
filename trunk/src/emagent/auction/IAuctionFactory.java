package emagent.auction;


import emagent.agent.brp.*;

public interface IAuctionFactory {
	public IAuction create(AuctionType auctionType, long curBid, long l, IBrp seller);
}
