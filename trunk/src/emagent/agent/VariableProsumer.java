package emagent.agent;

public class VariableProsumer extends AbstractProsumer implements IProsumer {
	
	private int time;
	private I2IFunction function;
	
	public VariableProsumer(I2IFunction func) {
		function = func;
	}

	@Override
	public void notifyTick(int time) throws Exception {
		this.time = time;
	}

	@Override
	public int getTotalConsumption() {
		return function.map(time);
	}

	


}
