package common;

import java.util.LinkedList;
import java.util.TreeMap;

public class Fred extends Node {

	int q; //current queue size
	long time; // current "real" time
	long avg; // average queue size
	int count; //number of packets since last drop
	long avgcq; //average per-flow queue size
	long max_q; //maximum allowed per-flow queue size
	Timer timer;
	LinkedList<Packet> buffer;
	TreeMap<Integer, Flow> flows;
	
	private class Flow{
		Node sourceNode; //referencja na wezel nadajacy
		int glen_i; //number of packets buffered;
		int strike_i; //number of over-runs;
	}
	
	
	public Fred(int id, Timer timer) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(long time) {
		// TODO Auto-generated method stub
		
	}
	
	public int getConnectionIdFromPacket(Packet P){
		return P.sourceNode.getId();
	}
	
	public long getTime(){
		return timer.getTime();
	}

}
