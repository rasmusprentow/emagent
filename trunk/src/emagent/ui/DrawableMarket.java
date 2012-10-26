package emagent.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
	
	public DrawableMarket(IMarket market) {
		super();
		
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
		//auctionsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	
	///	auctionsPanel.add(new Label("sss"));
		
		auctions = new ArrayList<Label>();
		for(int i = 0 ; i < auctionLog.getTreshold(); i++){
			Label l = new Label("");
			auctions.add(l);
			auctionsPanel.add(l);
		}
		add(auctionsPanel);
	}
	@Override
	public void update() {
		
		//auctionsPanel.removeAll();
		auctionsPanel.add(new Label("TEST"));
		auctionsPanel.repaint();
		int i = 0;
		System.out.println(auctionLog.size());
		for(IAuction auction : auctionLog)
		{
			
			
			auctions.get(i).setText(("Sold " + auction.getQuantity() + "E  for " + 
													auction.getResult().getPrice() + 
													" from " + auction.getSeller().toString() + " to " +
													auction.getResult().getBuyer() ));
			Color color = Color.green;
			if(auction.getResult().getBuyer() == null){
				color = Color.RED;
			}
			auctions.get(i).setForeground(color);
			if(i >= auctionLog.getTreshold() - 1)
			{
				continue;
			}
			i++;
		}
		
	}

}
