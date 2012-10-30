package emagent.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import emagent.agent.brp.*;
import emagent.ui.listeners.GuiDisListener;

public class DrawableBrp extends DrawableAgent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3757231984750674472L;
	private IBrp brp;
	private Label money;
	private Label consumation;
	private Label electricity;
	private Label fines;
	public DrawableBrp(IBrp brp) {
		
		this.brp = brp;
		setPreferredSize(new Dimension(99,99));
		setLayout(new GridLayout(5,1));
		setVisible(true);
		setBackground(Color.GREEN);
		this.add(new Label(this.toString()));
		
		brp.subscribeToUpdates(this);
		money = new Label("Money           ");
		electricity = new Label("electricity              ");
		consumation = new Label("consumation              ");
		fines = new Label("fines              ");
		this.add(money);
		this.add(electricity);
		this.add(consumation);
		this.add(fines);
	}

	@Override
	public void update() {
	
		if(GuiDisListener.isDisabled())
		{
			return ;
		}
		money.setText("Cash: " + brp.getCurrentMonetaryBalance()/1000 + "k");
		electricity.setText("Electricity: " +brp.getCurrentElectricalBalance());
		consumation.setText(formatTotalConsumption());
		fines.setText("Fined: " + brp.getFineAmountThisRound() / 1000 + "k");
	}
	
	
	public String toString()
	{
		return this.brp.toString();
	}
	
	private String formatTotalConsumption()
	{
		long cons = brp.getTotalConsumption();
		if(cons < 0)
		{
			return "Prod: " + Math.abs(cons);
		}
		else
		{
			return "Cons: " + cons;
		}
		
		
	}
	
}
