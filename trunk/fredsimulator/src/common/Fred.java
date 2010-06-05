package common;

import java.util.LinkedList;
import java.util.TreeMap;

/**
 * @author Malina
 *
 */
public class Fred extends Node {

	int q; //current queue size
	long time; // current "real" time
	long avg; // average queue size
	int count; //number of packets since last drop
	long avgcq; //average per-flow queue size
	long max_q; //maximum allowed per-flow queue size
	int Nactive;  // number of active flows
	//Timer timer;
	
	LinkedList<Packet> buffer;
	TreeMap<Integer, Flow> flows; // klucz to id source'a
	
	private class Flow{
		Node sourceNode; //referencja na wezel nadajacy
		int qlen_i; //number of packets buffered;
		int strike_i; //number of over-runs;
		Flow(){};
		Flow(Node sourceNode){
			this.sourceNode=sourceNode;
			qlen_i=0;
			strike_i=0;
		}
	}
	
	
	public Fred(int id, Timer timer) {
		super(id);
		q=0;
		avg=0;
		count=0;
		avgcq=0;
		max_q=0;
		Nactive=0;
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
		return Timer.getTime();
	}

	/** Metoda tworzaca nowy flow
	 * @param p pakiet dla ktorego trzeba stworzyc flow
	 * @return zwraca nowopowstaly flow
	 */
	public Flow createNewFlow(Packet p){
		Flow flow = new Flow (p.sourceNode);
		flows.put(p.sourceNode.getId(), flow);
		return flow;
	}
	
	private void calculateAvg(boolean departingPacket){
		if(q!=0 || departingPacket){
			avg = (long) ((1-Constans.w_q)*avg + Constans.w_q*q);
		} else{
			//tutaj nie wiem jeszcze co wpisac
		}
		if (Nactive!=0){
			avgcq = avg/Nactive;
		} else{
			avgcq = avg;
		}
		avgcq= Math.max(avgcq, 1);
		
		if(q==0 && departingPacket){
			//tutaj nie wiem jeszcze co wpisac
		}
	}
	
	private void dropPacket (Packet pckt){
		
	}
	
	private void acceptPacket(Packet p){
		
	}
	
	/**moja funkcja do liczenia jednego gowienka, trzeba obgadac z gerim
	 * @param p_a p_a
	 * @return 
	 */
	private boolean calculateDropProbability (double p_a){
		double random = Math.random();
		if (p_a> random) return true;
		else return false;
		
	}
	
	public void enquePacket(Packet pckt) {
		// sprawdza czy flow do ktorego nalezy pakiet istnieje w mapie flows_ i jesli nie to tworzy nowy flow
		int packetId = pckt.sourceNode.getId();
		Flow packetFlow = new Flow();
		if(flows.containsKey(packetId)){
			packetFlow = flows.get(packetId);
		} else{
			packetFlow = createNewFlow(pckt);
		}
		
		if (q==0){calculateAvg(false);}
		
		max_q=Constans.min_th;
		
		if(avg>=Constans.max_th){
			max_q=2;
		}
		
		//identify and manage non-adaptive flows:
		if((packetFlow.qlen_i >= max_q) || (avg >= Constans.max_th && packetFlow.qlen_i > 2*avgcq) || (packetFlow.qlen_i >= avgcq && packetFlow.strike_i > 1)){
			packetFlow.strike_i++;
			dropPacket(pckt);
			return;
		}
		
		//operate in random drop mode
		if(Constans.min_th <= avg && avg < Constans.max_th){
			count++;
			
			//only random drop from robust flows:
			if(packetFlow.qlen_i >= Math.max(Constans.min_q, avgcq)){
				//calculate probability p_a:
				double p_b = Constans.max_p*(avg-Constans.min_th)/(Constans.max_th-Constans.min_th);
				double p_a = p_b/(1 - count*p_b);
				if (calculateDropProbability(p_a)){
					dropPacket(pckt);
					count = 0;
					return;
					}
			}
			
		} else if (avg < Constans.min_th){
			//no drop mode
			count = -1; //?
		} else{
			//drop-tail mode
			count = 0;
			dropPacket(pckt);
			return;
		}
		if (packetFlow.qlen_i == 0){
			Nactive++;
		}
		calculateAvg(false);
		acceptPacket(pckt);
	}
}
