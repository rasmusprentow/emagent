package emagent.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiDisListener implements ActionListener {

	private static boolean disabled = false;
	@Override
	public void actionPerformed(ActionEvent e) {
		disabled = !disabled;
	}
	public static boolean isDisabled() {
		return disabled;
	}

}
