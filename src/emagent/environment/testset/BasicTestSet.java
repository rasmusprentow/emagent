package emagent.environment.testset;

import emagent.agent.FirstPriceSealedBidOneShotMarket;
import emagent.agent.I2IFunction;
import emagent.agent.Tso;
import emagent.agent.brp.IBrp;
import emagent.agent.prosumer.ConstantProsumer;
import emagent.agent.prosumer.IProsumer;
import emagent.agent.prosumer.VariableProsumer;
import emagent.environment.IEnvironment;

public class BasicTestSet extends AbstractTestSet 
{

	@Override
	public void setup(IEnvironment env) {
		
		market = new FirstPriceSealedBidOneShotMarket();
		tso = new Tso(market);
		
		
		
		IBrp brp1 = createBrp("Brp 1", env.getStandardConsumationElectricityPrice()*15);
		IBrp brp2 = createBrp("Brp 2",env.getStandardConsumationElectricityPrice()*15);
		IBrp brp3 = createBrp("Brp 3",env.getStandardConsumationElectricityPrice()*15);
		IBrp brp4 = createBrp("Brp 4",env.getStandardConsumationElectricityPrice()*15);
		
		
		
		I2IFunction func1 = new I2IFunction()
		{
			@Override
			public long map(long arg) {
				return (long) (0.01 * arg);
				
			}
		};
		
		I2IFunction func2 = new I2IFunction()
		{
			@Override
			public long map(long arg) {
				return (long) (-0.01 * arg);
				
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
		
	}
}
