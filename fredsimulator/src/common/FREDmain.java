package common;

import java.util.ArrayList;
import java.util.Iterator;

public class FREDmain {

	/**
	 * @param args
	 */
	
	static ArrayList<Node> nodes;
	static ArrayList<Link> links;
	
	public static void main(String[] args) {
		
		//long seconds = 1000000;
		nodes = new ArrayList<Node>();
		links = new ArrayList<Link>();
		///tworzenie sieci testowej:
		FakeNode fake = new FakeNode(0);
//		Fred fred = new Fred(0, Timer.timer);
		UDPSource udpSource = new UDPSource(1, 5000000); 
		TCPSource tcpSource1 = new TCPSource(2);
		TCPSource tcpSource2 = new TCPSource(3);
//		TCPSource tcpSource2 = new TCPSource(3);
		Sink sink = new Sink(4);
		nodes.add(fake);
		nodes.add(udpSource);
		nodes.add(tcpSource1);
		nodes.add(tcpSource2);
		nodes.add(sink);
		Link link1 = new Link(udpSource, fake, (int) ((Constans.second)/100),  10 * 1000 * 1000);
		Link link2 = new Link(fake, sink, (int) ((Constans.second)/100), 10 * 1000 * 1000 );
		Link link3 = new Link(tcpSource1, fake, (int) ((Constans.second)/100), 10 * 1000 * 1000 );
		Link link4 = new Link(tcpSource2, fake, (int) ((Constans.second)/100), 10 * 1000 * 1000 );
		links.add(link1);
		links.add(link2);
		links.add(link3);
		links.add(link4);
		long time = 0;

		while ((time = Timer.increment())<10 * Constans.second)
		{
			for (Iterator<Node> iterator = nodes.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
				node.handle(time);				
			}
			
			for (Iterator<Link> iterator = links.iterator(); iterator.hasNext();) {
				Link link = (Link) iterator.next();
					link.handle(time);				
				}	
		}
	}

}
