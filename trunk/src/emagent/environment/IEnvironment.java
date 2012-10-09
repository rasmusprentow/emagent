package emagent.environment;

import emagent.ui.Writer;
import emagent.ui.TickListener;

public interface IEnvironment {
	
	public int getTime();
	public void turnOver() throws Exception;
	void start(TickListener tickListener) throws Exception;
}
