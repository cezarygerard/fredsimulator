package common;

public class Sink extends Node {

	public Sink(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(long time) {
		// TODO Auto-generated method stub
		
	}

	public void enquePacket(Packet pckt) {
		
		
		
		if(pckt instanceof TCPPacket)
		{
			((TCPSource)pckt.sourceNode).handleAck(((TCPPacket)pckt).sequenceNumber);
		}
	}
}
