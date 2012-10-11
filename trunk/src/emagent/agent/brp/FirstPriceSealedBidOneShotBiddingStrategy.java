package emagent.agent.brp;

import emagent.auction.AuctionList;
import emagent.auction.Bid;
import emagent.auction.IAuction;

public class FirstPriceSealedBidOneShotBiddingStrategy implements
		IBrpBiddingStrategy {

	@Override
	public int  bidOnAuctions(AuctionList auctions, int monetaryBalance,
			int electricalBalance, IBrp bidder) throws Exception {
		int biddedThisRound = 0;
		if(electricalBalance < 0)
		{	
			auctions.sortByQuantity(false);
			IAuction lastAuction = auctions.get(0);
			for(IAuction auction : auctions)
			{
				if(electricalBalance >= 0)
				{
					break;
				}
				int bidPrice =  (int) (Math.random()* 4) * auction.getQuantity() + auction.getMinimumBidPrice();
				
				if(bidPrice <= monetaryBalance - biddedThisRound)
				{
					biddedThisRound += bidPrice;
					auction.addBid(new Bid( bidPrice,bidder ) );
				}
				lastAuction = auction;
			}
		}
		return biddedThisRound;
	}

}
