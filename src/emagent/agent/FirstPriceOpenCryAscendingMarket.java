package emagent.agent;

import java.util.ArrayList;
import java.util.Collection;

import emagent.auction.AuctionList;
import emagent.auction.AuctionLog;
import emagent.auction.AuctionType;
import emagent.auction.BidOrder;
import emagent.auction.BidPrice;
import emagent.auction.BidType;
import emagent.auction.IAuction;
import emagent.auction.IAuctionResult;
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
		for(IAuction auction : auctions)
		{
			for(IBrp brp : auctionListeners)
			{
				brp.notifyAuctionsAvailable(auctions);
			}
			auction.close();
			results.add(auction.getResult());
		}
		return results;
	}

	@Override
	public AuctionLog getAuctionHistory() {
		// TODO Auto-generated method stub
		return null;
	}

}
