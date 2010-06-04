package common;

public class Timer {

	private long time;

	public static long getTime() {
		return timer.time;
	}
	
	static long increment()
	{
		return ++timer.time;
	}
	

	static Timer timer = new Timer();
}
