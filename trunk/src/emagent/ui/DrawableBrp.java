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
	private Label electricity;
	
	public DrawableBrp(IBrp brp) {
		super();
		//
		setPreferredSize(new Dimension(200,100));
		setLayout(new GridLayout(3,1));
		setVisible(true);
		setBackground(Color.YELLOW);
		
		this.brp = brp;
		brp.subscribeToUpdates(this);
		money = new Label("Money           ");
		electricity = new Label("electricity              ");
		money.setSize(100, money.getHeight());
		electricity.setSize(100,electricity.getHeight());
		this.add(money);
		this.add(electricity);
	}

	@Override
	public void update() {

		money.setText("Cash: " + brp.getCurrentMonetaryBalance());
		electricity.setText("Electricity: " +brp.getCurrentElectricalBalance());
	}
	
	
	public String toString()
	{
		return "BRP";
	}
	
	
}
