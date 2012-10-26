package emagent.environment;

public class ExponentialFine implements IFine {
	protected int pricePerImbalance;
	protected int imbalance;
	
	public ExponentialFine(int amountPerImbalance, int imbalance)
	{
		this.pricePerImbalance = amountPerImbalance;
		this.imbalance = imbalance;
	}
	
	public int amount()
	{
		return pricePerImbalance^imbalance;
	}

}
