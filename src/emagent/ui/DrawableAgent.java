package emagent.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JPanel;

abstract public class DrawableAgent extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DrawableAgent()
	{
	

		this.setForeground(Color.BLACK);
		this.add(new Label(this.toString()));
	}
	
	public abstract void update();
	

}

