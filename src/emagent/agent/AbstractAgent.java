package emagent.agent;

import emagent.ui.DrawableAgent;

abstract public class AbstractAgent implements IAgent{

	protected DrawableAgent drawableAgent;
	
	public void subscribeToUpdates(DrawableAgent drawableAgent) {
		this.drawableAgent =  drawableAgent;
	
	}

	protected final void update()
	{
		
		if(drawableAgent != null)
		{
			drawableAgent.update();
		}
	}
}
