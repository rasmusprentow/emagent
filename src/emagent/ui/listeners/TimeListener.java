package emagent.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import emagent.environment.IEnvironment;

public class TimeListener implements ActionListener {

	private IEnvironment environment;
	private JButton plusBtn;
	private JButton minusBtn;
	public TimeListener (IEnvironment env)
	{
		environment = env;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		JButton btn = (JButton) arg0.getSource();
	
		if(btn.getText() == "-")
		{
			minusBtn = btn;
			
			if(environment.getSleepTime() == 0)
			{
				plusBtn.setEnabled(true);
				environment.setSleepTime(1);
			
			} else if(environment.getSleepTime() < 2000)
			{
				environment.setSleepTime(environment.getSleepTime()*2);
				
			}else {
				btn.setEnabled(false);
			}
		} else if(btn.getText() == "+")
		{
			long sleepTime = environment.getSleepTime();
			plusBtn = btn;
			environment.setSleepTime(sleepTime/2);
			if(environment.getSleepTime() == 0)
			{
				
				btn.setEnabled(false);
			} else if (sleepTime >= 2000)
			{
				minusBtn.setEnabled(true);
			}
		}
	}

}
