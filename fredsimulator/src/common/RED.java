package common;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * @author Malina
 *
 */
public class RED extends Node {

	int q; //current queue size
	long time; // current "real" time
	double avg; // average queue size
	int count; //number of packets since last drop
	double avgcq; //average per-flow queue size
	long q_time;
	Link outLink; // link ktory wychodzi z wezla fred
	//Timer timer;
	

	LinkedList<Packet> buffer;
		
	public RED(int id) {
		super(id);
		q=0;
		avg=0;
		count=0;
		avgcq=0;
	
		q_time=0;
		outLink=null;
		buffer = new LinkedList<Packet>();
	}

	@Override
	public void handle(long time) {
		if(outLink == null){
			Link temp = links.first();
			boolean while_flag = true;
			while(while_flag && temp!=null){
				if (temp.getSource().equals(this)){
					while_flag=false;
					outLink=temp;
				} else{
					temp = links.higher(temp);
				}
			}
			if (temp == null){System.out.println("cos tu nie gra....");}			
		}
	//	if(Timer.getTime()% modulo == 0 && !outLink.isBusy() && !buffer.isEmpty()){
		if(!outLink.isBusy() && !buffer.isEmpty()){
			Packet departingPacket = buffer.poll();
			q--;
//			departPacket(departingPacket);

				System.out.println(this + " sendPacket " + Timer.getTime());
				try {
					this.outLink.placeInLink(departingPacket);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
	}
/*	
	private void departPacket(Packet pckt){
		calculateAvg(true);
		int packetId = pckt.sourceNode.getId();
		Flow packetFlow = flows.get(packetId);
		packetFlow.qlen_i--;
		try {
			if (packetFlow.qlen_i == 0){
				Nactive--;
				flows.remove(packetId);		
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/	

	public int getConnectionIdFromPacket(Packet P){
		return P.sourceNode.getId();
	}
	
	//public long getTime(){
		//return Timer.getTime();
	//}

	
	private void calculateAvg(){
		avg = ((1-Constans.w_q)*avg + Constans.w_q*q);
	}
	
	private void dropPacket (Packet pckt){
		calculateAvg();
			int i = 0;
			i++;

	}
	
	private void acceptPacket(Packet pckt){
		buffer.add(pckt);
		//packetFlow = 
		int packetId = pckt.sourceNode.getId();
		q++;
		q += 0;
	}
	
	private int fTime(){
		long timeDifference = Timer.getTime()-q_time;
		int result = (int) (timeDifference/Constans.second*outLink.getBitrate()/Constans.udp_packet_size);
		return result;
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
	/*
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
		if((packetFlow.qlen_i >= max_q) || (avg >= Constans.max_th && packetFlow.qlen_i > 2*avgcq) || (packetFlow.qlen_i >= avgcq  && packetFlow.strike_i > 1)){
			packetFlow.strike_i++;
			dropPacket(pckt);
			return;
		}
	*/	
		//operate in random drop mode
		if(Constans.min_th <= avg && avg < Constans.max_th){
			count++;
			
			//only random drop from robust flows:
		//	if(packetFlow.qlen_i >= Math.max(Constans.min_q, avgcq))
			{
				//calculate probability p_a:
				double p_b = (Constans.max_p*(avg-Constans.min_th))/(Constans.max_th-Constans.min_th);
				double p_a = p_b/(1 - count*p_b);
				if(p_a < 0) 
					p_a=1;
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
		
		if(q>= Constans.buffer_size)
		{
			//drop-tail mode
			count = 0;
			dropPacket(pckt);
			return;
		}
		
		calculateAvg();
		acceptPacket(pckt);
	}
}
