package emagent.agent.brp;

import emagent.auction.AuctionFactory;
import emagent.auction.AuctionList;
import emagent.auction.AuctionType;
import emagent.environment.Environment;

public class FirstPriceSealedBidOneShotSellingStrategy implements
		IBrpSellingStrategy {

	@Override
	public AuctionList postAuctions(long energyBalance, AuctionType auctionType, IBrp seller) {
		AuctionList auctions = new AuctionList();
		
		while(energyBalance > 0)
		{
			long electricalQuantity = Math.min(energyBalance/3+1,energyBalance);
			auctions.add(AuctionFactory.getFactory().create(
					auctionType, 
					electricalQuantity, 
					electricalQuantity*Environment.getEnvironment().getStandardProductionElectricityPrice()+1, 
					seller));
			energyBalance -= electricalQuantity;
		}
		return auctions;
	}

}
