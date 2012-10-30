package emagent.auction;

import java.util.Stack;

import emagent.agent.brp.IBrp;

public class NewRoundAuction implements IAuction {

	@Override
	public boolean addBid(IBid e) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AuctionStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStatus(AuctionStatus status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getStartingPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getMinimumBidPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IBrp getSeller() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stack<IBid> getBids() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IAuctionResult getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void newBiddingRound() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean bidAdded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMaximumBidPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AuctionType getAuctionType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBrp getLeadingBidder() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
