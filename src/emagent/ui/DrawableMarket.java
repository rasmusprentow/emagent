package emagent.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;


import javax.swing.JPanel;


import emagent.agent.IMarket;
import emagent.auction.*;

public class DrawableMarket extends DrawableAgent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IMarket market;
	private JPanel auctionsPanel;
	private ArrayList<Label> auctions;
	private AuctionLog auctionLog;
	
	public DrawableMarket() {
		super();
	
	}
	@Override
	public void update() {
		if(market == null) return;
		
		int i = 0;

		for(IAuction auction : auctionLog)
		{
			
			
			auctions.get(i).setText(("Sold " + auction.getQuantity() + " MW  for " + 
													auction.getResult().getPrice() + 
													"  from " + auction.getSeller().toString() + " to " +
													auction.getResult().getBuyer() +
													" each: " + auction.getResult().getPrice()/auction.getQuantity()) + "");
			Color color = Color.green;
			if(auction.getResult().getBuyer() == null){
				color = Color.RED;
			}
			auctions.get(i).setForeground(color);
			if(i >= auctionLog.getTreshold() - 1)
			{
				break;
			}
			i++;
		}
		
	}
	public void setEnvironment(IMarket market) {
		
		
		setPreferredSize(new Dimension(199,700));
		setLayout(new GridLayout(4,1));
		setVisible(true);
		setBackground(Color.GREEN);
		
		this.market = market;
		this.market.subscribeToUpdates(this);
		
		this.setLayout(new BorderLayout());
		auctionsPanel = new JPanel();
		
		auctionLog = market.getAuctionHistory();
		auctionsPanel.setBackground(Color.BLACK);
		auctionsPanel.setForeground(Color.YELLOW);
		auctionsPanel.setPreferredSize(new Dimension(200,200));
		auctionsPanel.setLayout(new GridLayout(auctionLog.getTreshold(),1));
	
		
		auctions = new ArrayList<Label>();
		for(int i = 0 ; i < auctionLog.getTreshold(); i++){
			Label l = new Label("");
			auctions.add(l);
			auctionsPanel.add(l);
		}
		add(auctionsPanel);
	}

}
