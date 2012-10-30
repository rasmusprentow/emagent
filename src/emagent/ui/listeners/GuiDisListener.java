package emagent.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GuiDisListener implements ActionListener {

	private static boolean disabled = false;
	@Override
	public void actionPerformed(ActionEvent e) {
		disabled = !disabled;
		JButton btn = (JButton) e.getSource();
		if(disabled)
		{
			btn.setText("Enable Gui");
		} else {
			btn.setText("Disable Gui");
		}
	}
	public static boolean isDisabled() {
		return disabled;
	}

}
