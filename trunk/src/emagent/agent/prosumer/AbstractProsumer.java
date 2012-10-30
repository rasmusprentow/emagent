package emagent.agent.prosumer;

import emagent.agent.AbstractAgent;
import emagent.environment.Environment;

abstract public class AbstractProsumer extends AbstractAgent implements IProsumer {
	

	private String name; 
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
