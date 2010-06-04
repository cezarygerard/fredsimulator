package common;

public class UDPSource extends Node {

	public UDPSource(int id, long bitrate) {
		super(id);
		modulo = (int) (Constans.udp_packet_size * 8 / bitrate * Constans.second);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(long time) {
		if(Timer.getTime()% modulo == 0){
			UDPPacket packet = new UDPPacket(this, Constans.udp_packet_size);
			links.first().placeInLink(packet);
		}
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * w bit/s
	 */
	long bitrate;
	
	int modulo;
}
