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
	private static final int queueSize = 32;
	
	public void enquePacket(Packet pckt) {
		System.out.println(this + " enquePacket ");
		if(queueSize > queue.size())
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
			Link lnk = (Link) iterator.next();
			if(lnk.getDestination().getId() == 4)
			{
				linkToSink = lnk;
				break;
			}
			
		}
		if (!linkToSink.isBusy()) {// jezeli link jest wolny
			System.out.println(this + " handle linkToSink.isBusy()?? " + linkToSink.isBusy());
			try {
				linkToSink.placeInLink(packet);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
