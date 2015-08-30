public class HeartbeatMonitor extends Thread {
	
	Boolean running = false;
	
	public HeartbeatMonitor() {
		
		super("Heartbeat Monitor");
		
	}
	
	public void run() {
		
		running = false;
		
		while (running) {
			
			try {
				
				this.sleep(2500);
			
			} catch (InterruptedException e) {}
			
			if (running) {
				
				new Packet09().write();
	
			}
			
		}
			
	}
	
	public void stopThread() {
		
		running = false;
		this.interrupt();
		
	}
	
}
