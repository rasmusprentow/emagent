package emagent.agent;



public interface IMarket extends IAgent {
	public void subscribeToAuctions(IBrp l);
	
	public  void unsubscribeToAuctions(IBrp l);


	public void notifyTurnOver(int newTick) throws Exception;

}
