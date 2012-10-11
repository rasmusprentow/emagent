package emagent.auction;

import java.util.EmptyStackException;

import emagent.agent.IBrp;

public class FirstPriceSealedBidOneShotAuction extends Auction {

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

}
