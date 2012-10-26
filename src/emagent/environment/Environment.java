package emagent.environment;


import java.util.ArrayList;

import emagent.agent.brp.*;
import emagent.agent.*;
import emagent.environment.testset.*;
import emagent.ui.TickListener;

public class Environment implements IEnvironment {

	static private IEnvironment instance = null;
	protected int time;
	private ITestSet testSet;
	protected ArrayList<TickListener> tickNotifiers;
	private int standardElectricityPrice = 1000;
	private long sleepTime = 500;

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
		testSet = new DynamicTestSet();
		tickNotifiers = new ArrayList<TickListener>();
	}
	
	
	private int getStandardElectricityPrice()
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

		tickNotifiers.add(tickListener);
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
			Thread.sleep(sleepTime );
		
		}
	}

	public ITso getTso() {
		return testSet.getTso();
	}

	@Override
	public int getTime() {
		return time;
	}

	@Override
	public void turnOver() throws Exception {
		

	}

	@Override
	public int getStandardConsumationElectricityPrice() {
		return (int) (getStandardElectricityPrice() * 1.10);
	}

	@Override
	public int getStandardProductionElectricityPrice() {
		return (int) (getStandardElectricityPrice() * 0.90);
	}

	@Override
	public int getStandardFineElectricityPrice() {
		return (int) (getStandardElectricityPrice() * 1.5);
	}

	@Override
	public int getPriceDifference() {
		return getStandardConsumationElectricityPrice()-getStandardProductionElectricityPrice();
	}

}
