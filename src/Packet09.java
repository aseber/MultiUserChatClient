import java.io.IOException;

public class Packet09 {

	static private final byte ID = 9;
	
	public Packet09 () {}
	
	public void read() {
		
		HeartbeatMonitor monitor = new HeartbeatMonitor();
		monitor.start();
		
	}
	
	public void write() {
		
		try {
			
			ClientBase.out.writeByte(ID);
			ClientBase.out.flush();
		
		} catch (IOException e) {
		
			CommunicationThreadHandler.lostConnectionToServer();
		
		}
		
		System.out.println("Heartbeat");
		
	}
	
	public byte getID() {
		
		return ID;
		
	}
	
}
