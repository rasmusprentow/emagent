package emagent.agent.brp;

import emagent.auction.AuctionList;

public interface IBrpBiddingStrategy {
	public void bidOnAuctions(AuctionList auctions, int monetaryBalance, int electricalBalance);
}
