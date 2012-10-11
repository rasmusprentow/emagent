package emagent.auction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class AuctionList extends ArrayList<IAuction> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9109657888610000487L;

	public void sortByPrice()
	{
		
		Collections.sort(this, new Comparator<IAuction>() {

		

			@Override
			public int compare(IAuction o1, IAuction o2) {
				return o1.getStartingPrice() - o2.getStartingPrice();
				
			}
			
			
		});
		
	}
	
	public void sortByQuantity()
	{
		
		Collections.sort(this, new Comparator<IAuction>() {

		

			@Override
			public int compare(IAuction o1, IAuction o2) {
				return o1.getQuantity() - o2.getQuantity();
				
			}
			
			
		});
		
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
	
	
}
