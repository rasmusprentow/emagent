package emagent.agent.brp;

import emagent.auction.AuctionList;
import emagent.auction.Bid;
import emagent.auction.IAuction;
import emagent.environment.Environment;

public class FirstPriceSealedBidOneShotBiddingStrategy implements
		IBrpBiddingStrategy {

	@Override
	public void  bidOnAuctions(AuctionList auctions, int monetaryBalance,
			int electricalBalance, IBrp bidder) throws Exception {
		if(electricalBalance < 0 && monetaryBalance > 0)
		{	
			auctions.sortByQuantity(false);
			for(IAuction auction : auctions)
			{
				if(electricalBalance >= 0)
				{
					break;
				}
				int bidPrice = auction.getMinimumBidPrice();
				int randPrice =  (int) (Math.random()* Environment.getEnvironment().getPriceDifference()) * auction.getQuantity() + bidPrice;
				
				if(bidPrice <= monetaryBalance)
				{
					if(-electricalBalance >= auction.getQuantity())
					{
						int finalBidPrice = Math.min(monetaryBalance, randPrice);
						monetaryBalance -= finalBidPrice;
						electricalBalance += auction.getQuantity();
						auction.addBid(new Bid( finalBidPrice,bidder ) );
					}
				}
			}
		}
	}

}
