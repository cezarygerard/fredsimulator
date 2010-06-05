
package common;

import java.util.TreeSet;

/**
 * @author czarek
 * 
 */
public abstract class Node {

	/**
	 * nodeId, Link
	 */
	TreeSet<Link> links;

	final int id;

	public Node(int nodeId) {
		super();
		links = new TreeSet<Link>();
		this.id = nodeId;
	}

	public int getId() {
		return id;
	}

	public abstract void handle(long time);

	String s;

	public boolean equals(Object o) {
		if (o instanceof Node) {
			return this.id == ((Node) o).id;
		} else
			return false;
	}

	public void enquePacket(Packet pckt) {
		// TODO Auto-generated method stub

	}

}
