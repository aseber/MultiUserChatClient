import java.io.DataOutputStream;
import java.net.Socket;

public class ClientBase {
	
	static ClientGUI gui;
	static InputCommunications in;
	static DataOutputStream out;
	static ClientConnectionGUI clientConnectionGUI;
	static ClientLoginGUI clientLoginGUI;
	static Socket socket;
	static HeartbeatMonitor heartbeatMonitor;
	static final String VERSION = "3.25";
	
	public static void main(String[] args) {

		gui = new ClientGUI();
		gui.setVisible(true);
		gui.printCommand("Go to connections and click on \"Connect to server\" to join a server of your choice.");
		
	}

}
