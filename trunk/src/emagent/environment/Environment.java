package emagent.environment;


import emagent.agent.*;
import emagent.ui.TickListener;

public class Environment implements IEnvironment {

	static private IEnvironment instance = null;
	protected int time;
	protected TickListener tickListener;
	private IMarket market;
	private boolean turnOver = false;
	
	public static IEnvironment getEnvironment()
	{
		if(instance == null)
		{
			instance = new Environment();
		}
		return instance;
	}
	
	private Environment()
	{
	}
	
	
	
	@Override
	public void start(TickListener tickListener) throws Exception {
		this.tickListener = tickListener;
		market = new FirstPriceSealedBidOneShotMarket();
		
		IBrp brp1 = new Brp(10);
		brp1.addProsumer(new ConstantProsumer(10));
		
		IBrp brp2 = new Brp(10);
		brp2.addProsumer(new ConstantProsumer(-10));
		
		market.subscribeToAuctions(brp1);
		market.subscribeToAuctions(brp2);
		while(true)
		{

			time++;
			tickListener.notifyTick(time);
			brp1.notifyTurnOver(time);
			brp2.notifyTurnOver(time);
			market.notifyTurnOver(getTime());
		}
	}

	@Override
	public int getTime() {
		return time;
	}

	@Override
	public void turnOver() throws Exception {
		

	}

}
