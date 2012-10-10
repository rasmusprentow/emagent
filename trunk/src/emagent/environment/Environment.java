package emagent.environment;


import java.util.ArrayList;
import java.util.Collection;

import emagent.agent.*;
import emagent.ui.TickListener;

public class Environment implements IEnvironment {

	static private IEnvironment instance = null;
	protected int time;

	private IMarket market;
	private boolean turnOver = false;
	private ArrayList<TickListener> tickNotifiers;
	private ArrayList<IBrp> brps;
	private ArrayList<IProsumer> prosumers;

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
		tickNotifiers = new ArrayList<TickListener>();
		brps = new ArrayList<IBrp>();
		prosumers = new ArrayList<IProsumer>();
	}
	
	
	

	@Override
	public ArrayList<TickListener> getTickNotifiers() {
		return tickNotifiers;
	}


	@Override
	public ArrayList<IBrp> getBrps() {
		return brps;
	}

	

	@Override
	public ArrayList<IProsumer> getProsumers() {
		return prosumers;
	}


	@Override
	public IMarket getMarket() {
		return market;
	}

	@Override
	public void start(TickListener tickListener) throws Exception {
		tickNotifiers.add(tickListener);
		market = new FirstPriceSealedBidOneShotMarket();
		
		
		
		IBrp brp1 = new Brp(10);
		IBrp brp2 = new Brp(10);
		
		market.subscribeToAuctions(brp1);
		market.subscribeToAuctions(brp2);

		brps.add(brp1);
		brps.add(brp2);
		
		I2IFunction func1 = new I2IFunction()
		{
			@Override
			public int map(int arg) {
				return (int) (0.01 * arg);
				
			}
		};
		
		IProsumer pro3 = new VariableProsumer(func1);
		IProsumer pro1 = new ConstantProsumer(10);
		IProsumer pro2 = new ConstantProsumer(-10);
		
		prosumers.add(pro1);
		prosumers.add(pro2);
		prosumers.add(pro3);
		
		brp1.addProsumer(pro1);
		brp2.addProsumer(pro2);
		brp1.addProsumer(pro3);
		
		
		
		
			
	
	
		
		tickNotifiers.addAll(prosumers);
		tickNotifiers.addAll(brps);
		tickNotifiers.add(market);
		
		
		
		while(true)
		{

			time++;
			
			for(TickListener tl : tickNotifiers)
			{
				tl.notifyTick(getTime());
			}
			market.startRound();
			Thread.sleep(100);
		
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
