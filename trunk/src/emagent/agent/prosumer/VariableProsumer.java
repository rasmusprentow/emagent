package emagent.agent.prosumer;

import emagent.agent.I2IFunction;

public class VariableProsumer extends AbstractProsumer implements IProsumer {
	
	private long time;
	private I2IFunction function;
	private long totalConsumption = 0;
	public VariableProsumer(I2IFunction func) {
		function = func;
	}

	@Override
	public void notifyTick(long time) throws Exception {
		this.time = time;
		totalConsumption = function.map(time);
		update();
	}

	@Override
	public long getTotalConsumption() {
		return totalConsumption;
	}

	public String getName() {
		return null;
	}

	


}
