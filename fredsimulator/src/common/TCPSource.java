package common;

import java.util.TreeMap;

public class TCPSource extends Node {

	public TCPSource(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(long time) {
		// TODO Auto-generated method stub
		
	}
	
	int windowSize;
	
	/**
	 * mapa przechowujaca informacje o wyslanych i nie potwierdzonych pakietach
	 */
	TreeMap <Long, Long > window;
	
	
	/** metoda wywolywana przez klase Sink aby poinformowac o tym ze pakiet dotarl
	 * @param sn sequence number pakietu
	 */
	public void handleAck (long sn){
		
	}
}
