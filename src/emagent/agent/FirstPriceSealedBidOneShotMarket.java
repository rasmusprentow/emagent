package emagent.agent;

import java.util.ArrayList;
import java.util.Collection;

import emagent.auction.*;
import emagent.agent.brp.*;

public class FirstPriceSealedBidOneShotMarket extends Market {

	AuctionType auctionType;
	@Override
	protected Collection<IAuctionResult> bidRound(AuctionList auctions) throws Exception {
		Collection<IAuctionResult> results = new ArrayList<IAuctionResult>();
		
		for(IBrp brp : auctionListeners)
		{
			brp.notifyAuctionsAvailable(auctions);
		}
		for(IAuction auction : auctions)
		{
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
