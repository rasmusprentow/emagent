package emagent.auction;


import emagent.agent.brp.*;
public interface IBid {

	public long getPrice();
	public IBrp getBuyer(); 
}
