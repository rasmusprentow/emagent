package emagent.auction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class AuctionList extends ArrayList<IAuction> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9109657888610000487L;

	public AuctionList sortByPrice(boolean ascending)
	{
		final int multiplyer = ascending ? 1 : -1;
		Collections.sort(this, new Comparator<IAuction>() {
			@Override
			public int compare(IAuction o1, IAuction o2) {
				return (o1.getStartingPrice() - o2.getStartingPrice()) * multiplyer;
			}
		});
		
		return this;
	}
	
	public AuctionList sortByPrice()
	{
		return sortByPrice(true);
	}
	
	public AuctionList sortByQuantity(boolean ascending)
	{
		final int multiplyer = ascending ? 1 : -1;
		Collections.sort(this, new Comparator<IAuction>() {
			@Override
			public int compare(IAuction o1, IAuction o2) {
				return (o1.getQuantity() - o2.getQuantity()) * multiplyer;
			}
		});
		return this;
	}
	
	public AuctionList sortByQuantity()
	{
		return sortByQuantity(true);
	}
	
	public boolean bidsAddedThisRound()
	{
		for(IAuction auction : this)
		{
			if(auction.bidAdded())
			{
				return true;
			}
		}
		return false;
	}
	
	public void newBiddingRound()
	{
		for(IAuction auction : this)
		{
			auction.newBiddingRound();
		}
	}
	
	
}
