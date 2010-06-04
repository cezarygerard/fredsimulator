/**
 * 
 */
package common;

import java.io.ObjectInputStream.GetField;
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

	/**
	 * flaga oznaczajaca czy lacze jest zajete
	 * {@link isBusy()}
	 */
	private boolean isBusy;
	
	
	/**
	 * czas za ile lacze bedzie wolne
	 * wykorzystywane wewnatrz klasy do okreslenie czy lacze jest wolne
	 */
	private double timeTofree;
	
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
		isBusy =false;
		timeTofree = 0;
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
		if(isBusy = false)
		{	
			delayList.add(new PacketTimePair(p));
			timeTofree = p.size / this.bitrate;
		}
		
	}
	
	public void handle(long time)
	{
		for (int i = 0; i < delayList.size(); i++) {
			if(--delayList.get(i).timeToWait <= 0)
			{
				destination.enquePacket(delayList.get(i).pckt);
			}
		}
		
		if(timeTofree > 0 )
		{
			timeTofree--;
			isBusy = true;
		}
		else
		{
			isBusy = false;
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
	
	/**
	 * @return the isBusy
	 */
	public boolean isBusy() {
		return isBusy;
	}
}
