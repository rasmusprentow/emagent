package emagent.auction;

import java.util.EmptyStackException;

import emagent.agent.brp.IBrp;


public abstract class FirstPriceAbstractAuction extends Auction {

	public FirstPriceAbstractAuction(int quantity, int startingPrice,
			IBrp seller) {
		super(quantity, startingPrice, seller);
		// TODO Auto-generated constructor stub
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
	public int getMaximumBidPrice() {
		return Integer.MAX_VALUE;
	}
	@Override
	abstract public AuctionType getAuctionType();

	@Override
	boolean verifyBid(IBid bid) {
		if(bid.getPrice() < getMinimumBidPrice())
		{
			return false;
		}
		return true;
	}


}
