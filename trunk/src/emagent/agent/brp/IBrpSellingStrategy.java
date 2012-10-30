package emagent.agent.brp;

import emagent.auction.AuctionList;
import emagent.auction.AuctionType;

public interface IBrpSellingStrategy {
	public AuctionList postAuctions(long l, AuctionType auctionType, IBrp seller);
}
