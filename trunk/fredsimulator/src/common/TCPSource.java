package common;


import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author czarek
 *
 */

public class TCPSource extends Node {
	
	int windowSize;
	
	Long sequenceNumber;

	/**
	 * mapa przechowujaca informacje o wyslanych i nie potwierdzonych pakietach
	 * key - seqnum
	 * val - time
	 */
	TreeMap <Long, Long > sentPackets;
	
	/**
	 * mapa przechowujaca informacje o potwierdzonych pakietach
	 * p
	*/
	TreeSet <Long> ackedPackets;
	
	public TCPSource(int id) {
		super(id);
		sentPackets = new TreeMap<Long, Long>();
		ackedPackets = new TreeSet<Long>();	
		sequenceNumber = (long)0;
		windowSize = Constans.tcp_window;
	}

	public void handle(long time) {
		for (Iterator<Long> iterator = sentPackets.keySet().iterator(); iterator.hasNext();) {
			long seq = (Long) iterator.next();
			if(  sentPackets.get(seq) < time )
			{
				System.out.println(this + "handle slowdown");
				slowDown();
				return;
			}			
		}
		
		if(sentPackets.size() + ackedPackets.size() < windowSize)
		{//jezeli jeszcze nie przekroczono okna
			sendPacket();
		}
		
		
	}
	
	private void sendPacket() {
		
		if(!links.first().isBusy())
		{//jezeli link jest wolny
			TCPPacket packet = new TCPPacket(this, Constans.tcp_packet_size,sequenceNumber++);
			System.out.println(this + " sendPacket " + " sentPackets " + sentPackets + " ackedPackets " + ackedPackets + Timer.getTime());
			try {
				links.first().placeInLink(packet);
				sentPackets.put(packet.sequenceNumber,(long) Timer.getTime() + Constans.rtt * 2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	private void slowDown() {
		windowSize = (2 > windowSize/2 ? 2: windowSize/2 );
		try{
			sequenceNumber = sentPackets.firstKey();
		}
		catch (Exception e) {
			System.out.println("sequenceNumber " + sequenceNumber);
			e.printStackTrace();
		}
	}

	/** metoda wywolywana przez klase Sink aby poinformowac o tym ze pakiet dotarl
	 * @param sn sequence number pakietu
	 */
	public void handleAck (long sn){
		if(sentPackets.remove(sn) != null)
		{//jezeli pakiet jest w sentPackets to nie minal timeout
			ackedPackets.add(sn);
		}					
	}
}
