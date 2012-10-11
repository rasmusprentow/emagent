package emagent.auction;

public class AuctionType {
	private BidPrice bidPrice;
	private BidType bidType;
	private BidOrder bidOrder;

	public AuctionType(BidPrice bidPrice, BidType bidType, BidOrder bidOrder) {
		super();
		this.bidPrice = bidPrice;
		this.bidType = bidType;
		this.bidOrder = bidOrder;
	}
	
	public BidPrice getBidPrice() {
		return bidPrice;
	}

	public BidType getBidType() {
		return bidType;
	}

	public BidOrder getBidOrder() {
		return bidOrder;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof AuctionType)
		{
			AuctionType other = (AuctionType)o;
			return  this.getBidOrder() == other.getBidOrder() &&
					this.getBidPrice() == other.getBidPrice() &&
					this.getBidType() == other.getBidType();
		}
		
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return getBidOrder().hashCode() ^ getBidPrice().hashCode() ^ getBidType().hashCode(); 
	}
}
