public class Packet10 {

	static private final byte ID = 10;
	
	public Packet10 () {}
	
	public void read() {
		
		ClientBase.gui.printError(ClientBase.in.readUTF());
		
	}
	
	public void write() {} // Might end up using later. (Powershell like command line interface?)
	
	public byte getID() {
		
		return ID;
		
	}
	
}