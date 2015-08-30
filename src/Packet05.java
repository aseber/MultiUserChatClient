public class Packet05 {

	static private final byte ID = 5;
	
	public Packet05 () {}
	
	public void read() {
		
		ClientBase.gui.addUserToList(new User(ClientBase.in.readUTF(), new Permission(ClientBase.in.readByte())));
		
	}
	
	public byte getID() {
		
		return ID;
		
	}
	
}
