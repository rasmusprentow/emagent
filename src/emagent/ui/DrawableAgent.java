package emagent.ui;

import java.awt.Color;

import javax.swing.JPanel;

abstract public class DrawableAgent extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DrawableAgent()
	{
	 super();

		this.setForeground(Color.BLACK);
		
	}
	
	public abstract void update();
		

}

