package ChattingForm;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class SjChatClient {
	public static void main(String[] args) {
		//모양
		ChatClientFrame cf = new ChatClientFrame("Sejong Chatting Client");
		cf.addWindowListener(new WindowExit());
		cf.setVisible(true);
		
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

class ChatClientFrame extends Frame{
	Panel pan1, pan2, pan3, pan11, pan12, pan21, pan22;
	TextArea showText;
	TextField severAddr, portNo, talkName, messageBox;
	Button connectButton, disconnectButton, sendButton;
	
	Socket echoSocket = null;
	PrintStream socketOut = null;
	BufferedReader socketIn = null;
	BufferedReader stdIn;
	String strUser, strMsg;
	Sj10ReceiveThread1 rec;
	
	ChatClientFrame(){}
	ChatClientFrame(final String str){
		super(str);
		pan1 = new Panel();
		pan2 = new Panel();
		pan3 = new Panel();
		pan11 = new Panel();
		pan12 = new Panel();
		pan21 = new Panel();
		pan22 = new Panel();
		
		showText = new TextArea(10, 50);
		severAddr = new TextField("localhost", 10);
		portNo = new TextField("1234", 10);
		talkName = new TextField("손님", 10);
		messageBox = new TextField(30);
		connectButton = new Button("Connect");
		disconnectButton = new Button("DisConnect");
		sendButton = new Button("Send");
		setSize(570,240);
		//addWindowListener(new Sj6WindowHandler());
		pan1.setBackground(Color.GREEN);
		pan2.setBackground(Color.YELLOW);
		pan11.setBackground(Color.LIGHT_GRAY);
		pan12.setBackground(Color.ORANGE);
		pan21.setBackground(Color.CYAN);
		pan22.setBackground(Color.BLUE);
		
		pan1.setLayout(new BorderLayout());
		pan12.setLayout(new BorderLayout());
		pan2.setLayout(new BorderLayout());
		pan21.setLayout(new BorderLayout());
		pan22.setLayout(new BorderLayout());
		
		pan21.add(showText);
		pan22.add(messageBox);
		
		pan2.add("North", pan21);
		pan2.add("Center", pan22);
		
		pan11.setLayout(new GridLayout(4, 2, 0, 10));
		pan11.add(new Label(" Server Ip"));
		pan11.add(severAddr);
		pan11.add(new Label(" Port No"));
		pan11.add(portNo);
		pan11.add(new Label(" Name"));
		pan11.add(talkName);
		pan11.add(connectButton);
		pan11.add(disconnectButton);
		pan12.add(sendButton);
		showText.setEditable(false);
		
		disconnectButton.setEnabled(false);
		sendButton.setEnabled(false);
		pan1.add("North", pan11);
		pan1.add("Center", pan12);
		
		add("East", pan1);
		add("Center", pan2);
	}
	
	public void ClientChatting() {
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
		}
	}
}

class WindowExit extends WindowAdapter{
	public void windowClosing(WindowEvent e) {
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);
	}
}

class Sj10ReceiveThread1 extends Thread{
	BufferedReader socketIn = null;
	String strSocket;
	Sj10ReceiveThread1(){}
	Sj10ReceiveThread1(BufferedReader socketIn){
		this.socketIn = socketIn;
	}
	public void run() {
		System.out.println("Server에 접속됨");
		try {
			while((strSocket = socketIn.readLine()) != null) {
				System.out.println(strSocket);
			}
		}
		catch(Exception e) {
			System.out.println("연결이 끊겼습니다.");
		}
	}
}