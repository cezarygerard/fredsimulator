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

		Fred fred = new Fred(0);
		TCPSource tcpSource0 = new TCPSource(0); 
		TCPSource tcpSource1 = new TCPSource(1); 
		TCPSource tcpSource2 = new TCPSource(2); 
		TCPSource tcpSource3 = new TCPSource(3); 
		TCPSource tcpSource4 = new TCPSource(4); 
		
//		TCPSource tcpSource1 = new TCPSource(2);
//		TCPSource tcpSource2 = new TCPSource(3);
//		TCPSource tcpSource3 = new TCPSource(4);
		Sink sink = new Sink(111); 
		
		//nodes.add(fake);
		nodes.add(fred);
		nodes.add(tcpSource0);
		nodes.add(tcpSource1);
		nodes.add(tcpSource2);
		nodes.add(tcpSource3);
		nodes.add(tcpSource4);
//		nodes.add(tcpSource1);
//		nodes.add(tcpSource2);
//		nodes.add(tcpSource3);
		nodes.add(sink);

		Link link0 = new Link(tcpSource0, fred, (int) ((Constans.second)/500), Constans.link_speed);
		Link link1 = new Link(tcpSource1, fred, (int) ((Constans.second)/500), Constans.link_speed);
		Link link2 = new Link(tcpSource2, fred, (int) ((Constans.second)/500), Constans.link_speed);
		Link link3 = new Link(tcpSource3, fred, (int) ((Constans.second)/500), Constans.link_speed);
		Link link4 = new Link(tcpSource4, fred, (int) ((Constans.second)/500), Constans.link_speed);
		Link link5 = new Link(fred, sink, (int) ((Constans.second)/500), Constans.link_speed );
//		Link link3 = new Link(tcpSource1, fred, (int) ((Constans.second)/500),  Constans.link_speed );
//		Link link4 = new Link(tcpSource2, fred, (int) ((Constans.second)/100), Constans.link_speed );
//		Link link5 = new Link(tcpSource3, fred, (int) ((Constans.second)/100), Constans.link_speed );

		links.add(link0);
		links.add(link1);
		links.add(link2);
		links.add(link3);
		links.add(link4);
		links.add(link5);
//-------------------------WEZLY DO REDA--------------------------------	
		RED red = new RED(0);
		TCPSource tcpSource10 = new TCPSource(10); 
		TCPSource tcpSource11 = new TCPSource(11); 
		TCPSource tcpSource12 = new TCPSource(12); 
		TCPSource tcpSource13 = new TCPSource(13); 
		TCPSource tcpSource14 = new TCPSource(14); 
		
//		TCPSource tcpSource1 = new TCPSource(2);
//		TCPSource tcpSource2 = new TCPSource(3);
//		TCPSource tcpSource3 = new TCPSource(4);
		Sink sink2 = new Sink(222); 
		
		//nodes.add(fake);
		nodes.add(red);
		nodes.add(tcpSource10);
		nodes.add(tcpSource11);
		nodes.add(tcpSource12);
		nodes.add(tcpSource13);
		nodes.add(tcpSource14);
//		nodes.add(tcpSource1);
//		nodes.add(tcpSource2);
//		nodes.add(tcpSource3);
		nodes.add(sink2);
		
		Link link10 = new Link(tcpSource10, red, (int) ((Constans.second)/500), Constans.link_speed);
		Link link11 = new Link(tcpSource11, red, (int) ((Constans.second)/500), Constans.link_speed);
		Link link12 = new Link(tcpSource12, red, (int) ((Constans.second)/500), Constans.link_speed);
		Link link13 = new Link(tcpSource13, red, (int) ((Constans.second)/500), Constans.link_speed);
		Link link14 = new Link(tcpSource14, red, (int) ((Constans.second)/500), Constans.link_speed);

		Link link15 = new Link(red, sink2, (int) ((Constans.second)/500), Constans.link_speed );
//		Link link3 = new Link(tcpSource1, fred, (int) ((Constans.second)/500),  Constans.link_speed );
//		Link link4 = new Link(tcpSource2, fred, (int) ((Constans.second)/100), Constans.link_speed );
//		Link link5 = new Link(tcpSource3, fred, (int) ((Constans.second)/100), Constans.link_speed );

		links.add(link10);
		links.add(link11);
		links.add(link12);
		links.add(link13);
		links.add(link14);
		links.add(link15);
//-------------------------~~WEZLY DO REDA--------------------------------

		long time = 0;	
		while ((time = Timer.increment())<Constans.timeToMesure)
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
