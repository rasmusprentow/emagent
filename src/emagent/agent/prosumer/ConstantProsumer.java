package emagent.agent.prosumer;


public class ConstantProsumer extends AbstractProsumer implements IProsumer {

	protected long consumation;

	public ConstantProsumer(long consumation) {
		super();
		this.consumation = consumation;
	}
	
	@Override
	public long getTotalConsumption() {
		
		return consumation;
	}

	@Override
	public void notifyTick(long time) throws Exception {
		// TODO Auto-generated method stub
		update();
	}

	

}
