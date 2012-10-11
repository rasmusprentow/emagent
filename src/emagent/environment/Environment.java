package emagent.environment;


import java.util.ArrayList;
import java.util.Collection;

import emagent.agent.*;
import emagent.ui.TickListener;

public class Environment implements IEnvironment {

	static private IEnvironment instance = null;
	protected int time;

	private IMarket market;
	private ITso tso;
	private ArrayList<TickListener> tickNotifiers;
	private ArrayList<IBrp> brps;
	private ArrayList<IProsumer> prosumers;
	private int standardElectricityPrice = 1000;

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
	
	private IBrp createBrp(int cash)
	{
		IBrp brp1 = new Brp(cash);
	
		market.subscribeToAuctions(brp1);
	
		
		brps.add(brp1);
		
		return brp1;
	}
	
	@Override
	public void start(TickListener tickListener) throws Exception {
		tickNotifiers.add(tickListener);
		market = new FirstPriceSealedBidOneShotMarket();
		tso = new Tso(market);
		
		
		
		IBrp brp1 = createBrp(getStandardConsumationElectricityPrice()*15);
		IBrp brp2 = createBrp(getStandardConsumationElectricityPrice()*15);
		IBrp brp3 = createBrp(getStandardConsumationElectricityPrice()*15);
		IBrp brp4 = createBrp(getStandardConsumationElectricityPrice()*15);
		
		
		
		I2IFunction func1 = new I2IFunction()
		{
			@Override
			public int map(int arg) {
				return (int) (0.01 * arg);
				
			}
		};
		
		I2IFunction func2 = new I2IFunction()
		{
			@Override
			public int map(int arg) {
				return (int) (-0.01 * arg);
				
			}
		};
		
		
		
		IProsumer pro1 = new ConstantProsumer(10);
		IProsumer pro2 = new ConstantProsumer(-10);
		IProsumer pro3 = new VariableProsumer(func1);
		IProsumer pro4 = new VariableProsumer(func2);
		IProsumer pro5 = new ConstantProsumer(11);
		IProsumer pro6 = new ConstantProsumer(-10);
		prosumers.add(pro1);
		prosumers.add(pro2);
		prosumers.add(pro3);
		prosumers.add(pro4);
		prosumers.add(pro5);
		prosumers.add(pro6);
		brp1.addProsumer(pro1);
		brp2.addProsumer(pro2);
		brp1.addProsumer(pro3);
		brp3.addProsumer(pro4);
		brp4.addProsumer(pro5);
		brp4.addProsumer(pro6);
		
			
	
	
		
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
			tso.checkBrps();
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
		return (int) (getStandardElectricityPrice() * 2);
	}

}
