package emagent.agent.brp;

import emagent.auction.AuctionList;
import emagent.auction.AuctionType;

public interface IBrpSellingStrategy {
	public AuctionList postAuctions(int energyBalance, AuctionType auctionType, IBrp seller);
}
