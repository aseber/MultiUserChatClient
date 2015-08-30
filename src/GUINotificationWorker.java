import java.awt.Color;

import javax.swing.JTextField;


public class GUINotificationWorker extends Thread {
	
	private JTextField field;
	private String message = "";
	private int fadeInMillis = 0;
	private int waitTimeMillis = 0;
	private int fadeOutMillis = 0;
	
	public GUINotificationWorker(JTextField notificationTextField, String message, int fadeInMillis, int waitTimeMillis ,int fadeOutMillis) {
		
		super.setName("GUINotificationWorker");
		this.field = notificationTextField;
		this.message = message;
		this.fadeInMillis = fadeInMillis;
		this.waitTimeMillis = waitTimeMillis;
		this.fadeOutMillis = fadeOutMillis;
		
	}

	@Override
	public void run() {
		
		while (true) {
		
			this.field.setForeground(new Color(255, 0, 0, 0));
			this.field.setText(this.message);
			
			try {
			
				for(int i = 0; i  <= 255; i++) {
					
					this.field.setForeground(new Color(255, 0, 0, i));
					sleep(this.fadeInMillis);	
			
				}
			
				sleep(this.waitTimeMillis);
			
				for(int i = 255; i >= 0; i--) {
					
					this.field.setForeground(new Color(255, 0, 0, i));
					sleep(this.fadeOutMillis);
					
				}
				
			}
			
			catch (InterruptedException e) {
				
				break;
				
			}
		
			break;
			
		}
			
		this.field.setText("");
		
	}
	
}
