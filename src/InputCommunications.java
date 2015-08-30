import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class InputCommunications extends Thread {

	private DataInputStream serverInput;
	private Socket socket;
	private boolean running = true;
	
	@Override
	public void run() {
		
		byte packetID;
		
		while (running) {
			
			packetID = 0;
			
			try {
				
				packetID = serverInput.readByte();
				
			} catch (EOFException e) { //Under some circumstances, the EOF exception will not be found and the Input thread will not shut down.
				
				CommunicationThreadHandler.lostConnectionToServer();
				
			} catch (IOException e) {}
			
			switch (packetID) {
			
				case 1:
					break;
					
				case 2:
					new Packet02().read();
					break;
					
				case 3:
					break;
					
				case 4:
					new Packet04().read();
					break;
					
				case 5:
					new Packet05().read();
					break;
					
				case 6:
					new Packet06().read();
					break;
					
				case 7:
					new Packet07().read();
					break;
					
				case 8:
					new Packet08().read();
					break;
					
				case 9:
					break;
					
				case 10:
					new Packet10().read();
					
				default:
					break;
			
			}
			
		}
		
	}

	public InputCommunications(Socket socket) {
		
		setName("InputCommunications Thread");
		this.socket = socket;
		
		try {
			
			serverInput = new DataInputStream(this.socket.getInputStream());
		
		} catch (IOException e) {

			e.printStackTrace();
		
		}
		
	}
	
	public void closeThread() {
		
		try {
			
			serverInput.close();
		
		} catch (IOException e) {}
		
		running = false;
		ClientBase.in = null;
		
	}
	
	public byte readByte() {
		
		try {
		
			return serverInput.readByte();
		
		} catch (IOException e) {
			
			CommunicationThreadHandler.lostConnectionToServer();
		
		}
		
		return 0;
		
	}
	
	public int readInt() {
		
		try {
			
			return serverInput.readInt();
			
		} catch (IOException e) {
				
			CommunicationThreadHandler.lostConnectionToServer();
			
		}
		
		return 0;
		
	}
	
	public String readUTF() {
		
		try {
			
			return serverInput.readUTF();
			
		} catch (IOException e) {
				
			CommunicationThreadHandler.lostConnectionToServer();
			
		}
		
		return "\u0000";
		
	}
	
	public boolean readBoolean() {
		
		try {
			
			return serverInput.readBoolean();
			
		} catch (IOException e) {
				
			CommunicationThreadHandler.lostConnectionToServer();
			
		}
		
		return false;
		
	}
	
	public void verifyIntegrity() {
		
		new Packet09().write();
		
	}
	
}
