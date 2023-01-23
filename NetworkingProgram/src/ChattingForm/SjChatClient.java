package ChattingForm;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class SjChatClient {
	public static void main(String[] args) {
		//모양
		ChatClientFrame chatForm = new ChatClientFrame("Sejong Chatting Client");
		chatForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatForm.setSize(600, 260);
		chatForm.setVisible(true);
		
		//클라이언트 대화
		/*Socket echoSocket = null;
		PrintStream socketOut = null;
		BufferedReader socketIn = null;
		BufferedReader stdIn;
		String strUser, strMsg;
		Sj10ReceiveThread1 rec;
		try {
			echoSocket = new Socket("localhost", 1234);
			socketOut = new PrintStream(echoSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			
			strMsg = socketIn.readLine();
			if(strMsg.equals("Sj10ChatServer")) {
				socketOut.println("Sj10EchoClient3");
				rec = new Sj10ReceiveThread1(socketIn);
				rec.start();
				
				stdIn = new BufferedReader(new InputStreamReader(System.in));
				while((strUser = stdIn.readLine()) != null) {
					socketOut.println(strUser);
				}
				stdIn.close();
			}
			else {
				System.out.println("잘못된 Server입니다.");
			}
			socketOut.close();
			socketIn.close();
			echoSocket.close();
		}
		catch(UnknownHostException e) {
			System.err.println("Server가 없습니다.");
			System.exit(1);
		}
		catch(IOException e) {
			System.err.println("입출력  Error.");
			System.exit(1);
		}
		catch(Exception e) {
			System.out.println("연결이 끊겼습니다.");
			System.exit(1);
		}*/
	}
}

class ChatClientFrame extends JFrame{
	JTextArea showText;
	JTextField severIp, portNo, talkName, messageBox;
	JButton connectButton, disConnectButton, sendButton;
	JPanel pan1, pan2, pan3, pan31, pan32;
	
	String stalkName;
	ClientChatting cc;
	
	public ChatClientFrame() {}
	public ChatClientFrame(String str) {
		super(str);
		showText = new JTextArea(8,78);
		severIp = new JTextField("localhost", 7);
		portNo = new JTextField("1234", 4);
		talkName = new JTextField("손님1", 4);
		connectButton = new JButton("Connect");
		connectButton.addActionListener(new CCBHandler());
		disConnectButton = new JButton("DisConnect");
		disConnectButton.addActionListener(new CDCBHandler());
		messageBox = new JTextField(38);
		sendButton = new JButton("Send");
		sendButton.addActionListener(new SendHandler());
		
		pan1 = new JPanel();
		pan2 = new JPanel();
		pan3 = new JPanel();
		pan31 = new JPanel();
		pan32 = new JPanel();
		
		pan1.setBackground(Color.GREEN);
		pan2.setBackground(Color.YELLOW);
		pan3.setBackground(Color.CYAN);
		
		add("North", pan1);
		add("Center", pan2);
		add("South", pan3);
		
		pan2.setLayout(new BorderLayout());
		pan2.add(showText);
		showText.setEditable(false);
		
		pan31.add(new Label("Server IP"));
		pan31.add(severIp);
		pan31.add(new Label(" port No"));
		pan31.add(portNo);
		pan31.add(new Label(" 대  화  명"));
		pan31.add(talkName);
		pan31.add(connectButton);
		pan31.add(disConnectButton);
		disConnectButton.setEnabled(false);
		pan32.add(new Label("Message"));
		pan32.add(messageBox);
		messageBox.setEditable(false);
		pan32.add(sendButton);
		sendButton.setEnabled(false);
		
		pan3.setLayout(new BorderLayout());
		pan3.add("Center", pan31);
		pan3.add("South", pan32);
	}
	
	public class CCBHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			connectButton.setEnabled(false);
			disConnectButton.setEnabled(true);
			sendButton.setEnabled(true);
			messageBox.setEditable(true);
			portNo.setEnabled(false);
			severIp.setEnabled(false);
			
			int iportNo = 0;
			String sportNo = portNo.getText();
			stalkName = talkName.getText();
			try{
				iportNo = Integer.parseInt(sportNo);
	        }
	        catch (NumberFormatException ex){
	            ex.printStackTrace();
	        }
			String sseverIp = severIp.getText();
			
			cc = new ClientChatting(showText, messageBox);
			cc.connect(iportNo, sseverIp, stalkName);
		}
	}
	
	public class CDCBHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			connectButton.setEnabled(true);
			disConnectButton.setEnabled(false);
			sendButton.setEnabled(false);
			messageBox.setEditable(false);
			portNo.setEnabled(true);
			severIp.setEnabled(true);
			
			cc.disconnect();
		}
	}
	
	public class SendHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			//stalkName = talkName.getText();
			stalkName = talkName.getText();
			cc.send(stalkName);
		}
	}
}

class ClientChatting{
	Socket echoSocket = null;
	PrintStream socketOut = null;
	BufferedReader socketIn = null;
	BufferedReader stdIn;
	String strUser, strMsg;
	Sj10ReceiveThread1 rec;
	JTextArea showText;
	JTextField messageBox;
	
	String talkName;
	
	ClientChatting(){}
	ClientChatting(JTextArea ST, JTextField MB){
		showText = ST;
		messageBox = MB;
	}
	
	public void connect(int iportNo, String sseverIp, String stalkName) {
		try {
			echoSocket = new Socket(sseverIp, iportNo);//new Socket("localhost", 1234);
			socketOut = new PrintStream(echoSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			strMsg = socketIn.readLine();
			talkName = stalkName;
			//if(strMsg.equals("Sj10ChatServer")) {
			if(strMsg.equals("SjChatServer")) {
				socketOut.println("SjChatClient");
				socketOut.println(talkName);
				rec = new Sj10ReceiveThread1(socketIn, showText);
				rec.start();
			}
			else {
				System.out.println("잘못된 Server입니다.");
				socketOut.close();
				socketIn.close();
				echoSocket.close();
			}
		}
		catch(UnknownHostException e) {
			System.err.println("Server가 없습니다.");
			System.exit(1);
		}
		catch(IOException e) {
			System.err.println("입출력  Error.");
			System.exit(1);
		}
		catch(Exception e) {
			System.out.println("연결이 끊겼습니다.");
			System.exit(1);
		}
	}
	
	public void disconnect()
	{
		try {
			socketOut.close();
			socketIn.close();
			echoSocket.close();
		}
		catch(UnknownHostException e) {
			System.err.println("Server가 없습니다.");
			System.exit(1);
		}
		catch(IOException e) {
			System.err.println("입출력  Error.");
			System.exit(1);
		}
		catch(Exception e) {
			System.out.println("연결이 끊겼습니다.");
			System.exit(1);
		}
	}
	
	public void send(String stalkName) {
		if(!talkName.equals(stalkName))
		{
			strUser = "/r" + stalkName;
			socketOut.println(strUser);
			talkName = stalkName;
		}
		strUser = messageBox.getText();
		socketOut.println(strUser);
	}
}

class Sj10ReceiveThread1 extends Thread{
	BufferedReader socketIn = null;
	String strSocket;
	JTextArea showText;
	
	Sj10ReceiveThread1(){}
	Sj10ReceiveThread1(BufferedReader socketIn, JTextArea showText){
		this.socketIn = socketIn;
		this.showText = showText;
	}
	public void run() {
		showText.append("Server에 접속됨");
		try {
			while((strSocket = socketIn.readLine()) != null) {
				showText.append(strSocket + "\n");
			}
		}
		catch(Exception e) {
			showText.append("연결이 끊겼습니다.");
		}
	}
}
