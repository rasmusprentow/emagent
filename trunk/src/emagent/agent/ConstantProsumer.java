package emagent.agent;

public class ConstantProsumer extends AbstractProsumer implements IProsumer {

	protected int consumation;

	public ConstantProsumer(int consumation) {
		super();
		this.consumation = consumation;
	}
	
	@Override
	public int getTotalConsumption() {
		
		return consumation;
	}

	@Override
	public void notifyTick(int time) throws Exception {
		// TODO Auto-generated method stub
		update();
	}

}
