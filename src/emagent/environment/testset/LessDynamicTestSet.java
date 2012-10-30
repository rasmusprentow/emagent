package emagent.environment.testset;
import emagent.agent.*;
import emagent.agent.brp.IBrp;
import emagent.agent.prosumer.ConstantProsumer;
import emagent.agent.prosumer.IProsumer;
import emagent.agent.prosumer.SinusProsumer;
import emagent.agent.prosumer.VariableProsumer;

import emagent.environment.IEnvironment;

public class LessDynamicTestSet extends AbstractTestSet {

	@Override
	public void setup(IEnvironment env) 
	{
		market = new FirstPriceOpenCryAscendingMarket();
		tso = new Tso(market);
		env.setSleepTime(500);
		IBrp brpa = createBrp("A",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpb = createBrp("B",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpc = createBrp("C",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpd = createBrp("D",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpe = createBrp("E",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpf = createBrp("F",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpg = createBrp("G",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brph = createBrp("H",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpi = createBrp("I",env.getStandardConsumationElectricityPrice()*15000);
		
		
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
		IProsumer village2 = new SinusProsumer(50, 0, 25);
		IProsumer windmill = new VariableProsumer(new I2IFunction(){
		
			@Override
			public long map(long arg) {
				return - (long) (Math.random()*50 + 25);
			}
			
			
		});
		
		IProsumer village3 = new ConstantProsumer(100);
		IProsumer city2 = new ConstantProsumer(200);
		IProsumer nuclear = new VariableProsumer(new I2IFunction(){

			@Override
			public long map(long arg) {
				if(arg < 5)
				{
					return 10000;
				} else 
				{
					return -300;
				}
			}
			
			
		});
	/*	IProsumer village3 = new SinusProsumer(40, 0, 20);
		IProsumer nuclear = new VariableProsumer(100);
		*/
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
		prosumers.add(village2);
		prosumers.add(windmill);
		prosumers.add(nuclear);
		prosumers.add(city2);
		prosumers.add(village3);
		
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
		
		brpg.addProsumer(village2);
		brpg.addProsumer(windmill);
		
		brph.addProsumer(nuclear);
		brph.addProsumer(city2);
		
		brpi.addProsumer(village3);
	}

}
