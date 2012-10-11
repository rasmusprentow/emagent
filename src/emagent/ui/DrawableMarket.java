package emagent.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JPanel;

import emagent.agent.IBrp;
import emagent.agent.IMarket;
import emagent.auction.Auction;
import emagent.auction.AuctionLog;

public class DrawableMarket extends DrawableAgent {

	private IMarket market;
	private JPanel auctionsPanel;
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
		AuctionLog auctionLog = market.getAuctionHistory();
		
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
