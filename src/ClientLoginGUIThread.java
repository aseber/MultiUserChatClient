
public class ClientLoginGUIThread implements Runnable {

	@Override
	public void run() {
		
		ClientBase.gui.createClientLoginGUI();
		
	}

}
