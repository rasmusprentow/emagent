package emagent.environment;

public class Environment implements IEnvironment {

	static private IEnvironment instance = null;
	
	public static IEnvironment getEnvironment()
	{
		if(instance == null)
		{
			instance = new Environment();
		}
		return instance;
	}
	
	private Environment()
	{
	}
	
	
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void turnOver() {
		// TODO Auto-generated method stub
		
	}

}
