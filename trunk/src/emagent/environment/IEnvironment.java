package emagent.environment;

import java.util.ArrayList;
import java.util.Collection;

import emagent.agent.brp.*;
import emagent.agent.IMarket;
import emagent.agent.IProsumer;
import emagent.agent.ITso;
import emagent.ui.TickListener;

public interface IEnvironment {
	
	public int getTime();
	public void turnOver() throws Exception;
	void start(TickListener tickListener) throws Exception;
	public abstract IMarket getMarket();
	public abstract ArrayList<IProsumer> getProsumers();
	public abstract ArrayList<IBrp> getBrps();
	public abstract Collection<TickListener> getTickNotifiers();

	public int getStandardConsumationElectricityPrice();
	int getStandardProductionElectricityPrice();
	public int getStandardFineElectricityPrice();
	public int getPriceDifference();
	public ITso getTso();
	public abstract void setSleepTime(long sleepTime);
	public abstract long getSleepTime();
}
