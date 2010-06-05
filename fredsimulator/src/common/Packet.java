/**
 * 
 */
package common;

/**
 * @author czarek
 *
 */
public abstract class Packet {

	Node sourceNode;
	
//	Node destinationNode;
	
	int size;

	public Packet(Node sourceNode, int size) {
		super();
		this.sourceNode = sourceNode;
		this.size = size;
	}


	
	public String toString() {
		return "Packet: " + this.getClass() + " sourceNode: " + sourceNode;
	}


	
	
}
