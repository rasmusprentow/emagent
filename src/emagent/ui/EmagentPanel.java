package emagent.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import emagent.agent.brp.*;
import emagent.agent.IProsumer;
import emagent.environment.Environment;

public class EmagentPanel extends JPanel implements TickListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel center;
	private JPanel leftSide;
	private JPanel rightSide;
	private Label totalConsumptionLabel;
	static   String TOTAL_CONSUMATION_STRING = "TotalConsumption: ";
	private Label timeLabel;
	private ArrayList<DrawableAgent> drawableAgents = null;
	private  JPanel brpsPanel;
	private  JPanel prosumersPanel;
	private DrawableMarket market;
	public EmagentPanel()
	{
		this.setLayout( new GridLayout(1,3) );
		leftSide = new JPanel();
		leftSide.setLayout(new GridLayout(3,1));
		leftSide.setSize(this.getWidth()/2, this.getWidth());
		this.add(leftSide);
		center = new JPanel();
		
		center.setLayout(new BorderLayout());
		this.add(center);
	//	leftSide.setLayout(new GridLayout(1,1))
		
		
		
		center.add(new Label("BRPs"), BorderLayout.NORTH);
		
		totalConsumptionLabel = new Label(TOTAL_CONSUMATION_STRING + "0 MW");
		leftSide.add(totalConsumptionLabel);
		
		timeLabel = new Label("Tick: XXXXXXXXXXXXXXX");
		leftSide.add(timeLabel);
		
		
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
			
			market = new DrawableMarket(Environment.getEnvironment().getMarket());
			leftSide.add(market);
		}
		
	
		
		updateTotalEnergyConsumation();
		timeLabel.setText("Tick: " + time);
	}


}
