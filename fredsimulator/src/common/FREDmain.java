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
		
		
		
		///tworzenie sieci testowej:
		Fred fred = new Fred(0);
		UDPSource udpSource = new UDPSource(1); 
//		TCPSource tcpSource1 = new TCPSource(2); 
//		TCPSource tcpSource2 = new TCPSource(3);
		Sink sink = new Sink(4);
		
		
		
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
