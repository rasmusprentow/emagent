package emagent.environment;

public class LinearFine implements IFine {
	protected int pricePerImbalance;
	protected int imbalance;
	
	public LinearFine(int amountPerImbalance, int imbalance)
	{
		this.pricePerImbalance = amountPerImbalance;
		this.imbalance = imbalance;
	}
	
	public long amount()
	{
		return imbalance*pricePerImbalance;
	}
}
