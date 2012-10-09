package emagent.agent;

public class ConstantProsumer implements IProsumer {

	protected int consumation;

	public ConstantProsumer(int consumation) {
		super();
		this.consumation = consumation;
	}
	
	@Override
	public int getTotalConsumption() {
		
		return consumation;
	}

}
