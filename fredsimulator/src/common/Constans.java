/**
 * 
 */
package common;

/**
 * @author Malina
 *
 */
public class Constans {
	
	/**
	 * rozdzielczosc zegara, oznacza ile jednostek zegara to 1 sekunda
	 */
	public static final double second = 1000*1000; 

	public static final double w_q = 0.02; // w jednostkach
	
	public static final int buffer_size = 32; //w pakietach
	
	public static final int rtt = (int) (second / 25) ; //w us
	
	public static final long min_th = Math.min(rtt/1000, buffer_size); // w pakietach
	
	public static final long max_th = 2* min_th; // w pakietach
	
	public static final double max_p = 0.02; //w jednostkach
	
	public static final int min_q = 4; // w pakietach
	
	public static final int tcp_packet_size = 1500; //w bajtach
	
	public static final int udp_packet_size = 1500; //w bajtach
	
	/**
	 * poczatkowe okno w tcp, liczone jest w pakietach
	 * 
	 */
	public static final int tcp_window = (int) 65536 / 1500;

	public static final double linkDelayVariation = 0.0025 ;
}
