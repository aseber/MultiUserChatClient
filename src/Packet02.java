public class Packet02 {

	static private final byte ID = 2;
	
	public Packet02 () {}
	
	public void read() {
		
		boolean accepted = ClientBase.in.readBoolean();
		
		if (accepted) {
			
			ClientBase.clientConnectionGUI.dispose();
			ClientBase.gui.purgeUserList();
			new Thread(new ClientLoginGUIThread(), "ClientLoginGUI Thread").start();
			ClientBase.gui.setTitle(ClientGUI.DEFAULT_CLIENT_TITLE + " - " + ClientBase.in.readUTF());
			
		}
		
		else {
			
			ClientBase.clientConnectionGUI.displayErrorMessage("Server and client versions do not match.");
			CommunicationThreadHandler.quitServer();
			
		}
		
	}
	
	public byte getID() {
		
		return ID;
		
	}
	
}
