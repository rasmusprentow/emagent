package emagent.agent.brp;

import emagent.auction.AuctionList;

public interface IBrpBiddingStrategy {
	
	public void bidOnAuctions(AuctionList auctions, long monetaryBalance,
			long l, IBrp bidder) throws Exception;
}
