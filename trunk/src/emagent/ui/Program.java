package emagent.ui;



import javax.swing.JFrame;

import emagent.environment.Environment;

public class Program {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		 JFrame window = new JFrame("Thinking Box");
		 EmagentPanel content = new EmagentPanel();
	     //TBMPanel content = new TBMPanel();
	     window.setContentPane(content);
	      window.pack();  // Set size of window to preferred size of its contents.
	      window.setResizable(false);  // User can't change the window's size.
	      window.setLocation(200,400);
	      window.setSize(1200, 800);
	      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	      window.setVisible(true);
	      
	      
	      
		Environment.getEnvironment().start(content);
		
	}
	
	

}
