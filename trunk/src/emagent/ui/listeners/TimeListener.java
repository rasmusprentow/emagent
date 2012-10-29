package emagent.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import emagent.environment.IEnvironment;

public class TimeListener implements ActionListener {

	private IEnvironment environment;
	public TimeListener (IEnvironment env)
	{
		environment = env;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		JButton btn = (JButton) arg0.getSource();
	
		if(btn.getText() == "-")
		{
			if(environment.getSleepTime() == 0)
			{
				environment.setSleepTime(1);
			} else if(environment.getSleepTime() >= 2000)
			{
				
			}else {
				environment.setSleepTime(environment.getSleepTime()*2);
			}
		} else if(btn.getText() == "+")
		{
			
			
			environment.setSleepTime(environment.getSleepTime()/2);
		}
	}

}
