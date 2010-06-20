package common;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import utils.Pair;

public class Sink extends Node {

	ArrayList<Pair<Long, Packet>> delayToAck;
	TreeMap<Integer, FileWriter> writers;

	public Sink(int id) {
		super(id);
		delayToAck = new ArrayList<Pair<Long, Packet>>();
		writers = new TreeMap<Integer, FileWriter>();
	}

	public void handle(long time) {
		for (int i = 0; i < delayToAck.size(); i++) {
			Pair<Long, Packet> element = delayToAck.get(i);
			if (element.first <= time) {
				((TCPSource) element.second.sourceNode)
				.handleAck(((TCPPacket) element.second).sequenceNumber);
				delayToAck.remove(i);
				if (element.second instanceof TCPPacket) {
					System.out.println(this
							+ " handle element: "
							+ element.first
							+ "   "
							+ element.second
							+ "source_window_size"
							+ ((TCPSource) element.second.sourceNode)
							.getWindowSize());
					if(Timer.getTime() > Constans.timeStartToMesure)
					{
						try {
							writers.get(element.second.sourceNode.id).write("\n"+ Timer.getTime()+";" + this +";"
									+ " handle element: " +";"
									+ element.first +";"
									+ "   "
									+ element.second +";"
									+ "source_window_size" +";"
									+ ((TCPSource) element.second.sourceNode)
									.getWindowSize());
						} catch (IOException e) {
							try {
								writers.put(element.second.sourceNode.id, new FileWriter("logfile_" + this.id + "_" + element.second.sourceNode.name + "_" + element.second.sourceNode.id + ".txt"));
								writers.get(element.second.sourceNode.id).write("\n"+ Timer.getTime()+";" + this +";"
										+ " handle element: " +";"
										+ element.first +";"
										+ "   "
										+ element.second +";"
										+ "source_window_size" +";"
										+ ((TCPSource) element.second.sourceNode)
										.getWindowSize());


							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				} else {
					System.out.println(this + " handle element: "
							+ element.first + "   " + element.second);
				}
			}
		}
	}

	public void enquePacket(Packet pckt) {
		System.out.println(this.getClass() + " przyszedl pakiet: " + pckt);
		if(Timer.getTime() > Constans.timeStartToMesure)
		{
			try {
				writers.get(pckt.sourceNode.id).write("\n" + Timer.getTime() +";" + "  " +";" + this.getClass()
						+ " przyszedl pakiet: " + ";" + pckt);
				writers.get(pckt.sourceNode.id).flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				try {
					writers.put(pckt.sourceNode.id, new FileWriter("logfile_" + this.id + "_" + pckt.sourceNode.name + "_" + pckt.sourceNode.id + ".txt"));

					writers.get(pckt.sourceNode.id).write("\n" + Timer.getTime() +";" + "  " +";" + this.getClass()
							+ " przyszedl pakiet: " + ";" + pckt);
					writers.get(pckt.sourceNode.id).flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (pckt instanceof TCPPacket) {
			// uwaga, tutaj od razu dodawany jest znacznik - czas w ktorym ma
			// byc wywolane ack
			delayToAck.add((new Pair<Long, Packet>(new Long(Timer.getTime()
					- ((TCPPacket)pckt).sentTime), pckt)));
		}
	}
}
