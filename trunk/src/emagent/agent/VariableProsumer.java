package emagent.agent;

public class VariableProsumer extends AbstractProsumer implements IProsumer {
	
	private int time;
	private I2IFunction function;
	private int totalConsumption = 0;
	public VariableProsumer(I2IFunction func) {
		function = func;
	}

	@Override
	public void notifyTick(int time) throws Exception {
		this.time = time;
		totalConsumption = function.map(time);
		update();
	}

	@Override
	public int getTotalConsumption() {
		return totalConsumption;
	}

	


}
