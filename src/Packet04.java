public class Packet04 {

	static private final byte ID = 4;
	
	public Packet04 () {}
	
	public void read() {
		
		boolean accepted = ClientBase.in.readBoolean();
		String message = ClientBase.in.readUTF();
		
		if (accepted) {
			
			ClientBase.gui.printCommand(message);
			ClientBase.clientLoginGUI.dispose();
			ClientBase.gui.setchatFunctionsEnabled(true);
			
		} else {
			
			ClientBase.clientLoginGUI.displayErrorMessage(message);
			
		}
		
	}
	
	public byte getID() {
		
		return ID;
		
	}
	
}
