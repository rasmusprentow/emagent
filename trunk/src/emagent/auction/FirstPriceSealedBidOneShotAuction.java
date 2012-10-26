package emagent.auction;



import emagent.agent.brp.*;

public class FirstPriceSealedBidOneShotAuction extends FirstPriceAbstractAuction {

	private AuctionType auctionType;

	public FirstPriceSealedBidOneShotAuction(int quantity, int startingPrice,
			IBrp seller) {
		super(quantity, startingPrice, seller);

	}

	@Override
	public int getMinimumBidPrice() {
	
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
