package emagent.agent;

import java.util.ArrayList;
import java.util.Collection;

import emagent.auction.*;
import emagent.agent.brp.*;

public class FirstPriceOpenCryAscendingMarket extends Market implements IAgent {
	private AuctionType auctionType;
	@Override
	protected AuctionType getAuctionType() {
		if(auctionType == null)
		{
			auctionType = new AuctionType(BidPrice.FIRST, BidType.OPEN_CRY,BidOrder.ASCENDING);
		}
		return auctionType;
	}

	@Override
	protected Collection<IAuctionResult> bidRound(AuctionList auctions)
			throws Exception {
		Collection<IAuctionResult> results = new ArrayList<IAuctionResult>();
		boolean newBids = true;
		while(newBids)
		{
				for(IBrp brp : auctionListeners)
				{
					brp.notifyAuctionsAvailable(auctions);
				}
				
				newBids = auctions.bidsAddedThisRound();
				auctions.newBiddingRound();
		}
		for(IAuction auction : auctions)
		{
			
			auction.close();
			results.add(auction.getResult());
		}
		return results;
	}



}
