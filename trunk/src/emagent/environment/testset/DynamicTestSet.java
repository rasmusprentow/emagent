package emagent.environment.testset;
import emagent.agent.*;
import emagent.agent.brp.IBrp;

import emagent.environment.IEnvironment;

public class DynamicTestSet extends AbstractTestSet {

	@Override
	public void setup(IEnvironment env) 
	{
		market = new FirstPriceOpenCryAscendingMarket();
		tso = new Tso(market);
		env.setSleepTime(5);
		IBrp brpa = createBrp("A",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpb = createBrp("B",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpc = createBrp("C",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpd = createBrp("D",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpe = createBrp("E",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpf = createBrp("F",env.getStandardConsumationElectricityPrice()*15000);
		
		
		
		IProsumer powerplant100 = new ConstantProsumer(-100);
		IProsumer house = new SinusProsumer(10, 0, 5);
		IProsumer solarCell1 = new SinusProsumer(-20, 0, 0);
		IProsumer farm = new SinusProsumer(20, 0, 5);
		IProsumer powerplant50 = new ConstantProsumer(-50);
		IProsumer solarCell2 = new SinusProsumer(-20, 0, 0);
		IProsumer city = new ConstantProsumer(200);
		IProsumer town = new SinusProsumer(70, 0, 60);
		IProsumer village = new SinusProsumer(40, 0, 20);
		IProsumer powerplant150 = new ConstantProsumer(-150);
		
		prosumers.add(powerplant100);
		prosumers.add(house);
		prosumers.add(solarCell1);
		prosumers.add(farm);
		prosumers.add(powerplant50);
		prosumers.add(solarCell2);
		prosumers.add(city);
		prosumers.add(town);
		prosumers.add(village);
		prosumers.add(powerplant150);
		
		
		brpa.addProsumer(powerplant100);
		brpa.addProsumer(house);
		
		brpb.addProsumer(solarCell1);
		brpb.addProsumer(farm);
		
		brpc.addProsumer(powerplant50);
		brpc.addProsumer(solarCell2);
		brpc.addProsumer(city);

		brpd.addProsumer(town);
		brpe.addProsumer(village);
		brpf.addProsumer(powerplant150);
	}

}
