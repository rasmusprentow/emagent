package emagent.environment;

import java.util.ArrayList;
import java.util.Collection;

import emagent.agent.brp.*;
import emagent.agent.prosumer.IProsumer;
import emagent.agent.IMarket;
import emagent.agent.ITso;
import emagent.ui.TickListener;

public interface IEnvironment {
	
	public long getTime();
	public void turnOver() throws Exception;
	void start(TickListener tickListener) throws Exception;
	public abstract IMarket getMarket();
	public abstract ArrayList<IProsumer> getProsumers();
	public abstract ArrayList<IBrp> getBrps();
	public abstract Collection<TickListener> getTickNotifiers();

	public long getStandardConsumationElectricityPrice();
	long getStandardProductionElectricityPrice();
	public long getStandardFineElectricityPrice();
	public long getPriceDifference();
	public ITso getTso();
	public abstract void setSleepTime(long sleepTime);
	public abstract long getSleepTime();
}
