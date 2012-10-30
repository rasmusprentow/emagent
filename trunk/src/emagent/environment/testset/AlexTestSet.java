package emagent.environment.testset;
import emagent.agent.*;
import emagent.agent.brp.IBrp;
import emagent.agent.prosumer.ConstantProsumer;
import emagent.agent.prosumer.IProsumer;
import emagent.agent.prosumer.NuclearProsumer;
import emagent.agent.prosumer.SinusProsumer;
import emagent.agent.prosumer.VariableProsumer;

import emagent.environment.IEnvironment;

public class AlexTestSet extends AbstractTestSet {

	@Override
	public void setup(IEnvironment env) 
	{
		market = new FirstPriceOpenCryAscendingMarket();
		tso = new Tso(market);
		env.setSleepTime(512);
		IBrp brpa = createBrp("Anne",env.getStandardConsumationElectricityPrice()*1000);
		IBrp brpb = createBrp("Bente",env.getStandardConsumationElectricityPrice()*1000);
		
		createProsumer("Powerplant 100", new ConstantProsumer(-100), brpa);
		createProsumer("City", new ConstantProsumer(100), brpb);
	}
}
