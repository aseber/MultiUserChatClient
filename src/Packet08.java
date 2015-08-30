public class Packet08 {

	static private final byte ID = 8;
	static private final byte USER_LOGGED_ON = 1;
	static private final byte USER_LOGGED_OFF = 2;
	byte loginType;
	
	public Packet08 () {}
	
	public void read() {
		
		loginType = ClientBase.in.readByte();
		
		if (loginType == USER_LOGGED_ON) {
			
			ClientBase.gui.printCommand("\"" + ClientBase.in.readUTF() + "\" has connected to the server.");
			
		} else if (loginType == USER_LOGGED_OFF) {
			
			ClientBase.gui.printError("\"" + ClientBase.in.readUTF() + "\" has disconnected from the server.");
			
		} else {
			
			System.out.println("Malformed Packet08 detected");
			
		}
		
	}
	
	public byte getID() {
		
		return ID;
		
	}
	
}
