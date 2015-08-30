public class Packet06 {

	static private final byte ID = 6;
	
	public Packet06 () {}
	
	public void read() {
		
		ClientBase.gui.removeUserFromList(new User(ClientBase.in.readUTF(), new Permission(ClientBase.in.readByte())));
		
	}
	
	public byte getID() {
		
		return ID;
		
	}
	
}
