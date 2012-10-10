package emagent.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.BorderFactory;

import emagent.agent.IBrp;

public class DrawableBrp extends DrawableAgent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3757231984750674472L;
	private IBrp brp;
	private Label money;
	private Label consumation;
	private Label electricity;
	public DrawableBrp(IBrp brp) {
		super();
		//
		setPreferredSize(new Dimension(99,99));
		setLayout(new GridLayout(4,1));
		setVisible(true);
		setBackground(Color.GREEN);
		
		this.brp = brp;
		brp.subscribeToUpdates(this);
		money = new Label("Money           ");
		electricity = new Label("electricity              ");
		consumation = new Label("consumation              ");
		this.add(money);
		this.add(electricity);
		this.add(consumation);
	}

	@Override
	public void update() {

		money.setText("Cash: " + brp.getCurrentMonetaryBalance());
		electricity.setText("Electricity: " +brp.getCurrentElectricalBalance());
		consumation.setText("Cons.: " + brp.getTotalConsumption());
	}
	
	
	public String toString()
	{
		return "BRP";
	}
	
	
}
