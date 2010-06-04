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

	public Link(Node source, Node sink, int delay, long bitrate) {
		super();
		this.source = source;
		this.sink = sink;
		this.delay = delay;
		this.bitrate = bitrate;
	}
}
