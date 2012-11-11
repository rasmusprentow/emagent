package emagent.auction;


import emagent.agent.brp.IBrp;
public interface IBid {

	public long getPrice();
	public IBrp getBuyer(); 
}
