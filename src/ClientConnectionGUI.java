import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Wed Jun 13 11:46:29 MDT 2012
 */



/**
 * @author Austin Seber
 */
public class ClientConnectionGUI extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private GUINotificationWorker worker;
	
	public ClientConnectionGUI(Frame owner) {
	
		super(owner);
		initComponents();
	
	}

	public ClientConnectionGUI(Dialog owner) {
		
		super(owner);
		initComponents();
	
	}

	public void displayErrorMessage(String message) {
		
		closeNotificationThread();
		this.worker = new GUINotificationWorker(notificationTextField, message, 5, 500, 30);
		this.worker.start();
		
	}
	
	@Override
	public void dispose() {
		
		closeNotificationThread();
		ClientBase.clientConnectionGUI = null;
		super.dispose();
		
	}
	
	public void closeNotificationThread() {
		
		try {
			
			this.worker.interrupt();
		
		}
		
		catch (NullPointerException e) {}
		
	}
	
	private void connectButtonMouseClicked() {

		while (true) {

			String serverAddress, serverName;
			int serverPort;

			serverAddress = serverAddressTextField.getText();
			
			if (serverAddress.equals("")) {

				this.displayErrorMessage("Server address field is empty.");
				break;

			}
			
			serverName = serverNameTextField.getText();

			try {

				serverPort = Integer.valueOf(serverPortTextField.getText()).intValue();

			}

			catch (NumberFormatException e2) {

				this.displayErrorMessage("Invalid port.");
				break;

			}
			
			if (ServerConnection.connect(serverAddress, serverName, serverPort)) {

				CommunicationThreadHandler.connectedToServer();
				new Packet01().write("Chat Program; Version: " + ClientBase.VERSION);
				HeartbeatMonitor monitor = new HeartbeatMonitor();
				monitor.start();
				ClientBase.heartbeatMonitor = monitor;
				break;

			}

			else {

				this.displayErrorMessage("Could not connect to \"" + serverAddress + "\" on port " + serverPort + ".");
				serverAddressTextField.requestFocusInWindow();
				break;

			}	

		}
		
	}

	private void cancelButtonMouseClicked() {

		CommunicationThreadHandler.quitServer();
		
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Austin Seber
		clientConnectionGUI = new JPanel();
		contentPanel = new JPanel();
		serverAddressTextField = new JTextField();
		serverNameTextField = new JTextField();
		serverPortTextField = new JTextField();
		saveServerInformationCheckButton = new JCheckBox();
		buttonBar = new JPanel();
		notificationTextField = new JTextField();
		connectButton = new JButton();
		cancelButton = new JButton();

		//======== this ========
		setTitle("Connect to a server");
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== clientConnectionGUI ========
		{
			clientConnectionGUI.setBorder(Borders.DIALOG_BORDER);

			clientConnectionGUI.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new FormLayout(
					"135dlu, $lcgap, 106dlu",
					"2*(default, $lgap), default"));

				//---- serverAddressTextField ----
				serverAddressTextField.setBorder(new TitledBorder(null, "Server address", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
				serverAddressTextField.setBackground(new Color(240, 240, 240));
				serverAddressTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
				serverAddressTextField.setText("localhost");
				contentPanel.add(serverAddressTextField, CC.xywh(1, 1, 3, 1));
				serverAddressTextField.addKeyListener(new KeyListener() {
					
					@Override
					public void keyPressed(KeyEvent key) {
						
						if (key.getKeyCode() == KeyEvent.VK_ENTER) {
							
							connectButtonMouseClicked();
							
						}
						
						if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
							
							cancelButtonMouseClicked();
							
						}
						
					}

					@Override
					public void keyReleased(KeyEvent e) {}

					@Override
					public void keyTyped(KeyEvent e) {}
					
				});

				//---- serverNameTextField ----
				serverNameTextField.setBorder(new TitledBorder(null, "Server name", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
				serverNameTextField.setBackground(new Color(240, 240, 240));
				serverNameTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
				contentPanel.add(serverNameTextField, CC.xy(1, 3));

				//---- serverPortTextField ----
				serverPortTextField.setBorder(new TitledBorder(null, "Server port", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
				serverPortTextField.setBackground(new Color(240, 240, 240));
				serverPortTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
				serverPortTextField.setText("25566");
				contentPanel.add(serverPortTextField, CC.xy(3, 3));
				serverPortTextField.addKeyListener(new KeyListener() {
					
					@Override
					public void keyPressed(KeyEvent key) {
						
						if (key.getKeyCode() == KeyEvent.VK_ENTER) {
							
							connectButtonMouseClicked();
							
						}
						
						if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
							
							cancelButtonMouseClicked();
							
						}
						
					}

					@Override
					public void keyReleased(KeyEvent e) {}

					@Override
					public void keyTyped(KeyEvent e) {}
					
				});

				//---- saveServerInformationCheckButton ----
				saveServerInformationCheckButton.setText("Save server information");
				saveServerInformationCheckButton.setBorder(new LineBorder(new Color(0, 0, 0, 0), 2));
				contentPanel.add(saveServerInformationCheckButton, CC.xy(3, 5));
			}
			clientConnectionGUI.add(contentPanel, BorderLayout.WEST);

			//======== buttonBar ========
			{
				buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				buttonBar.setLayout(new FormLayout(
					"$lcgap, 133dlu, $lcgap, $button, $rgap, $button",
					"pref"));

				//---- notificationTextField ----
				notificationTextField.setBorder(new LineBorder(new Color(0, 0, 0, 0), 2));
				notificationTextField.setBackground(new Color(240, 240, 240));
				notificationTextField.setEditable(false);
				buttonBar.add(notificationTextField, CC.xy(2, 1));

				//---- connectButton ----
				connectButton.setText("Connect");
				connectButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						connectButtonMouseClicked();
						
					}
					
				});
				buttonBar.add(connectButton, CC.xy(4, 1));

				//---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						cancelButtonMouseClicked();
						
					}
					
				});
				buttonBar.add(cancelButton, CC.xy(6, 1));
			}
			clientConnectionGUI.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(clientConnectionGUI, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents

		serverNameTextField.setEnabled(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		connectButton.setFocusable(false);
		//connectButton.setMnemonic(KeyEvent.VK_ENTER); //Make the enter key connect to the server as if you were pressing it.
		cancelButton.setFocusable(false);
		//cancelButton.setMnemonic(KeyEvent.VK_ESCAPE); //Make the escape key quit from the menu as if you were pressing it.
		
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Austin Seber
	private JPanel clientConnectionGUI;
	private JPanel contentPanel;
	private JTextField serverAddressTextField;
	private JTextField serverNameTextField;
	private JTextField serverPortTextField;
	private JCheckBox saveServerInformationCheckButton;
	private JPanel buttonBar;
	private JTextField notificationTextField;
	private JButton connectButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
