/**
 * 
 */
package common;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author czarek
 * 
 */
public class FakeNode extends Node {

	LinkedList<Packet> queue;
	
	private static final int queueSize = 64;
	
	public FakeNode(int nodeId) {
		super(nodeId);
		queue = new LinkedList<Packet>();
		// TODO Auto-generated constructor stub
	}
	
	public void enquePacket(Packet pckt) {
		System.out.println(this + " enquePacket " + pckt);
		if(queueSize > queue.size())
			queue.add(pckt);
	}


	public void handle(long time) {

		Link linkToSink = null;
		for (Iterator<Link> iterator = links.iterator(); iterator.hasNext();) {
			Link lnk = (Link) iterator.next();
			if(lnk.getDestination().getId() == 4)
			{
				linkToSink = lnk;
				break;
			}
			
		}
		if (!linkToSink.isBusy()) {// jezeli link jest wolny
			Packet packet = queue.poll();
			if (packet  == null)
				return;
			
			System.out.println(this + " handle " + packet);
			try {
				linkToSink.placeInLink(packet);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
