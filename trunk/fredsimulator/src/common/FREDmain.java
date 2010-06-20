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
		UDPSource udpSource = new UDPSource(1,  (long) (Constans.link_speed)); 
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

		Link link1 = new Link(udpSource, fred, (int) ((Constans.second)/100), Constans.link_speed);
		Link link2 = new Link(fred, sink, (int) ((Constans.second)/100),  2*Constans.link_speed );
		Link link3 = new Link(tcpSource1, fred, (int) ((Constans.second)/100),  Constans.link_speed );
		Link link4 = new Link(tcpSource2, fred, (int) ((Constans.second)/100), Constans.link_speed );
		Link link5 = new Link(tcpSource3, fred, (int) ((Constans.second)/100), Constans.link_speed );

		links.add(link1);
		links.add(link2);
		links.add(link3);
		links.add(link4);
		links.add(link5);
//-------------------------WEZLY DO FREDA--------------------------------	
		RED red = new RED(5);
		UDPSource udpSource6 = new UDPSource(6,  (long) (Constans.link_speed)); 
		TCPSource tcpSource7 = new TCPSource(7);
		TCPSource tcpSource8 = new TCPSource(8);
		TCPSource tcpSource9 = new TCPSource(9);
		Sink sink2 = new Sink(10);
		
		//nodes.add(fake);
		nodes.add(red);
		nodes.add(udpSource6);
		nodes.add(tcpSource7);
		nodes.add(tcpSource8);
		nodes.add(tcpSource9);
		nodes.add(sink2);

		Link link6 = new Link(udpSource6, red, (int) ((Constans.second)/100), Constans.link_speed);
		Link link7 = new Link(red, sink2, (int) ((Constans.second)/100),  2*Constans.link_speed );
		Link link8 = new Link(tcpSource7, red, (int) ((Constans.second)/100),  Constans.link_speed );
		Link link9 = new Link(tcpSource8, red, (int) ((Constans.second)/100), Constans.link_speed );
		Link link10 = new Link(tcpSource9, red, (int) ((Constans.second)/100), Constans.link_speed );

		links.add(link6);
		links.add(link7);
		links.add(link8);
		links.add(link9);
		links.add(link10);
//-------------------------~~WEZLY DO FREDA--------------------------------

		long time = 0;	
		while ((time = Timer.increment())<120 * Constans.second)
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
