package emagent.environment.testset;

import java.util.ArrayList;

import emagent.agent.IMarket;
import emagent.agent.IProsumer;
import emagent.agent.ITso;
import emagent.agent.brp.Brp;
import emagent.agent.brp.IBrp;

public abstract class AbstractTestSet implements ITestSet {

	protected IMarket market;
	protected ITso tso;
	protected ArrayList<IBrp> brps;
	protected ArrayList<IProsumer> prosumers;


	public  AbstractTestSet() {
		// TODO Auto-generated method stub

		this.brps = new ArrayList<IBrp>();
		this.prosumers = new ArrayList<IProsumer>();
	}
	
	@Override
	public IMarket getMarket() {
		return market;
	}

	@Override
	public ITso getTso() {
		return tso;
	}

	@Override
	public ArrayList<IBrp> getBrps() {
		return brps;
	}

	@Override
	public ArrayList<IProsumer> getProsumers() {
		return prosumers;
	}

	
	protected IBrp createBrp(String name, int cash)
	{
		IBrp brp1 = new Brp(name, cash);
	
		market.subscribeToAuctions(brp1);
	
		
		brps.add(brp1);
		
		return brp1;
	}
}
