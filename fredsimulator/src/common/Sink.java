package common;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import utils.Pair;

public class Sink extends Node {

	ArrayList<Pair<Long, Packet>> delayToAck;
	FileWriter writer ;
	public Sink(int id) {
		super(id);
		delayToAck = new ArrayList<Pair<Long, Packet>>();
		try {
			writer = new FileWriter("logfile.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void handle(long time) {
		for (int i = 0; i < delayToAck.size(); i++) {
			Pair<Long, Packet> element = delayToAck.get(i);
			if (element.first <= time) {
				System.out.println(this + " handle element: " + element.first +  "   "+ element.second);
				((TCPSource) element.second.sourceNode)
						.handleAck(((TCPPacket) element.second).sequenceNumber);
				delayToAck.remove(i);
			}
		}
	}

	public void enquePacket(Packet pckt) {
		System.out.println(this.getClass() + " przyszedl pakiet: " + pckt);
		try {
			writer.write("\n" + Timer.getTime() + "  " + this.getClass() + " przyszedl pakiet: " + pckt);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pckt instanceof TCPPacket) {
			// uwaga, tutaj od razu dodawany jest znacznik - czas w ktorym ma
			// byc wywolane ack
			delayToAck.add((new Pair<Long, Packet>(new Long(Timer.getTime()
					+ Constans.rtt / 2), pckt)));
		}
	}
}
