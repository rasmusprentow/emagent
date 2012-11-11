package emagent.environment.testset;

import java.util.ArrayList;

import emagent.agent.IMarket;
import emagent.agent.ITso;
import emagent.agent.brp.IBrp;
import emagent.agent.prosumer.IProsumer;
import emagent.environment.IEnvironment;

public interface ITestSet {
	public void setup(IEnvironment env);
	public IMarket getMarket();
	public ITso getTso();
	public ArrayList<IBrp> getBrps();
	public ArrayList<IProsumer> getProsumers();
}
