package emagent.ui;

public class Writer implements  TickListener
{

	@Override
	public void notifyTick(long time) {
		System.out.println(time);
		
	}
	
	
	
}