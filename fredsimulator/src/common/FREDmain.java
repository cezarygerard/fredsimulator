package common;

import java.util.ArrayList;
import java.util.Iterator;

public class FREDmain {

	/**
	 * @param args
	 */
	
	static Timer timer;
	
	static ArrayList<Node> nodes;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		
		while (true)
		{
			for (Iterator<Node> iterator = nodes.iterator(); iterator.hasNext();) {
				timer.increment();
				long time = timer.getTime();
				Node node = (Node) iterator.next();
				node.handle(time);				
			}
			
			
			
		}
	}

}
