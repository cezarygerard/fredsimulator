/**
 * 
 */
package common;

/**
 * @author czarek
 * 
 */
public class TCPPacket extends Packet {

	long sequenceNumber;

	public TCPPacket(Node sourceNoude, int size,long sequenceNumber) {
		super(sourceNoude, size);
		this.sequenceNumber = sequenceNumber;
		// TODO Auto-generated constructor stub
	}

	public boolean equals(Object o) {
		if (o instanceof TCPPacket) {
			return (this.sequenceNumber == ((TCPPacket) o).sequenceNumber) && this.sourceNode.equals(((TCPPacket)o).sourceNode);
		} else
			return false;
	}
	
	public String toString() {
		return super.toString() + " sequenceNumber: " + sequenceNumber;
	}
}
