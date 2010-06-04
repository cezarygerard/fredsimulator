/**
 * 
 */
package common;

import java.util.LinkedList;

/**
 * @author czarek
 *
 */
public class Link {

	/**
	 * 
	 */
	private Node source;
	
	/**
	 * 
	 */
	private Node destination;
	
	/**
	 * in miliseconds
	 */
	private int delay;
	
	/**
	 * bits/s
	 */
	private double bitrate;

	
	private LinkedList<PacketTimePair> delayList;
	
	/**
	 * @param source
	 * @param destination
	 * @param delay
	 * @param d
	 */
	public Link(Node source, Node destination, int delay, double d) {
		super();
		this.source = source;
		this.destination = destination;
		this.delay = delay;
		this.bitrate = d;
		source.links.add(this);
		destination.links.add(this);
	}

	/**
	 * @return the source
	 */
	public Node getSource() {
		return source;
	}

	/**
	 * @return the sink
	 */
	public Node getDestination() {
		return destination;
	}

	/**
	 * @return the delay
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * @return the bitrate
	 */
	public double getBitrate() {
		return bitrate;
	}
	
	public void placeInLink(Packet p)
	{
		delayList.add(new PacketTimePair(p));
	}
	
	public void handle(long time)
	{
		for (int i = 0; i < delayList.size(); i++) {
			if(--delayList.get(i).timeToWait <= 0)
			{
				destination.enquePacket(delayList.get(i).pckt);
			}
			
		}
	}

	private class PacketTimePair
	{
		long timeToWait;
		Packet pckt;
		
		public PacketTimePair(Packet p) {
			super();
			this.timeToWait = delay;
			this.pckt = p;
		}		
	}
}
