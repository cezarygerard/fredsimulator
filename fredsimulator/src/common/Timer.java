package common;

public class Timer {

	private long time;

	public long getTime() {
		return timer.time;
	}
	
	public long increment()
	{
		return ++timer.time;
	}
	
	static Timer timer = new Timer();
}
