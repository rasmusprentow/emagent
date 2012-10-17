package emagent.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JPanel;

import emagent.agent.brp.*;
import emagent.agent.IMarket;
import emagent.auction.Auction;
import emagent.auction.AuctionLog;
import emagent.auction.IAuction;

public class DrawableMarket extends DrawableAgent {

	private IMarket market;
	private JPanel auctionsPanel;
	private AuctionLog auctionLog;
	
	public DrawableMarket(IMarket market) {
		super();
		//
		setPreferredSize(new Dimension(199,199));
		setLayout(new GridLayout(4,1));
		setVisible(true);
		setBackground(Color.GREEN);
		
		this.market = market;
		market.subscribeToUpdates(this);
		/*money = new Label("Money           ");
		electricity = new Label("electricity              ");
		consumation = new Label("consumation              ");
		this.add(money);
		this.add(electricity);
		this.add(consumation);*/
		this.setLayout(new BorderLayout());
		auctionsPanel = new JPanel();
		add(auctionsPanel);
		auctionLog = market.getAuctionHistory();
		
		
	}
	@Override
	public void update() {
		auctionsPanel = new JPanel();
		auctionsPanel.setPreferredSize(new Dimension(200,200));
		auctionsPanel.setLayout(new BorderLayout(10,1));
		int i = 0;
		for(IAuction auction : auctionLog)
		{
			JPanel aucPanel = new JPanel();
			aucPanel.add(new Label(auction.getSeller().toString()));
			auctionsPanel.add(aucPanel);
			if(i >= 10)
			{
				continue;
			}
			i++;
		}
	}

}
