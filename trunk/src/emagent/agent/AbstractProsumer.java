package emagent.agent;

import emagent.environment.Environment;

abstract public class AbstractProsumer extends AbstractAgent implements IProsumer {
	
	public int payElectricalBill()
	{
		if(this.getTotalConsumption() >= 0){
			return Environment.getEnvironment().getStandardConsumationElectricityPrice() * this.getTotalConsumption();
		}
		else {
			return Environment.getEnvironment().getStandardProductionElectricityPrice() * this.getTotalConsumption();
		}
		
	}

}
