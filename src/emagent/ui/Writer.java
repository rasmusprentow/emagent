package emagent.ui;

public class Writer implements  TickListener
{

	@Override
	public void notifyTick(int time) {
		System.out.println(time);
		
	}
	
	
	
}