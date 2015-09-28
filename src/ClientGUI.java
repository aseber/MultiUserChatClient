import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

public class ClientGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final String DEFAULT_CLIENT_TITLE = "Chat Test - Version: " + ClientBase.VERSION;

	public ClientGUI() {

		initComponents();

	}

	public void createClientLoginGUI() {

		ClientBase.clientLoginGUI = new ClientLoginGUI(this);
		ClientBase.clientLoginGUI.setModalityType(ModalityType.APPLICATION_MODAL);
		ClientBase.clientLoginGUI.setVisible(true);

	}

	public void createClientConnectionGUI() {

		ClientBase.clientConnectionGUI = new ClientConnectionGUI(this);
		ClientBase.clientConnectionGUI.setModalityType(ModalityType.APPLICATION_MODAL);
		ClientBase.clientConnectionGUI.setVisible(true);

	}

	public void printMessage(String message) {

		chatDocument = chatWindow.getStyledDocument();
		/*JScrollBar bar = chatWindowScrollPane.getVerticalScrollBar();
		Boolean move = false;*/

		try {

			/*if ((bar.getValue() + bar.getVisibleAmount()) == bar.getMaximum()) {

				move = true;

			}*/

			chatDocument.insertString(chatDocument.getLength(), message + "\n", defaultSet);

			/*if (move) {

				synchronized (bar) {

					System.out.println((bar.getValue() + bar.getVisibleAmount()) + " | " + bar.getMaximum());
					chatWindowScrollPane.repaint();
					bar.setValue(bar.getMaximum() - bar.getVisibleAmount());
					move = false;

					//Appears that there is a race error wherein the scrollpane is setting itself while this
					//setValue() is being used too, which would lead to it never finishing the setValue() properly

				}

			}*/


		}

		catch (BadLocationException e) {

			e.printStackTrace();

		}

	}

	public void printCommand(String string) {

		chatDocument = chatWindow.getStyledDocument();

		try {

			chatDocument.insertString(chatDocument.getLength(), string + "\n", commandSet);

		}

		catch (BadLocationException e) {

			e.printStackTrace();

		}

	}

	public void printError(String string) {

		chatDocument = chatWindow.getStyledDocument();

		try {

			chatDocument.insertString(chatDocument.getLength(), string + "\n", errorSet);

		}

		catch (BadLocationException e) {

			e.printStackTrace();

		}

	}

	public void addUserToList(User user) {

		synchronized (model) {

			model.add(model.getSize(), user);

		}

	}

	public void removeUserFromList(User user) {

		synchronized (model) {

			for (int i = 0; i < model.getSize(); i++) {

				if (model.getElementAt(i).equals(user)) {

					model.remove(i);
					return;

				}

			}

			System.out.println("Could not find user to remove from user list.");

		}

	}

	public void removeUserFromList(int index) {

		synchronized (model) {

			model.remove(index);

		}

	}

	public void purgeUserList() {

		synchronized (model) {

			model.clear();

		}

	}

	public void setchatFunctionsEnabled(boolean enabled) {

		if (enabled) {

			messageInput.setEnabled(true);
			sendButton.setEnabled(true);
			disconnectFromServerMenuItem.setEnabled(true);
			return;

		} else {

			messageInput.setEnabled(false);
			sendButton.setEnabled(false);
			disconnectFromServerMenuItem.setEnabled(false);
			purgeUserList();
			return;

		}

	}

	private void messageInputFocusGained(FocusEvent e) {

		if (messageInput.getText().equals("Type message here...")) {

			 messageInput.setText("");
			 messageInput.setForeground(standardColor);

		 }

	}

	private void messageInputFocusLost(FocusEvent e) {

		if (messageInput.getText().equals("")) {

			messageInput.setText("Type message here...");
			messageInput.setForeground(shadedColor);

		}

	}

	private void messageInputKeyTyped(KeyEvent e) {

		if (e.getKeyChar() == KeyEvent.VK_ENTER) {

			if (!messageInput.getText().equals("")) {

				new Packet07().write(messageInput.getText());
				messageInput.setText("");

			}

		}

	}

	private void sendButtonMouseClicked(MouseEvent e) {

		if (!(messageInput.getText().equals("Type message here..."))) {

			new Packet07().write(messageInput.getText());
			messageInput.setText("Type message here...");

		}

	}

	private void connectToServerMenuItemActionPerformed(ActionEvent e) {

		createClientConnectionGUI();

	}

	private void disconnectFromServerMenuItemActionPerformed(ActionEvent e) {

		CommunicationThreadHandler.quitServer();

	}

	private void quitMenuItemActionPerformed(ActionEvent e) {

		CommunicationThreadHandler.quitServer();
		System.exit(1);

	}

	private void userListValueChanged(ListSelectionEvent e) {

		System.out.println(e);

	}

	private void initComponents() {

		standardColor = new Color(0, 0, 0);
		shadedColor = new Color(125, 125, 125);
		defaultSet = new SimpleAttributeSet();
		StyleConstants.setForeground(defaultSet, Color.black);
		commandSet = new SimpleAttributeSet();
		StyleConstants.setForeground(commandSet, Color.green);
		errorSet = new SimpleAttributeSet();
		StyleConstants.setForeground(errorSet, Color.red);
		model = new DefaultListModel<User>();


		menuBar = new JMenuBar();
		connectionsMenu = new JMenu();
		connectToServerMenuItem = new JMenuItem();
		disconnectFromServerMenuItem = new JMenuItem();
		quitMenuItem = new JMenuItem();
		bookmarksMenu = new JMenu();
		userListScrollPane = new JScrollPane();
		userList = new JList<User>(model);
		chatWindowScrollPane = new JScrollPane();
		chatWindow = new JTextPane();
		messageInput = new JTextField();
		sendButton = new JButton();

		addWindowListener(new WindowListener() {

			@Override
			public void windowClosing(WindowEvent arg0) {

				CommunicationThreadHandler.quitServer();
				System.exit(1);

			}

			@Override
			public void windowActivated(WindowEvent arg0) {}
			@Override
			public void windowClosed(WindowEvent arg0) {}
			@Override
			public void windowDeactivated(WindowEvent arg0) {}
			@Override
			public void windowDeiconified(WindowEvent arg0) {}
			@Override
			public void windowIconified(WindowEvent arg0) {}
			@Override
			public void windowOpened(WindowEvent arg0) {}

		});

		setLayout(new FormLayout(
			"5px, 200px:grow, 5px, 354px:grow, 5px, 75px, 5px",
			"25px, 5px, 339px:grow, 5px, 25px, 5px"));

		//======== menuBar ========
		{

			//======== connectionsMenu ========
			{
				connectionsMenu.setText("Connections");

				//---- connectToServerMenuItem ----
				connectToServerMenuItem.setText("Connect to a server");
				connectToServerMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						connectToServerMenuItemActionPerformed(e);
					}
				});
				connectionsMenu.add(connectToServerMenuItem);

				//---- disconnectFromServerMenuItem ----
				disconnectFromServerMenuItem.setText("Disconnect from current");
				disconnectFromServerMenuItem.setEnabled(false);
				disconnectFromServerMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						disconnectFromServerMenuItemActionPerformed(e);
					}
				});
				connectionsMenu.add(disconnectFromServerMenuItem);

				//---- quitMenuItem ----
				quitMenuItem.setText("Quit");
				quitMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						quitMenuItemActionPerformed(e);
					}
				});
				connectionsMenu.add(quitMenuItem);
			}
			menuBar.add(connectionsMenu);

			//======== bookmarksMenu ========
			{
				bookmarksMenu.setText("Bookmarks");
			}
			menuBar.add(bookmarksMenu);
		}
		add(menuBar, CC.xywh(1, 1, 7, 1, CC.FILL, CC.DEFAULT));

		//======== userListScrollPane ========
		{

			//---- userList ----
			userList.setAutoscrolls(false);
			userList.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					userListValueChanged(e);
				}
			});
			userListScrollPane.setViewportView(userList);
		}
		add(userListScrollPane, CC.xy(2, 3, CC.DEFAULT, CC.FILL));

		//======== chatWindowScrollPane ========
		{

			//---- chatWindow ----
			chatWindow.setEditable(false);
			chatWindowScrollPane.setViewportView(chatWindow);
			chatWindowScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

				@Override
				public void adjustmentValueChanged(AdjustmentEvent e) {

					JScrollBar scrollBar = chatWindowScrollPane.getVerticalScrollBar();

					scrollBar.setValue(scrollBar.getMaximum());
					/*if (((float) e.getValue() / (scrollBar.getMaximum() - scrollBar.getVisibleAmount() - 16)) == 1.0f) {

						scrollBar.setValue(scrollBar.getMaximum());

					}*/
				}


			});

		}
		add(chatWindowScrollPane, CC.xywh(4, 3, 3, 1, CC.DEFAULT, CC.FILL));

		//---- messageInput ----
		messageInput.setText("Type message here...");
		messageInput.setForeground(new Color(125, 125, 125));
		messageInput.setEnabled(false);
		messageInput.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				messageInputFocusGained(e);
			}
			@Override
			public void focusLost(FocusEvent e) {
				messageInputFocusLost(e);
			}
		});
		messageInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				messageInputKeyTyped(e);
			}
		});
		add(messageInput, CC.xywh(2, 5, 3, 1));

		//---- sendButton ----
		sendButton.setText("Send");
		sendButton.setEnabled(false);
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendButtonMouseClicked(e);
			}
		});
		add(sendButton, CC.xy(6, 5));

		userList.setCellRenderer(new CellRenderer());
		setTitle(DEFAULT_CLIENT_TITLE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocation(100, 100);
		pack();
		setMinimumSize(this.getSize());

	}

	private JMenuBar menuBar;
	private JMenu connectionsMenu;
	private JMenuItem connectToServerMenuItem;
	private JMenuItem disconnectFromServerMenuItem;
	private JMenuItem quitMenuItem;
	private JMenu bookmarksMenu;
	private JScrollPane userListScrollPane;
	private JList<User> userList;
	private JScrollPane chatWindowScrollPane;
	private JTextPane chatWindow;
	private JTextField messageInput;
	private JButton sendButton;

	private DefaultListModel<User> model;
	private Color standardColor;
	private Color shadedColor;
	private volatile Document chatDocument;
	SimpleAttributeSet defaultSet;
	SimpleAttributeSet commandSet;
	SimpleAttributeSet errorSet;

	class CellRenderer extends JLabel implements ListCellRenderer<Object> {

		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean selected, boolean cellHasFocus) {

			User user = (User) value;

			String username = user.toString();
			Color permissionColor = user.getPermission().getColorAttribute();
			setText(username);

			if (selected) {

				setBackground(list.getSelectionBackground());
				setForeground(new Color(100,100,255));

			} else if (!selected) {

				if (true) {

					setBackground(list.getBackground());
					setForeground(permissionColor);

				}

			}

			setEnabled(list.isEnabled());
			setFocusable(false);
			setFont(list.getFont());
			setOpaque(true);
			return this;

		}

	}

}
