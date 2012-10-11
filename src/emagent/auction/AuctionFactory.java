package emagent.auction;

import emagent.agent.brp.*;

public class AuctionFactory implements IAuctionFactory {

	private static AuctionFactory instance = null;
	@Override
	public IAuction create(AuctionType auctionType, int electricalAmount,
			int startingPrice, IBrp seller) {
		return new FirstPriceSealedBidOneShotAuction(electricalAmount, startingPrice, seller);
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
