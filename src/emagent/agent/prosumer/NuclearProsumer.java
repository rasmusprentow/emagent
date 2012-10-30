package emagent.agent.prosumer;

public class NuclearProsumer extends AbstractProsumer implements IProsumer {

	private long nextChange = 0;
	private int multi = 1;
	private long upPeriod;
	private long downPeriod;
	private int production;
	
	public NuclearProsumer(int production) {
		this(production,24*30,24);
	}

	public NuclearProsumer(int production,  long upPeriod, long downPeriod) {
		super();
		this.upPeriod = upPeriod;
		this.downPeriod = downPeriod;
		this.production = production;
	}

	@Override
	public long getTotalConsumption() {
		return -production*multi;
	}

	@Override
	public void notifyTick(long time) throws Exception {
		if(time > nextChange)
		{
			multi = 1-multi;
			if(multi == 0)
			{
				nextChange += downPeriod/2 + Math.random()*downPeriod;
			}
			else
			{
				nextChange += upPeriod/2 + Math.random()*upPeriod;;
			}
		}
	}
}
