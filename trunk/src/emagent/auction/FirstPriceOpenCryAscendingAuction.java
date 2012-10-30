package emagent.auction;

import emagent.agent.brp.IBrp;

public class FirstPriceOpenCryAscendingAuction extends FirstPriceAbstractAuction {

	private AuctionType auctionType;

	public FirstPriceOpenCryAscendingAuction(long electricalAmount, long startingPrice,
			IBrp seller) {
		super(electricalAmount, startingPrice, seller);
		
	}

	@Override
	public long getMinimumBidPrice() {
		long min = getStartingPrice();
		for(IBid bid : bids)
		{
			if(bid.getPrice() > min)
			{
				min = bid.getPrice();
			}
		}
		return min;
	}

	@Override
	public AuctionType getAuctionType() {
		
		if(auctionType == null)
		{
			auctionType = new AuctionType(BidPrice.FIRST, BidType.OPEN_CRY,BidOrder.ASCENDING);
		}
		return auctionType;
	}

	@Override
	public IBrp getLeadingBidder() {
		if(bids.empty())
		{
			return null;
		}
		return bids.peek().getBuyer();
	
	}	

}
