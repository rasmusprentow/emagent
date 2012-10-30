package emagent.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;

import emagent.agent.prosumer.IProsumer;
import emagent.ui.listeners.GuiDisListener;

public class DrawableProsumer extends DrawableAgent {

	private IProsumer prosumer;
	private Label consumation;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7119766827776710971L;

	public DrawableProsumer(IProsumer pro)
	{
		super();
		setPreferredSize(new Dimension(300,25));
		setLayout(new GridLayout(1,1));
		setVisible(true);
		setBackground(Color.GREEN);
		
		prosumer = pro;
		pro.subscribeToUpdates(this);
		consumation = new Label("XX");
		this.add(consumation);
		
	}
	
	@Override
	public void update() {
		if(GuiDisListener.isDisabled())
		{
			return ;
		}
		if(this.prosumer.getTotalConsumption() > 0)
		{
			consumation.setBackground(Color.RED);
		}
		else if(this.prosumer.getTotalConsumption() == 0)
		{
			consumation.setBackground(Color.YELLOW);
		} else {
			consumation.setBackground(Color.GREEN);
		}
		consumation.setText(Math.abs(prosumer.getTotalConsumption()) + " MW " + prosumer.getName() + " - " + prosumer.getBrpName());
		this.repaint();
	}
	
	public String toString(){ return "" ;};
}
