/**
 * 
 */
package common;

/**
 * @author czarek
 *
 */
public class Link {

	private Node source;
	
	private Node sink;
	
	private int delay;
	
	private long bitrate;

	public Link(Node source, Node destination, int delay, long bitrate) {
		super();
		this.source = source;
		this.sink = destination;
		this.delay = delay;
		this.bitrate = bitrate;
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
	public Node getSink() {
		return sink;
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
	public long getBitrate() {
		return bitrate;
	}

}
