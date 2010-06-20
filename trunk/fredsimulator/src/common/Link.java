/**
 * 
 */
package common;

import java.util.LinkedList;
import java.util.Random;

import utils.Pair;

/**
 * @author czarek
 * 
 */
public class Link implements Comparable<Link> {

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
	 * flaga oznaczajaca czy lacze jest zajete {@link isBusy()}
	 */
	private boolean isBusy;

	/**
	 * czas za ile lacze bedzie wolne wykorzystywane wewnatrz klasy do
	 * okreslenie czy lacze jest wolne
	 */
	private double timeTofree;

	/**
	 * tutaj odbywa sie opoznienie pierwszy parametr pary to czas jaki ma
	 * jeszcze spedzic tu pakiet
	 */
	private LinkedList<Pair<Long, Packet>> delayList;
	
	/**
	 * sluzy do wprowadzenia jakiejs losowosci - tak aby scenariusze mogly sie troche roznic
	 */
	Random rand;

	/**
	 * @param source
	 * @param destination
	 * @param delay
	 * @param d
	 */
	public Link(Node source, Node destination, int delay, double bitrate) {
		super();
		this.source = source;
		this.destination = destination;
		this.delay = delay;
		this.bitrate = bitrate;
		boolean a = source.links.add(this);
		boolean b = destination.links.add(this);
		if(a==false)
		{
			b =false;
		}
		isBusy = false;
		timeTofree = 0;
		delayList = new LinkedList<Pair<Long, Packet>>();
		rand = new Random();
		init();
	}

	private void init() {
	//	System.out.println(this + "  " + this.getClass());
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

	public void placeInLink(Packet p) throws Exception {
		//System.out.println(this + " time: " + Timer.getTime() + " placeInLink "+ p);
		if (p != null) {
			if (isBusy == false) {
				delayList.add(new Pair<Long, Packet>(new Long(
						delay), p));
				int ranV = 0;
				if(Constans.linkDelayVariation != 0.0)
				{
					ranV = rand.nextInt() %(int)( delay * Constans.linkDelayVariation);
				}
				
				timeTofree = 8 * p.size * Constans.second / this.bitrate + ranV;
			} else {
				throw new Exception(
						"Lacze zajete - trwa wysylanie innego pakietu");
			}
		}
	}

	public void handle(long time) {
		for (int i = 0; i < delayList.size(); i++) {
			if (--(delayList.get(i).first) <= 0) {
				System.out.println(this + " handle "+ delayList.get(i).first + " packet: " + delayList.get(i).first);
				destination.enquePacket(delayList.get(i).second);
				delayList.remove(i);
			}
		}

		if (timeTofree > 0) {
			timeTofree--;
			isBusy = true;
		} else {
			isBusy = false;
		}
	}

	/**
	 * @return the isBusy
	 */
	public boolean isBusy() {
	//	System.out.println(this + " isBusy " + isBusy + " ttf: " + timeTofree);
		return isBusy;
	}

	@Override
	public int compareTo(Link o) {
		if (this.source.getId() < o.source.getId()) {
			return -1;
		} else if (this.source.getId() > o.source.getId()) {
			return 1;
		} else if (this.destination.getId() < o.destination.getId()) {
			return -1;
		} else if (this.destination.getId() > o.destination.getId()) {
			return 1;
		} else {
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Link [bitrate=" + bitrate + ", delay=" + delay + ", delayList="
				+ delayList + ", destination=" + destination + ", isBusy="
				+ isBusy + ", rand=" + rand + ", source=" + source
				+ ", timeTofree=" + timeTofree + "]";
	}
	
}
