/**
 * 
 */
package common;

/**
 * @author Malina
 *
 */
public class Constans {

	public static final double w_q = 0.02; // w jednostkach
	
	public static final int buffer_size = 32; //w pakietach
	
	public static final int rtt = 40000; //w us
	
	public static final double min_th = Math.min(rtt/1000, buffer_size); // w pakietach
	
	public static final double max_th = 2* min_th; // w pakietach
	
	public static final double max_p = 0.02; //w jednostkach
	
	public static final int min_q = 4; // w pakietach
	
	public static final int tcp_packet_size = 1500; //w bajtach
	
	public static final int udp_packet_size = 1500; //w bajtach
}
