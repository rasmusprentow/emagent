package emagent.agent;

import java.util.ArrayList;
import java.util.Collection;

import emagent.auction.IAuction;
import emagent.auction.IAuctionResult;

public class FirstPriceSealedBidOneShotMarket extends Market {

	@Override
	protected Collection<IAuctionResult> bidRound(ArrayList<IAuction> auctions) {
		Collection<IAuctionResult> results = new ArrayList<IAuctionResult>();
		for(IAuction auction : auctions)
		{
			for(IBrp brp : auctionListeners)
			{
				brp.notifyAuctionAvailable(auction);
			}
			auction.close();
			results.add(auction.getResult());
		}
		return results;
	}

}
