package emagent.auction;

import java.util.Stack;

import emagent.agent.IBrp;

public abstract class Auction implements IAuction{
	protected int quantity;
	protected int startingPrice;
	protected AuctionStatus status;
	protected IBrp seller;
	protected Stack<IBid> bids;
	protected IAuctionResult result;
	private boolean bidAdded = false;
	
	public Auction(int quantity, int startingPrice, IBrp seller)
	{
		this.quantity = quantity;
		this.startingPrice = startingPrice;
		this.seller = seller;
		status = AuctionStatus.CREATED;
		bids = new Stack<IBid>();
	}

	public boolean add(IBid e) throws Exception {
		if(status != AuctionStatus.POSTED)
		{
			throw new Exception("Auction has not yet been posted");
		}
		if(!verifyBid(e))
		{
			return false;
		}
		bidAdded = true;
		return bids.add(e);
	}
	
	abstract boolean verifyBid(IBid bid);

	public AuctionStatus getStatus() {
		return status;
	}

	public void setStatus(AuctionStatus status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getStartingPrice() {
		return startingPrice;
	}

	public IBrp getSeller() {
		return seller;
	}

	public Stack<IBid> getBids() {
		return bids;
	}
	
	@Override
	public void close() {
		status = AuctionStatus.DONE;
	}

	
	@Override
	public void newBiddingRound() {
		this.bidAdded = true;
	}

	@Override
	public boolean bidAdded() {
		return bidAdded;
	}
}
