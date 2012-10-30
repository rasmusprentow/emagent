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
		env.setSleepTime(512);
		IBrp brpa = createBrp("Anne",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpb = createBrp("Bente",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpc = createBrp("Christinna",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpd = createBrp("Dorte",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpe = createBrp("Emma",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpf = createBrp("Freja",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpg = createBrp("Grete",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brph = createBrp("Heidi",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpi = createBrp("Ida",env.getStandardConsumationElectricityPrice()*15000);
		IBrp brpj = createBrp("Joan",env.getStandardConsumationElectricityPrice()*15000);
		
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
	
		createProsumer("Powerplant 100", new ConstantProsumer(-100), brpa);
		createProsumer("Kronborg", new SinusProsumer(10, 0, 5), brpa);
		createProsumer("Rasmus Solcenter", new SinusProsumer(-20, 0, 0), brpb);
		createProsumer("Højbjerghuse", new SinusProsumer(20, 0, 5), brpb);
		createProsumer("Powerplant 50", new ConstantProsumer(-50), brpc);
		createProsumer("Alex Solar Center", new SinusProsumer(-20, 0, 0), brpc);
		createProsumer("Skanderborg", new ConstantProsumer(200), brpc);
		createProsumer("Hjoering", new SinusProsumer(70, 0, 60), brpd);
		createProsumer("Randers", new SinusProsumer(60, 0, 20), brpe);
		createProsumer("Lunar Plant Randers", new SinusProsumer(-20, 0, 0,24*29), brpe);
		createProsumer("Powerplant 150", new ConstantProsumer(-150), brpf);
		createProsumer("Viborg", new SinusProsumer(50, 0, 25), brpg);
		createProsumer("Windmill", windmill, brpg);
		createProsumer("Nuclear",  new NuclearProsumer(300,24*30,4), brph);
		createProsumer("Aarhus", new ConstantProsumer(200), brph);
		createProsumer("Frederikshavn", new ConstantProsumer(100), brpi);
		createProsumer("Esbjerg Windmill Park", windmill2, brpj);
		createProsumer("Cern Fusion", new NuclearProsumer(300,24*30*12,24*2), brpj);
		createProsumer("Aalborg", new ConstantProsumer(200), brpj);
		createProsumer("Nørresundby", new ConstantProsumer(100), brpj);
	}
}
