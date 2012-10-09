package emagent.auction;

import emagent.agent.IBrp;

public interface IAuctionFactory {
	public IAuction create(AuctionType auctionType, int electricalAmount, int startingPrice, IBrp seller);
}
