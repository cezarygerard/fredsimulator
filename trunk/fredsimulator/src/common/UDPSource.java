package common;

public class UDPSource extends Node {

	public UDPSource(int id, long bitrate) {
		super(id);
		modulo = (int) ((Constans.udp_packet_size * 8 * Constans.second)  / bitrate) ;
		int i = 0;
	}

	@Override
	public void handle(long time) {
		if(Timer.getTime()% modulo == 0){
			UDPPacket packet = new UDPPacket(this, Constans.udp_packet_size);
			if(!links.first().isBusy())
			{//jezeli link jest wolny
				try {
					links.first().placeInLink(packet);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	
	}
	
	/**
	 * w bit/s
	 */
	long bitrate;
	
	int modulo;
}
