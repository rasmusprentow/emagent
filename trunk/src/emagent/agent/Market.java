package emagent.agent;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import emagent.auction.*;
import emagent.environment.Environment;

public abstract class Market implements IMarket {
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
	public void notifyTurnOver(int newTick) throws Exception {

		shuffle();
		ArrayList<IAuction> auctions = postRound();
		shuffle(auctions);
		Collection<IAuctionResult> results = bidRound(auctions);
		handoutRound(results);
		cleanUp();
		Environment.getEnvironment().turnOver();
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
	
	protected ArrayList<IAuction> postRound()
	{
		ArrayList<IAuction> auctions = new ArrayList<IAuction>();
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

	protected abstract Collection<IAuctionResult> bidRound(ArrayList<IAuction> auctions) throws Exception;
	
	protected void handoutRound(Collection<IAuctionResult> results)
	{
		for(IAuctionResult result : results)
		{
			result.getBuyer().notifyAuctionResult(result);
			result.getSeller().notifyAuctionResult(result);
		}
	}
}
