package emagent.agent;

import java.util.ArrayList;
import java.util.Collection;

import emagent.agent.brp.IBrp;
import emagent.auction.AuctionList;
import emagent.auction.AuctionType;
import emagent.auction.BidOrder;
import emagent.auction.BidPrice;
import emagent.auction.BidType;
import emagent.auction.IAuction;
import emagent.auction.IAuctionResult;

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
