package emagent.auction;

import emagent.agent.IBrp;

public class FirstPriceSealedBidOneShotAuction extends Auction {
	 
	
	public FirstPriceSealedBidOneShotAuction(int quantity, int startingPrice,
			IBrp seller) {
		super(quantity, startingPrice, seller);
		
	}

	@Override
	public IAuctionResult getResult() {
	
		if(result == null){
			IBid bid = bids.peek();
			result = new AuctionResult(this,bid.getPrice(), bid.getBuyer());
		}
		return result;
	}

}
