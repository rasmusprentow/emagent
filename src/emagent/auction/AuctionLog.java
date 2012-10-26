package emagent.auction;


import java.util.LinkedList;

public class AuctionLog extends LinkedList<IAuction> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2417290462976212388L;
	private int treshold = 45;
	
	public int getTreshold() {
		return treshold;
	}

	public void addFirst(IAuction auction)
	{
		if(this.size() >= treshold)
		{
			this.removeLast();
		}
		 super.addFirst(auction);
	}

	public void addAllFirst(AuctionList auctions) {
		for(IAuction a : auctions)
		{
			addFirst(a);
		}
		
	}
	
	
}
