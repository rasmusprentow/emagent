package emagent.auction;

import java.util.EmptyStackException;

import emagent.agent.brp.*;

public class FirstPriceSealedBidOneShotAuction extends Auction {

	private AuctionType auctionType;

	public FirstPriceSealedBidOneShotAuction(int quantity, int startingPrice,
			IBrp seller) {
		super(quantity, startingPrice, seller);

	}

	@Override
	public IAuctionResult getResult() {

		if (result == null) {
			try {
				IBid bid = bids.peek();

				result = new AuctionResult(this, bid.getPrice(), bid.getBuyer());
			} catch (EmptyStackException e) {
				result = new NotSoldResult(this);
			}
		}
		return result;

	}

	@Override
	boolean verifyBid(IBid bid) {
		if(bid.getPrice() < getStartingPrice())
		{
			return false;
		}
		return true;
	}

	@Override
	public int getMinimumBidPrice() {
		int min = getStartingPrice();
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
	public int getMaximumBidPrice() {
		return Integer.MAX_VALUE;
	}

	@Override
	public  AuctionType getAuctionType() {
		if(auctionType == null)
		{
			auctionType = new AuctionType(BidPrice.FIRST, BidType.SEALED,BidOrder.ONE_SHOT);
		}
		return auctionType;
	}

}
