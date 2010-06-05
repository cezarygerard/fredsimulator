package common;

import java.util.ArrayList;

import utils.Pair;

public class Sink extends Node {

	ArrayList<Pair<Long, Packet>> delayToAck;

	public Sink(int id) {
		super(id);
		delayToAck = new ArrayList<Pair<Long, Packet>>();
	}

	@Override
	public void handle(long time) {
		for (int i = 0; i < delayToAck.size(); i++) {
			Pair<Long, Packet> element = delayToAck.get(i);
			if (element.first >= time) {
				((TCPSource) element.second.sourceNode)
						.handleAck(((TCPPacket) element.second).sequenceNumber);
				delayToAck.remove(i);
			}
		}
	}

	public void enquePacket(Packet pckt) {
		System.out.println(this.getClass() + " przyszedl pakiet: " + pckt);
		if (pckt instanceof TCPPacket) {
			// uwaga, tutaj od razu dodawany jest znacznik - czas w ktorym ma
			// byc wywolane ack
			delayToAck.add((new Pair<Long, Packet>(new Long(Timer.getTime()
					+ Constans.rtt / 2), pckt)));
		}
	}
}
