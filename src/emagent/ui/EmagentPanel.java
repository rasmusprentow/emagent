package emagent.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import emagent.agent.IBrp;
import emagent.agent.IProsumer;
import emagent.environment.Environment;

public class EmagentPanel extends JPanel implements TickListener{
	
	private JPanel rightSide;
	private JPanel leftSide;
	private Label totalConsumptionLabel;
	static   String TOTAL_CONSUMATION_STRING = "TotalConsumption: ";
	private Label timeLabel;
	private ArrayList<DrawableAgent> drawableAgents = null;
	private  JPanel brpsPanel;
	public EmagentPanel()
	{
		this.setLayout( new GridLayout(1,2) );
		leftSide = new JPanel();
		this.add(leftSide);
		rightSide = new JPanel();
		rightSide.setLayout(new BorderLayout());
		this.add(rightSide);
	//	leftSide.setLayout(new GridLayout(1,1))
		
		leftSide.add(new Label("Market"));
		rightSide.add(new Label("BRPs"), BorderLayout.NORTH);
		
		totalConsumptionLabel = new Label(TOTAL_CONSUMATION_STRING + "0 MW");
		leftSide.add(totalConsumptionLabel);
		
		timeLabel = new Label("Tick: XXXXXXXXXXXXXXX");
		leftSide.add(timeLabel);
		
		
		timeLabel.setSize(100,timeLabel.getHeight());
		
		brpsPanel = new JPanel();
		rightSide.add(brpsPanel, BorderLayout.CENTER);
		
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
				drawableAgents.add(new DrawableBrp(ag));
			}
			for(DrawableAgent da : drawableAgents)
			{
				
				brpsPanel.add(da, BorderLayout.CENTER);
				da.	setBorder(BorderFactory.createEmptyBorder());
			}
		}
		
	
		
		updateTotalEnergyConsumation();
		timeLabel.setText("Tick: " + time);
	}


}
