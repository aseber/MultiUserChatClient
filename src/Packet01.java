import java.io.IOException;

public class Packet01 {

	static private final byte ID = 1;
		
	public Packet01 () {}
	
	public void write(String handshake) {
			
		try {
			
			ClientBase.out.writeByte(ID);
			ClientBase.out.writeUTF(handshake);
			ClientBase.out.flush();
			
		} catch (IOException e) {
		
			CommunicationThreadHandler.lostConnectionToServer();
		
		}
		
	}
	
	public byte getID() {
		
		return ID;
		
	}
	
}
