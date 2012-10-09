package emagent.agent;

public interface IProsumer {

	/**
	 * 
	 * @return The amount of electricity consumed in one tick. Negative is production. 
	 */
	int getTotalConsumption();
}
