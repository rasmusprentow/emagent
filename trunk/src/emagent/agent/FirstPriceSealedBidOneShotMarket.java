package emagent.agent;

import java.util.ArrayList;
import java.util.Collection;

import emagent.auction.AuctionType;
import emagent.auction.BidOrder;
import emagent.auction.BidPrice;
import emagent.auction.BidType;
import emagent.auction.IAuction;
import emagent.auction.IAuctionResult;

public class FirstPriceSealedBidOneShotMarket extends Market {

	AuctionType auctionType;
	@Override
	protected Collection<IAuctionResult> bidRound(ArrayList<IAuction> auctions) throws Exception {
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

	@Override
	protected AuctionType getAuctionType() {
		if(auctionType == null)
		{
			auctionType = new AuctionType(BidPrice.FIRST, BidType.SEALED,BidOrder.ONE_SHOT);
		}
		return auctionType;
	}

}
