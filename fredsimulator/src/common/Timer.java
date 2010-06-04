package common;

public class Timer {

	private long time;

	public static long getTime() {
		return timer.time;
	}
	
	public static long increment()
	{
		return ++timer.time;
	}
	

	static Timer timer = new Timer();
}
