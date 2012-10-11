package emagent.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;

import emagent.agent.IProsumer;

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
		setPreferredSize(new Dimension(40,20));
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
		consumation.setText("" + prosumer.getTotalConsumption());
		
	}
	
	public String toString(){ return "" ;};
}
