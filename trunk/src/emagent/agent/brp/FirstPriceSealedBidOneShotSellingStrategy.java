package emagent.agent.brp;

import emagent.auction.AuctionFactory;
import emagent.auction.AuctionList;
import emagent.auction.AuctionType;
import emagent.environment.Environment;

public class FirstPriceSealedBidOneShotSellingStrategy implements
		IBrpSellingStrategy {

	@Override
	public AuctionList postAuctions(long energyBalance, AuctionType auctionType, IBrp seller) {
		AuctionList res = new AuctionList();
		
		while(energyBalance > 0)
		{
			long curBid = Math.min(energyBalance/3+1,energyBalance);
			res.add(AuctionFactory.getFactory().create(
					auctionType, 
					curBid, 
					curBid*Environment.getEnvironment().getStandardProductionElectricityPrice()+1, 
					seller));
			energyBalance -= curBid;
		}
		return res;
	}

}
