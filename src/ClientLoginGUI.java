import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Thu Jun 14 15:35:13 MDT 2012
 */



/**
 * @author Austin Seber
 */
public class ClientLoginGUI extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private GUINotificationWorker worker;
	
	public ClientLoginGUI(Frame owner) {
	
		super(owner);
		initComponents();
	
	}

	public ClientLoginGUI(Dialog owner) {
		
		super(owner);
		initComponents();
	
	}
	
	public void displayErrorMessage(String message) {
		
		closeNotificationThread();
		this.worker = new GUINotificationWorker(this.notificationTextField, message, 5, 500, 30);
		this.worker.start();
		
	} 
	
	@Override
	public void dispose() {
		
		closeNotificationThread();
		ClientBase.clientLoginGUI = null;
		super.dispose();
		
	}
	
	public void closeNotificationThread() {
		
		try {
			
			this.worker.interrupt();
		
		}
		
		catch (NullPointerException e3) {}
		
	}
	
	private void loginButtonMouseClicked() {
		
		while (true) {
			
			String username = usernameTextField.getText();
			String password = new String(passwordTextField.getPassword());
			
			if (username.equals("")) {
			
				displayErrorMessage("Please fill in all fields.");
				usernameTextField.requestFocusInWindow();
				break;
				
			}
			
			else {
				
				new Packet03().write(username, password);
				password = new String();
				
			}

			break;
			
		}
	}

	private void cancelButtonMouseClicked() {
		
		CommunicationThreadHandler.quitServer();
		
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Austin Seber
		clientLoginGUI = new JPanel();
		contentPanel = new JPanel();
		usernameTextField = new JTextField();
		passwordTextField = new JPasswordField();
		saveLoginInformationCheckBox = new JCheckBox();
		buttonBar = new JPanel();
		notificationTextField = new JTextField();
		loginButton = new JButton();
		cancelButton = new JButton();

		//======== this ========
		setTitle("Login to server");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== clientLoginGUI ========
		{
			clientLoginGUI.setBorder(Borders.DIALOG_BORDER);

			clientLoginGUI.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new FormLayout(
					"132dlu, $lcgap, 107dlu",
					"2*(default, $lgap), default"));

				//---- usernameTextField ----
				usernameTextField.setBorder(new TitledBorder(null, "Username", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
				usernameTextField.setBackground(new Color(240, 240, 240));
				usernameTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
				usernameTextField.setAction(null);
				contentPanel.add(usernameTextField, CC.xywh(1, 1, 3, 1));
				usernameTextField.addKeyListener(new KeyListener() {

					@Override
					public void keyPressed(KeyEvent key) {
					
						if (key.getKeyCode() == KeyEvent.VK_ENTER) {
						
							loginButtonMouseClicked();
					
						}
						
						if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
							
							cancelButtonMouseClicked();
					
						}
						
					}

					@Override
					public void keyReleased(KeyEvent arg0) {}

					@Override
					public void keyTyped(KeyEvent arg0) {}
					
					
					
				});

				//---- passwordTextField ----
				passwordTextField.setBorder(new TitledBorder("Password"));
				passwordTextField.setBackground(new Color(240, 240, 240));
				contentPanel.add(passwordTextField, CC.xywh(1, 3, 3, 1));
				passwordTextField.addKeyListener(new KeyListener() {

					@Override
					public void keyPressed(KeyEvent key) {
					
						if (key.getKeyCode() == KeyEvent.VK_ENTER) { 
						
							loginButtonMouseClicked();
					
						}
						
						if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
							
							cancelButtonMouseClicked();
							
						}
						
					}

					@Override
					public void keyReleased(KeyEvent arg0) {}

					@Override
					public void keyTyped(KeyEvent arg0) {}
					
					
					
				});
				
				//---- saveLoginInformationCheckBox ----
				saveLoginInformationCheckBox.setText("Save login information");
				saveLoginInformationCheckBox.setBorder(new LineBorder(new Color(0, 0, 0, 0), 2));
				contentPanel.add(saveLoginInformationCheckBox, CC.xy(3, 5));
			}
			clientLoginGUI.add(contentPanel, BorderLayout.NORTH);

			//======== buttonBar ========
			{
				buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				buttonBar.setLayout(new FormLayout(
					"$lcgap, default, $glue, $lcgap, default, $button, $rgap, $button",
					"pref"));

				//---- notificationTextField ----
				notificationTextField.setBackground(new Color(240, 240, 240));
				notificationTextField.setBorder(new LineBorder(new Color(0, 0, 0, 0), 2));
				notificationTextField.setEditable(false);
				buttonBar.add(notificationTextField, CC.xywh(2, 1, 3, 1));

				//---- loginButton ----
				loginButton.setText("Login");
				loginButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						loginButtonMouseClicked();
						
					}
					
				});
				buttonBar.add(loginButton, CC.xy(6, 1));

				//---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						cancelButtonMouseClicked();
						
					}
					
				});
				buttonBar.add(cancelButton, CC.xy(8, 1));
			}
			clientLoginGUI.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(clientLoginGUI, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		loginButton.setFocusable(false);
		//loginButton.setMnemonic(KeyEvent.VK_ENTER);
		cancelButton.setFocusable(false);
		//cancelButton.setMnemonic(KeyEvent.VK_ESCAPE);
		
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Austin Seber
	private JPanel clientLoginGUI;
	private JPanel contentPanel;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JCheckBox saveLoginInformationCheckBox;
	private JPanel buttonBar;
	private JTextField notificationTextField;
	private JButton loginButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
