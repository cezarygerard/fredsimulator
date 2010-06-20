package common;

public class UDPSource extends Node {

	private long timeToStart;

	public UDPSource(int id, long bitrate) {
		super(id);
		modulo = (int) ((Constans.udp_packet_size * 8 * Constans.second)/bitrate);
		this.timeToStart = (long)( Math.random()*Constans.timeToStart);
		this.name = "UDPSource";
	}

	public void handle(long time) {
		if(time > this.timeToStart)
		{
			if(Timer.getTime()% modulo == 0){
				UDPPacket packet = new UDPPacket(this, Constans.udp_packet_size);
				if(!links.first().isBusy())
				{//jezeli link jest wolny
					System.out.println(this + " sendPacket");
					try {
						links.first().placeInLink(packet);
					} catch (Exception e) {
						e.printStackTrace();
					}
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
