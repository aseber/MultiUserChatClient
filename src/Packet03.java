import java.io.IOException;

public class Packet03 {

	static private final byte ID = 3;
	
	public Packet03 () {}
	
	
	public void write(String username, String password) {
		
		try {
			
			ClientBase.out.writeByte(ID);
			ClientBase.out.writeUTF(username);
			ClientBase.out.writeUTF(password);
			ClientBase.out.flush();
			
		} catch (IOException e) {

			CommunicationThreadHandler.lostConnectionToServer();

		}
		
	}
	
	public byte getID() {
		
		return ID;
		
	}
	
}
