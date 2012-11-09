package emagent.environment;


import java.util.ArrayList;

import emagent.agent.brp.*;
import emagent.agent.prosumer.IProsumer;
import emagent.agent.*;
import emagent.environment.testset.*;
import emagent.ui.TickListener;

public class Environment implements IEnvironment {

	static private IEnvironment instance = null;
	protected long time;
	private ITestSet testSet;
	protected ArrayList<TickListener> tickNotifiers;
	private long standardElectricityPrice = 1000;
	private long sleepTime = 512;

	@Override
	public long getSleepTime() {
		return sleepTime;
	}

	@Override
	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

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
		//testSet = new LessDynamicTestSet();
		testSet = new LessDynamicTestSet();
		tickNotifiers = new ArrayList<TickListener>();
	}
	
	
	private long getStandardElectricityPrice()
	{
		return standardElectricityPrice;
	}
	
	@Override
	public ArrayList<TickListener> getTickNotifiers() {
		return tickNotifiers;
	}


	@Override
	public ArrayList<IBrp> getBrps() {
		return testSet.getBrps();
	}

	

	@Override
	public ArrayList<IProsumer> getProsumers() {
		return testSet.getProsumers();
	}


	@Override
	public IMarket getMarket() {
		return testSet.getMarket();
	}
	
	@Override
	public void start(TickListener tickListener) throws Exception {
		testSet.setup(this);

		//tickNotifiers.add(tickListener);
		tickNotifiers.addAll(getProsumers());
		tickNotifiers.addAll(getBrps());
		tickNotifiers.add(getMarket());
		while(true)
		{

			time++;
			
			for(TickListener tl : getTickNotifiers())
			{
				tl.notifyTick(getTime());
			}
			
			getMarket().startRound();
			getTso().checkBrps();
			tickListener.notifyTick(getTime());
			Thread.sleep(sleepTime );
		
		}
	}

	public ITso getTso() {
		return testSet.getTso();
	}

	@Override
	public long getTime() {
		return time;
	}

	@Override
	public void turnOver() throws Exception {
		

	}

	@Override
	public long getStandardConsumationElectricityPrice() {
		return (long) (getStandardElectricityPrice() * 1.10);
	}

	@Override
	public long getStandardProductionElectricityPrice() {
		return (long) (getStandardElectricityPrice() * 0.90);
	}

	@Override
	public long getStandardFineElectricityPrice() {
		return (long) (getStandardElectricityPrice() * 1.5);
	}

	@Override
	public long getPriceDifference() {
		return getStandardConsumationElectricityPrice()-getStandardProductionElectricityPrice();
	}

}
