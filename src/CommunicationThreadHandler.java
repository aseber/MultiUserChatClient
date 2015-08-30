import java.io.IOException;

public class CommunicationThreadHandler {

	private static boolean isConnectedToServer = false;
	
	public static void lostConnectionToServer() {
		
		if (isConnectedToServer) { // Might not need this
		
			System.out.println("Lost connection to server. Closing communication threads."); // Make sure this works as intended when you try to cancel joining a new server if you are already in one
			cleanup();
			ClientBase.gui.printError("Lost connection to current server."); // Add in disabling of chat functions
			
		}
		
	}
	
	public static void quitServer() {
		
		if (isConnectedToServer) { // Might not need this
			
			System.out.println("Logging out of current server. Closing communication threads.");
			cleanup();
			ClientBase.gui.printError("Logging out of current server."); // Make that message not show up when the user exits the Connections GUI without actually connecting to a server.
			
		}
			
	}
	
	public static void connectedToServer() {
		
		isConnectedToServer = true;
		
	}
	
	private static void cleanup() {
		
		try {ClientBase.in.closeThread();} catch (NullPointerException e) {}
		try {ClientBase.clientConnectionGUI.dispose();} catch (NullPointerException e) {}
		try {ClientBase.clientLoginGUI.dispose();} catch (NullPointerException e) {}
		try {ClientBase.out.close();} catch (IOException e) {} catch (NullPointerException e) {}
		try {ClientBase.socket.close();} catch (IOException e) {} catch (NullPointerException e) {}
		try {ClientBase.heartbeatMonitor.stopThread();} catch (NullPointerException e) {}
		ClientBase.gui.setchatFunctionsEnabled(false);
		ClientBase.out = null;
		isConnectedToServer = false;
		ClientBase.heartbeatMonitor = null;
		ClientBase.gui.setTitle(ClientGUI.DEFAULT_CLIENT_TITLE);
		
	}
	
}
