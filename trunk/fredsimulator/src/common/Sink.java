package common;

import java.util.ArrayList;

public class Sink extends Node {

	ArrayList<Packet> delayToAck;
	
	public Sink(int id) {
		super(id);
		delayToAck = new ArrayList<Packet> ();
	}

	@Override
	public void handle(long time) {
		// TODO Auto-generated method stub
		
	}

	public void enquePacket(Packet pckt) {
			
		if(pckt instanceof TCPPacket)
		{
			delayToAck.add(pckt);
//			((TCPSource)pckt.sourceNode).handleAck(((TCPPacket)pckt).sequenceNumber);
		}
	}
}
