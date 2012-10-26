package emagent.agent.brp;

import emagent.auction.*;
import emagent.environment.Environment;

public class FirstPriceSealedBidOneShotSellingStrategy implements
		IBrpSellingStrategy {

	@Override
	public AuctionList postAuctions(int energyBalance, AuctionType auctionType, IBrp seller) {
		AuctionList res = new AuctionList();
		
		while(energyBalance > 0)
		{
			int curBid = Math.min(energyBalance/2+1,energyBalance);
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
