import java.io.IOException;

public class Packet07 {

	static private final byte ID = 7;
	
	public Packet07 () {}
	
	public void read() {
		
		ClientBase.gui.printMessage(ClientBase.in.readUTF());
		
	}
	
	public void write(String message) {
		
		try {
			
			ClientBase.out.writeByte(ID);
			ClientBase.out.writeUTF(message);
			ClientBase.out.flush();
			
		} catch (IOException e) {
		
			CommunicationThreadHandler.lostConnectionToServer();
		
		}
	
	}
	
	public byte getID() {
		
		return ID;
		
	}
	
}
