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
		
		for(IAuction auction : auctions)
		{
			if(auction.getLeadingBidder() == bidder)
			{
				monetaryBalance -= auction.getMinimumBidPrice();
				electricalBalance += auction.getQuantity();
			}
		}
		if(electricalBalance < 0 && monetaryBalance > 0)
		{
			auctions.sortByQuantity(false);
			for(IAuction auction : auctions)
			{
				if(electricalBalance >= 0)
				{
					break;
				}
				if(auction.getLeadingBidder() == bidder)
				{
					continue;
				}
				int bidPrice = auction.getMinimumBidPrice();
				int randPrice =  (int) (Math.random()* Environment.getEnvironment().getPriceDifference()) * auction.getQuantity() + bidPrice;
				
				if(bidPrice/auction.getQuantity() <= privateValuation( - electricalBalance)/( - electricalBalance))
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

	private int privateValuation(int electricalBalance) {
		return Environment.getEnvironment().getTso().getFineSize(electricalBalance);
	}

}
