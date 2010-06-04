package common;


import java.util.TreeMap;

/**
 * @author czarek
 *
 */

public class TCPSource extends Node {

	
	
	int windowSize;
	
	/**
	 * mapa przechowujaca informacje o wyslanych i nie potwierdzonych pakietach
	 * kye - sn
	 * cal - time
	 */
	TreeMap <Long, Long > window;
	
	public TCPSource(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public void handle(long time) {
		// TODO Auto-generated method stub
		
	}
	
	/** metoda wywolywana przez klase Sink aby poinformowac o tym ze pakiet dotarl
	 * @param sn sequence number pakietu
	 */
	public void handleAck (long sn){
		window.remove(sn);
		
	}
}
