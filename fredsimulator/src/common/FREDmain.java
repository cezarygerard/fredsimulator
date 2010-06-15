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
		//FakeNode fake = new FakeNode(0);
		Fred fred = new Fred(0);
		UDPSource udpSource = new UDPSource(1,  6*1000*1000); 
		TCPSource tcpSource1 = new TCPSource(2);
		TCPSource tcpSource2 = new TCPSource(3);
		TCPSource tcpSource3 = new TCPSource(4);
		Sink sink = new Sink(4);
		
		//nodes.add(fake);
		nodes.add(fred);
		nodes.add(udpSource);
		nodes.add(tcpSource1);
		nodes.add(tcpSource2);
		nodes.add(tcpSource3);
		nodes.add(sink);
//		Link link1 = new Link(udpSource, fake, (int) ((Constans.second)/100),  10 * 1000 * 1000);
//		Link link2 = new Link(fake, sink, (int) ((Constans.second)/100), 10 * 1000 * 1000 );
//		Link link3 = new Link(tcpSource1, fake, (int) ((Constans.second)/100), 100 * 1000 * 1000 );
		Link link5 = new Link(tcpSource3, fred, (int) ((Constans.second)/100), 10 * 1000 * 1000 );
		Link link1 = new Link(udpSource, fred, (int) ((Constans.second)/100),  10 * 1000 * 1000);
		Link link2 = new Link(fred, sink, (int) ((Constans.second)/100), 10 * 1000 * 1000 );
		Link link3 = new Link(tcpSource1, fred, (int) ((Constans.second)/100), 10 * 1000 * 1000 );
		Link link4 = new Link(tcpSource2, fred, (int) ((Constans.second)/100), 10 * 1000 * 1000 );
		links.add(link1);
		links.add(link2);
		links.add(link3);
		links.add(link4);
		links.add(link5);
		long time = 0;

		while ((time = Timer.increment())<60 * Constans.second)
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
