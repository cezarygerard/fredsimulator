/**
 * 
 */
package common;

/**
 * @author czarek
 *
 */
public class TCPPacket extends Packet {

	public TCPPacket(Node sourceNoude, int size,long sequenceNumber) {
		super(sourceNoude, size);
		this.sequenceNumber = sequenceNumber;
		// TODO Auto-generated constructor stub
	}
	
	long sequenceNumber;

}
