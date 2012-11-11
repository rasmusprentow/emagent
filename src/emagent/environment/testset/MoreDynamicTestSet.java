package emagent.environment.testset;
import emagent.agent.FirstPriceOpenCryAscendingMarket;
import emagent.agent.I2IFunction;
import emagent.agent.Tso;
import emagent.agent.brp.IBrp;
import emagent.agent.prosumer.ConstantProsumer;
import emagent.agent.prosumer.IProsumer;
import emagent.agent.prosumer.SinusProsumer;
import emagent.agent.prosumer.VariableProsumer;
import emagent.environment.IEnvironment;

public class MoreDynamicTestSet extends AbstractTestSet {

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
	/*	IProsumer village3 = new SinusProsumer(40, 0, 20);
		IProsumer nuclear = new VariableProsumer(100);
		*/
		
		createProsumer("powerplant 100", powerplant100, brpa);
		createProsumer("house", house, brpa);
		createProsumer("solarCell", solarCell1, brpb);
		createProsumer("farm", farm, brpb);
		createProsumer("powerplant50", powerplant50, brpc);
		createProsumer("solarCell2", solarCell2, brpc);
		createProsumer("city", city, brpc);
		createProsumer("town", town, brpd);
		createProsumer("village", village, brpe);
		createProsumer("powerplant150", powerplant150, brpf);
		createProsumer("village2", village2, brpg);
		createProsumer("windmill", windmill, brpg);
		
		
	
	}

}
