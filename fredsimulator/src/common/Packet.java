/**
 * 
 */
package common;

/**
 * @author czarek
 *
 */
public abstract class Packet {

	Node sourceNoude;
	
	int size;

	public Packet(Node sourceNoude, int size) {
		super();
		this.sourceNoude = sourceNoude;
		this.size = size;
	}


	
	
}
