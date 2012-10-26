package emagent.agent;

import emagent.environment.Environment;
import emagent.environment.PowerFine;
import emagent.agent.brp.*;


public class Tso extends AbstractAgent implements ITso {
	private IMarket market;
	
	public Tso(IMarket market)
	{
		this.market = market;
	}
	
	@Override
	public void notifyTick(int time) throws Exception {
		
	}

	@Override
	public void checkBrps() {
		for(IBrp brp : market.getAuctionListeners())
		{
			int imbalance = brp.getTotalConsumption();
			if(imbalance > 0)
			{
				brp.notifyFine(new PowerFine(Environment.getEnvironment().getStandardFineElectricityPrice(), imbalance));
			}
		}
	}
}
