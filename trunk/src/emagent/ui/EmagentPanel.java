package emagent.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import emagent.agent.brp.*;
import emagent.agent.IProsumer;
import emagent.auction.IAuction;
import emagent.auction.NewRoundAuction;
import emagent.auction.NotSoldResult;
import emagent.environment.Environment;

public class EmagentPanel extends JPanel implements TickListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String TOTAL_IMBALANCE_STRING = "Total Imbalance: ";
	private JPanel center;
	private JPanel leftSide;
	private JPanel rightSide;
	private JPanel topPanel;
	private Label totalConsumptionLabel;
	static   String TOTAL_CONSUMATION_STRING = "Total Consumption: ";
	private String AVERAGE_ENERGY_PRICE_STRING = "Average Energy Price: ";
	static   String TOTAL_MONETARY_STRING = "TotalMoney: ";
	private Label timeLabel;
	private ArrayList<DrawableAgent> drawableAgents = null;
	private  JPanel brpsPanel;
	private  JPanel prosumersPanel;
	private DrawableMarket market;
	private Label totalImbalanceLabel;
	private Label averageEnergyPricelabel;
	private FileWriter file;
	private Label totalMoneyLabel;
	public EmagentPanel()
	{
		try {
			file = new FileWriter("avg.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLayout( new GridLayout(1,3) );
		leftSide = new JPanel();
		leftSide.setLayout(new BorderLayout());
		leftSide.setSize(this.getWidth()/2, this.getWidth());
		this.add(leftSide);
		center = new JPanel();
		
		center.setLayout(new BorderLayout());
		this.add(center);
	//	leftSide.setLayout(new GridLayout(1,1))
		
		
		
		center.add(new Label("BRPs"), BorderLayout.NORTH);
		
		totalConsumptionLabel = new Label(TOTAL_CONSUMATION_STRING + "0 MW");
		totalImbalanceLabel = new Label(TOTAL_IMBALANCE_STRING + "0 MW");
		averageEnergyPricelabel = new Label(AVERAGE_ENERGY_PRICE_STRING + "0 MW");
		totalMoneyLabel = new Label(TOTAL_MONETARY_STRING + "0");
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(4,1));
		topPanel.add(totalConsumptionLabel);
		topPanel.add(totalImbalanceLabel);
		topPanel.add(averageEnergyPricelabel);
		topPanel.add(totalMoneyLabel);
		timeLabel = new Label("Tick: XXXXXXXXXXXXXXX");
		topPanel.add(timeLabel);
		topPanel.setBackground(Color.black);
		topPanel.setForeground(Color.yellow);
		leftSide.add(topPanel, BorderLayout.NORTH);
		market = new DrawableMarket();
		leftSide.add(market, BorderLayout.CENTER);
		timeLabel.setSize(100,timeLabel.getHeight());
		
		brpsPanel = new JPanel();
		center.add(brpsPanel, BorderLayout.CENTER);
		
		rightSide = new JPanel();
		rightSide.setLayout(new BorderLayout());
		rightSide.add(new Label("Prosumers"), BorderLayout.NORTH);
		this.add(rightSide);
		
		prosumersPanel = new JPanel();
		rightSide.add(prosumersPanel, BorderLayout.CENTER);
		
		center.setBackground(Color.black);
		center.setForeground(Color.yellow);
		leftSide.setBackground(Color.black);
		leftSide.setForeground(Color.yellow);
		rightSide.setBackground(Color.black);
		rightSide.setForeground(Color.yellow);
		brpsPanel.setBackground(Color.black);
		brpsPanel.setForeground(Color.yellow);
		prosumersPanel.setBackground(Color.black);
		prosumersPanel.setForeground(Color.yellow);
	}
	
	public void updateTotalEnergyConsumation()
	{
		int total = 0;
		for(IProsumer pro : Environment.getEnvironment().getProsumers())
		{
			total += pro.getTotalConsumption();
		}
		totalConsumptionLabel.setText(TOTAL_CONSUMATION_STRING + total + " MW");
	}
	
	public void updateTotalEnergyImbalance()
	{
		int total = 0;
		for(IBrp brp : Environment.getEnvironment().getBrps())
		{
			total += Math.abs(brp.getCurrentElectricalBalance());
		}
		totalImbalanceLabel.setText(TOTAL_IMBALANCE_STRING + total + " MW");
	}
	
	public void updateAverageEnergyPrice()
	{
		int total = 0;
		int count = 0;
		boolean newRoundFound = false;
		for(IAuction auction : Environment.getEnvironment().getMarket().getAuctionHistory())
		{
			if(auction instanceof NewRoundAuction)
			{
				if(newRoundFound || true)
				{
					break;
				}
				newRoundFound = true;
			}
			else
			{
				if(!(auction instanceof NotSoldResult))
				{
					total += auction.getResult().getPrice();
					count += auction.getQuantity();
				}
			}
		}
		if(count > 0)
		{
			averageEnergyPricelabel.setText(AVERAGE_ENERGY_PRICE_STRING + total/count);
			try {
				file.write(Environment.getEnvironment().getTime() + "," + total+","+count+"\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateTotalMoney()
	{
		int total = 0;
		for(IBrp brp : Environment.getEnvironment().getBrps())
		{
			total += (brp.getCurrentMonetaryBalance());
		}
		totalMoneyLabel.setText(TOTAL_MONETARY_STRING + total + " ");
	}
	
	/*
	public void updateTotalMonetaryBalance()
	{
		int total = 0;
		for(IBrps brp : Environment.getEnvironment().getBrps())
		{
			total += brp.getTotalConsumption();
		}
		totalConsumption.setText(TOTAL_CONSUMATION_STRING + total + " MW");
	}
	*/
	
	
	@Override
	public void notifyTick(int time) {
		if(drawableAgents == null)
		{
			
			drawableAgents = new ArrayList<DrawableAgent>();
			for(IBrp ag : Environment.getEnvironment().getBrps())
			{
				DrawableAgent da = new DrawableBrp(ag);
				drawableAgents.add(da);
				brpsPanel.add(da, BorderLayout.CENTER);
				da.	setBorder(BorderFactory.createEmptyBorder());
			}
			for(IProsumer ag : Environment.getEnvironment().getProsumers())
			{
				DrawableAgent da = new DrawableProsumer(ag);
				drawableAgents.add(da);
				prosumersPanel.add(da, BorderLayout.CENTER);
				da.setBorder(BorderFactory.createEmptyBorder());
			}
			
			market.setEnvironment(Environment.getEnvironment().getMarket());
		
		}
		
		updateTotalMoney();
		updateTotalEnergyImbalance();
		updateTotalEnergyConsumation();
		updateAverageEnergyPrice();
		timeLabel.setText("Day: " + time/24 + " Hour: " + time % 24);
	}


}
