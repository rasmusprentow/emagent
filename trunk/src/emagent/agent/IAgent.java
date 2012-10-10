package emagent.agent;

import emagent.ui.DrawableAgent;
import emagent.ui.TickListener;

public interface IAgent extends TickListener{
	void subscribeToUpdates(DrawableAgent drawableAgent);
}
