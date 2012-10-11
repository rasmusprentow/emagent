package emagent.agent.brp;

import emagent.auction.*;
import emagent.environment.Environment;

public class FirstPriceSealedBidOneShotSellingStrategy implements
		IBrpSellingStrategy {

	@Override
	public AuctionList postAuctions(int energyBalance, AuctionType auctionType, IBrp seller) {
		AuctionList res = new AuctionList();
		if(energyBalance > 0)
		{
			res.add(AuctionFactory.getFactory().create(
					auctionType, 
					energyBalance, 
					Environment.getEnvironment().getStandardProductionElectricityPrice()+1, 
					seller));
		}
		return res;
	}

}
