package emagent.environment;

public class LinearFine implements IFine {
	protected int amountPerImbalance;
	protected int imbalance;
	
	public LinearFine(int amountPerImbalance, int imbalance)
	{
		this.amountPerImbalance = amountPerImbalance;
		this.imbalance = imbalance;
	}
	
	public int amount()
	{
		return imbalance*amountPerImbalance;
	}
}
