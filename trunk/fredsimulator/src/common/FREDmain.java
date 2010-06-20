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
		UDPSource udpSource0 = new UDPSource(0,  (long) (Constans.link_speed)); 
		UDPSource udpSource1 = new UDPSource(1,  (long) (Constans.link_speed)); 
		UDPSource udpSource2 = new UDPSource(2,  (long) (Constans.link_speed)); 
		UDPSource udpSource3 = new UDPSource(3,  (long) (Constans.link_speed)); 
		UDPSource udpSource4 = new UDPSource(4,  (long) (Constans.link_speed)); 
		
//		TCPSource tcpSource1 = new TCPSource(2);
//		TCPSource tcpSource2 = new TCPSource(3);
//		TCPSource tcpSource3 = new TCPSource(4);
		Sink sink = new Sink(111); 
		
		//nodes.add(fake);
		nodes.add(fred);
		nodes.add(udpSource0);
		nodes.add(udpSource1);
		nodes.add(udpSource2);
		nodes.add(udpSource3);
		nodes.add(udpSource4);
//		nodes.add(tcpSource1);
//		nodes.add(tcpSource2);
//		nodes.add(tcpSource3);
		nodes.add(sink);

		Link link0 = new Link(udpSource0, fred, (int) ((Constans.second)/500), Constans.link_speed);
		Link link1 = new Link(udpSource1, fred, (int) ((Constans.second)/500), Constans.link_speed);
		Link link2 = new Link(udpSource2, fred, (int) ((Constans.second)/500), Constans.link_speed);
		Link link3 = new Link(udpSource3, fred, (int) ((Constans.second)/500), Constans.link_speed);
		Link link4 = new Link(udpSource4, fred, (int) ((Constans.second)/500), Constans.link_speed);
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
//-------------------------WEZLY DO FREDA--------------------------------	
		RED red = new RED(0);
		UDPSource udpSource10 = new UDPSource(0,  (long) (Constans.link_speed)); 
		UDPSource udpSource11 = new UDPSource(1,  (long) (Constans.link_speed)); 
		UDPSource udpSource12 = new UDPSource(2,  (long) (Constans.link_speed)); 
		UDPSource udpSource13 = new UDPSource(3,  (long) (Constans.link_speed)); 
		UDPSource udpSource14 = new UDPSource(4,  (long) (Constans.link_speed)); 
		
//		TCPSource tcpSource1 = new TCPSource(2);
//		TCPSource tcpSource2 = new TCPSource(3);
//		TCPSource tcpSource3 = new TCPSource(4);
		Sink sink2 = new Sink(222); 
		
		//nodes.add(fake);
		nodes.add(red);
		nodes.add(udpSource10);
		nodes.add(udpSource11);
		nodes.add(udpSource12);
		nodes.add(udpSource13);
		nodes.add(udpSource14);
//		nodes.add(tcpSource1);
//		nodes.add(tcpSource2);
//		nodes.add(tcpSource3);
		nodes.add(sink);
		
		Link link10 = new Link(udpSource10, red, (int) ((Constans.second)/500), Constans.link_speed);
		Link link11 = new Link(udpSource11, red, (int) ((Constans.second)/500), Constans.link_speed);
		Link link12 = new Link(udpSource12, red, (int) ((Constans.second)/500), Constans.link_speed);
		Link link13 = new Link(udpSource13, red, (int) ((Constans.second)/500), Constans.link_speed);
		Link link14 = new Link(udpSource14, red, (int) ((Constans.second)/500), Constans.link_speed);

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
//-------------------------~~WEZLY DO FREDA--------------------------------

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
