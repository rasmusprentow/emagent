package emagent.agent.prosumer;

import emagent.agent.AbstractAgent;
import emagent.environment.Environment;

abstract public class AbstractProsumer extends AbstractAgent implements IProsumer {
	


	public long payElectricalBill()
	{
		if(this.getTotalConsumption() >= 0){
			return Environment.getEnvironment().getStandardConsumationElectricityPrice() * this.getTotalConsumption();
		}
		else {
			return Environment.getEnvironment().getStandardProductionElectricityPrice() * this.getTotalConsumption();
		}
		
	}

}
