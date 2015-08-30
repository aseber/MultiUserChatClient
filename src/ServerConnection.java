import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerConnection {
	
	public static boolean connect(String serverAddress, String serverName, int serverPort) {

		try {

			ClientBase.socket = new Socket(serverAddress, serverPort);
			ClientBase.socket.setSoTimeout(5000);

		}

		catch (IOException e) {

			ClientBase.socket = null; // Might cause issues whilst currently being in a server and losing connection to a new one. Maybe?
			return false;

		}

		ClientBase.in = new InputCommunications(ClientBase.socket);
		ClientBase.in.start();

		try {

			ClientBase.out = new DataOutputStream(ClientBase.socket.getOutputStream());

		} catch (IOException e) {

			e.printStackTrace();

		}
		
		return true;
		
	}
	
}
