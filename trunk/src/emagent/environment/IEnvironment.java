package emagent.environment;

import java.util.ArrayList;

import emagent.agent.brp.*;
import emagent.agent.IMarket;
import emagent.agent.IProsumer;
import emagent.ui.Writer;
import emagent.ui.TickListener;

public interface IEnvironment {
	
	public int getTime();
	public void turnOver() throws Exception;
	void start(TickListener tickListener) throws Exception;
	public abstract IMarket getMarket();
	public abstract ArrayList<IProsumer> getProsumers();
	public abstract ArrayList<IBrp> getBrps();
	public abstract ArrayList<TickListener> getTickNotifiers();

	public int getStandardConsumationElectricityPrice();
	int getStandardProductionElectricityPrice();
	public int getStandardFineElectricityPrice();
	public int getPriceDifference();
}
