package emagent.environment;

public class PowerFine implements IFine {
	protected long pricePerImbalance;
	protected long imbalance;
	
	public PowerFine(long l, long imbalance2)
	{
		this.pricePerImbalance = l;
		this.imbalance = imbalance2;
	}
	
	public long amount()
	{
		return (long) (pricePerImbalance*Math.pow(imbalance, 1.1));
	}

}
