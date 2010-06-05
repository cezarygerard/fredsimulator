/**
 * 
 */
package common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author czarek
 * 
 */
public class FakeNode extends Node {

	LinkedList<Packet> queue;

	public void enquePacket(Packet pckt) {
		queue.add(pckt);
	}

	public FakeNode(int nodeId) {
		super(nodeId);
		queue = new LinkedList<Packet>();
		// TODO Auto-generated constructor stub
	}

	public void handle(long time) {
		Packet packet = queue.poll();
		if(packet == null)
			return;
		Link linkToSink = null;
		for (Iterator<Link> iterator = links.iterator(); iterator.hasNext();) {
			Link l = (Link) iterator.next();
			if(l.getDestination().getId() == 4)
			{
				linkToSink = l;
				break;
			}
			
		}
		if (!linkToSink.isBusy()) {// jezeli link jest wolny
			try {
				linkToSink.placeInLink(packet);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
