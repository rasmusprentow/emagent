package emagent.auction;



import emagent.agent.brp.*;

public class FirstPriceSealedBidOneShotAuction extends FirstPriceAbstractAuction {

	private AuctionType auctionType;

	public FirstPriceSealedBidOneShotAuction(long electricalAmount, long startingPrice,
			IBrp seller) {
		super(electricalAmount, startingPrice, seller);

	}

	@Override
	public long getMinimumBidPrice() {
	
		return  getStartingPrice();
	}
	
	@Override
	public  AuctionType getAuctionType() {
		if(auctionType == null)
		{
			auctionType = new AuctionType(BidPrice.FIRST, BidType.SEALED,BidOrder.ONE_SHOT);
		}
		return auctionType;
	}

	
	@Override
	public IBrp getLeadingBidder() {
		return null;
	
	}
}
