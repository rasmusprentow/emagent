package emagent.agent;



import java.util.ArrayList;
import emagent.agent.brp.*;
import java.util.Collections;
import java.util.Collection;
import emagent.auction.*;
import emagent.environment.Environment;

public abstract class Market extends AbstractAgent implements IMarket {
	protected ArrayList<IBrp> auctionListeners;
	
	public Market()
	{
		auctionListeners = new ArrayList<IBrp>();
	}
	
	public void subscribeToAuctions(IBrp l) {
		auctionListeners.add( l);
	}
	
	public  void unsubscribeToAuctions(IBrp l) {
		auctionListeners.remove( l);
	}

	@Override
	public Collection<IBrp> getAuctionListeners()
	{
		ArrayList<IBrp> res = new ArrayList<IBrp>();
		res.addAll(auctionListeners);
		return res;
	}
	
	

	public Object getAuctioneers() {
		return auctionListeners.clone();
	}
	
	@Override
	public void startRound() throws Exception
	{
		shuffle();
		AuctionList auctions = postRound();
		shuffle(auctions);
		Collection<IAuctionResult> results = bidRound(auctions);
		handoutRound(results);
		cleanUp();
		Environment.getEnvironment().turnOver();
	}

	@Override
	public void notifyTick(int newTick) throws Exception {

	
	}

	protected final void cleanUp() {
	}

	protected void shuffle(ArrayList<IAuction> auctions)
	{
		shuffle();
		Collections.shuffle(auctions);
	}

	protected void shuffle()
	{
		Collections.shuffle(auctionListeners);
	}
	
	protected AuctionList postRound()
	{
		AuctionList auctions = new AuctionList();
		Collection<IAuction> curPost;
		for(IBrp brp : auctionListeners)
		{
			curPost = brp.notifyPostRound(getAuctionType());
			auctions.addAll(curPost);
		}
		for(IAuction a : auctions)
		{
			a.setStatus(AuctionStatus.POSTED);
		}
		return auctions;
	}
	
	protected abstract AuctionType getAuctionType();

	protected abstract Collection<IAuctionResult> bidRound(AuctionList auctions) throws Exception;
	
	protected void handoutRound(Collection<IAuctionResult> results)
	{
		for(IAuctionResult result : results)
		{
			if(!(result instanceof NotSoldResult))
			{
				result.getBuyer().notifyAuctionResult(result);
			}
			result.getSeller().notifyAuctionResult(result);
		}
	}
}
