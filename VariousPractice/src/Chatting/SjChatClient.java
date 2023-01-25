package Chatting;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class SjChatClient extends JFrame{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SjChatClient("Sejong Chatting Client").setVisible(true);
	}
	
	public SjChatClient() {}
	public SjChatClient(String str) {
		super(str);
		initForm();
	}
	
	void initForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pan1 = new JPanel();
		pan2 = new JPanel();
		pan11 = new JPanel();
		pan12 = new JPanel();
		
		serverAddr = new JTextField("localhost", 10);
		portNo = new JTextField("1234", 10);
		talkName = new JTextField("손님", 10);
		sendMessageBox = new JTextField(30);
		
		connectButton = new JButton("Connect");
		disconnectButton = new JButton("DisConnect");
		sendButton = new JButton("Send");
		
		showConnectorBox = new JTextArea(10, 10);
		showMessageBox = new JTextArea(20, 40);
		JScrollPane scrollPanel1 = new JScrollPane(showMessageBox);
		
		pan2.setLayout(new BorderLayout());
		pan2.add("Center", scrollPanel1);
		pan2.add("South", sendMessageBox);
		
		pan1.setLayout(new BorderLayout());
		pan11.setLayout(new GridLayout(4, 2, 0, 10));
		pan11.add(new JLabel(" Server Ip"));
		pan11.add(serverAddr);
		pan11.add(new JLabel(" Port No"));
		pan11.add(portNo);
		pan11.add(new JLabel(" Name"));
		pan11.add(talkName);
		pan11.add(connectButton);
		pan11.add(disconnectButton);
		pan12.setLayout(new BorderLayout());
		pan12.add("North", new JLabel("접속자"));
		pan12.add("Center", showConnectorBox);
		pan1.add("North", pan11);
		pan1.add("Center", pan12);
		pan1.add("South", sendButton);
		
		showMessageBox.setEditable(false);
		showConnectorBox.setEditable(false);
		connectButton.setEnabled(true);
		disconnectButton.setEnabled(false);
		sendButton.setEnabled(false);
		
		getContentPane().add("East", pan1);
		getContentPane().add("Center", pan2);
		pack();
		setVisible(true);
		
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				connectButtonActionPerformed(evt);
			}
		});
		
		disconnectButton.addActionListener(new disConnectHandler());
		sendMsgHandler = new sendMessageHandler3();
		sendButton.addActionListener(sendMsgHandler);
		sendMessageBox.addActionListener(sendMsgHandler);
		talkName.requestFocus();
	}
	
	private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String strMsg;
		try {
			echoSocket = new Socket(serverAddr.getText(), Integer.parseInt(portNo.getText()));
			socketOut = new PrintStream(echoSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			strMsg = socketIn.readLine();
			if(strMsg.equals("SjChatServer")) {
				socketOut.println("SjChatClient");
				socketOut.println(talkName.getText());
				rec = new SjChatReceiveThread(socketIn, showMessageBox);
				rec.start();
				sendMessageBox.requestFocus();
				connectButton.setEnabled(false);
				disconnectButton.setEnabled(true);
				sendButton.setEnabled(true);
			}
			else {
				showMessageBox.append("잘못된 Server입니다.\n");
			}
		}
		catch(UnknownHostException e) {
			showMessageBox.append("Server가 없습니다.\n");
		}
		catch(IOException e) {
			showMessageBox.append("입출력 Error\n");
		}
		catch(Exception e) {
			showMessageBox.append("연결이 끊겼습니다.\n");
		}
	}
	
	public class disConnectHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			showMessageBox.append("disconnect ");
			try {
				socketOut.close();
				socketIn.close();
				echoSocket.close();
				connectButton.setEnabled(true);
				disconnectButton.setEnabled(false);
				sendButton.setEnabled(false);
			} catch (IOException e) {
				showMessageBox.append("입출력 Error\n");
			}
		}
	}
	
	public class sendMessageHandler3 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			String strMsg;
			try {
				strMsg = sendMessageBox.getText();
				if(!strMsg.isEmpty()) {
					socketOut.println(strMsg);
					sendMessageBox.setText("");
					sendMessageBox.requestFocus();
				}
			}
			catch(Exception e) {
				showMessageBox.append("전송 오류\n");
			}
		}
	}
	
	private Socket echoSocket;
	private PrintStream socketOut;
	private BufferedReader socketIn;
	private sendMessageHandler3 sendMsgHandler;
	private SjChatReceiveThread rec;
	
	private JPanel pan1;
	private JPanel pan2;
	private JPanel pan11;
	private JPanel pan12;
	private JTextArea showConnectorBox;
	private JTextArea showMessageBox;
	private JTextField serverAddr;
	private JTextField portNo;
	private JTextField talkName;
	private JTextField sendMessageBox;
	private JButton connectButton;
	private JButton disconnectButton;
	private JButton sendButton;
}

class SjChatReceiveThread extends Thread{
	BufferedReader socketIn = null;
	JTextArea showMessageBox;
	String strSocket;
	
	SjChatReceiveThread(){}
	SjChatReceiveThread(BufferedReader socketIn, JTextArea showMessageBox){
		this.socketIn = socketIn;
		this.showMessageBox = showMessageBox;
	}
	public void run() {
		try {
			while((strSocket = socketIn.readLine()) != null) {
				showMessageBox.append(strSocket + "\n");
				showMessageBox.setCaretPosition(showMessageBox.getDocument().getLength());
			}
		}
		catch(Exception e) {
			showMessageBox.append("연결이 끊겼습니다.");
		}
	}
}