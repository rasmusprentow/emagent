package emagent.auction;

import emagent.agent.brp.IBrp;

public class AuctionFactory implements IAuctionFactory {

	private static AuctionFactory instance = null;
	@Override
	public IAuction create(AuctionType auctionType, long electricalAmount,
			long startingPrice, IBrp seller) {
		if(auctionType.getBidType() == BidType.OPEN_CRY){
			return new FirstPriceOpenCryAscendingAuction(electricalAmount, startingPrice, seller);
		} else {
			return new FirstPriceSealedBidOneShotAuction(electricalAmount, startingPrice, seller);
		}
	}


	public static IAuctionFactory getFactory()
	{
		if(instance == null)
		{
			instance = new AuctionFactory();
		}
		return instance;
	}
}
