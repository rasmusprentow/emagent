package emagent.ui;

import emagent.environment.Environment;

public class Program {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		Environment.getEnvironment().start(new Writer());
		
	}
	
	

}
