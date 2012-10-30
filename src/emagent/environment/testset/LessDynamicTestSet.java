package emagent.environment.testset;
import emagent.agent.*;
import emagent.agent.brp.IBrp;
import emagent.agent.prosumer.ConstantProsumer;
import emagent.agent.prosumer.IProsumer;
import emagent.agent.prosumer.NuclearProsumer;
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
		IBrp brpj = createBrp("J",env.getStandardConsumationElectricityPrice()*15000);
		
		IProsumer windmill = new VariableProsumer(new I2IFunction(){
			@Override
			public long map(long arg) {
				return - (long) (Math.random()*50 + 25);
			}
		});
		
		IProsumer windmill2 = new VariableProsumer(new I2IFunction(){
			@Override
			public long map(long arg) {
				return - (long) (Math.random()*100);
			}
		});
	
		createProsumer("powerplant 100", new ConstantProsumer(-100), brpa);
		createProsumer("Kronborg", new SinusProsumer(10, 0, 5), brpa);
		createProsumer("solarCell", new SinusProsumer(-20, 0, 0), brpb);
		createProsumer("Højbjerghuse", new SinusProsumer(20, 0, 5), brpb);
		createProsumer("powerplant 50", new ConstantProsumer(-50), brpc);
		createProsumer("solarCell2", new SinusProsumer(-20, 0, 0), brpc);
		createProsumer("Skanderborg", new ConstantProsumer(200), brpc);
		createProsumer("Hjoering", new SinusProsumer(70, 0, 60), brpd);
		createProsumer("Randers", new SinusProsumer(40, 0, 20), brpe);
		createProsumer("powerplant150", new ConstantProsumer(-150), brpf);
		createProsumer("Viborg", new SinusProsumer(50, 0, 25), brpg);
		createProsumer("windmill", windmill, brpg);
		createProsumer("nuclear",  new NuclearProsumer(300,24*30,4), brph);
		createProsumer("Aarhus", new ConstantProsumer(200), brph);
		createProsumer("village3", new ConstantProsumer(100), brpi);
		createProsumer("windmill2", windmill2, brpj);
		createProsumer("nuclear2", new NuclearProsumer(300,24*30*12,24*2), brpj);
		createProsumer("Aalborg", new ConstantProsumer(200), brpj);
		createProsumer("Nørresundby", new ConstantProsumer(100), brpj);
	}
}
