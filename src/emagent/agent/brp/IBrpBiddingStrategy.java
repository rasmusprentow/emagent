package emagent.agent.brp;

import emagent.auction.AuctionList;

public interface IBrpBiddingStrategy {
	
	public int bidOnAuctions(AuctionList auctions, int monetaryBalance,
			int electricalBalance, IBrp bidder) throws Exception;
}
