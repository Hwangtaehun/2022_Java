package ChattingForm;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.util.Vector;
import java.io.*;

public class SjChatServer {
	public static void main(String[] args) {
		//모양
		ChatFrameServer chatForm = new ChatFrameServer("Sejong Chatting Server");
		chatForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatForm.setSize(630, 260);
		chatForm.setVisible(true);
		
		//채팅서버
		/*ServerSocket serverSocket = null;
		Socket clientSocketet = null;
		ChatThread3 chatTrd;
		boolean bool = true;
		Vector<ChatThread3> vClient = new Vector<>();
		try {
			serverSocket = new ServerSocket(1234);
		}catch(IOException e) {
			System.out.println("Server Socket 생성 오류 발생 !");
			System.exit(1);
		}
		System.out.println("Chatting Server3이 1234번 Port에서 접속을 기다립니다.");
		try {
			while(bool) {
				clientSocketet = serverSocket.accept();
				chatTrd = new ChatThread3(clientSocketet, vClient);
				chatTrd.start();
				vClient.addElement(chatTrd);
			}
			serverSocket.close();
		}
		catch(IOException e) {
			System.out.println("접속 실패입니다.");
			System.exit(1);
		}*/
	}
}

class ChatFrameServer extends JFrame{
	JTextArea showText;
	JTextField severIp, portNo, talkName, messageBox;
	JButton connectButton, disConnectButton, sendButton;
	JPanel pan1, pan2, pan3, pan31, pan32;
	
	ServerSocket serverSocket = null;
	Socket clientSocketet = null;
	ChatThread3 chatTrd;
	boolean bool = true;
	Vector<ChatThread3> vClient = new Vector<>();
	
	public ChatFrameServer() {}
	public ChatFrameServer(String str) {
		super(str);
		showText = new JTextArea(8,78);
		severIp = new JTextField("localhost", 7);
		portNo = new JTextField("1234", 4);
		talkName = new JTextField("Server", 4);
		connectButton = new JButton("Server Start");
		disConnectButton = new JButton("Server Stop");
		messageBox = new JTextField(38);
		sendButton = new JButton("Send");
		
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
	
	public void ChattingServer() {
		try {
			serverSocket = new ServerSocket(1234);
		}catch(IOException e) {
			System.out.println("Server Socket 생성 오류 발생 !");
			System.exit(1);
		}
		System.out.println("Chatting Server3이 1234번 Port에서 접속을 기다립니다.");
		try {
			while(bool) {
				clientSocketet = serverSocket.accept();
				chatTrd = new ChatThread3(clientSocketet, vClient);
				chatTrd.start();
				vClient.addElement(chatTrd);
			}
			serverSocket.close();
		}
		catch(IOException e) {
			System.out.println("접속 실패입니다.");
			System.exit(1);
		}
	}
}

class ChatThread3 extends Thread{
	Socket clientSocket = null;
	PrintWriter socketOut;
	BufferedReader socketIn;
	String strInput, strName = "NoName";
	Vector<ChatThread3> vClient;
	
	public ChatThread3() {}
	public ChatThread3(Socket socket, Vector<ChatThread3> v) {
		clientSocket = socket;
		this.vClient = v;
	}
	
	public void removeClient() throws IOException{
		vClient.removeElement(this);
		broadcast("[" + strName + "] 님이 퇴장하셨습니다.");
	}
	
	public void sendUserList() throws IOException{
		socketOut.println("< 현재 접속자 " + vClient.size() + "명 명단 >");
		for(int i = 0; i < vClient.size(); i++) {
			ChatThread3 trd = ((ChatThread3)vClient.elementAt(i));
			socketOut.println(trd.strName);
		}
	}
	
	public void run() {
		try {
			System.out.println("Client:" + clientSocket.toString() + "\n에서 접속하였습니다.");
			socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			socketOut.println("Sj10ChatServer");
			strInput = socketIn.readLine();
			if(strInput.equals("Sj10EchoClient3")) {
				socketOut.println("<단축키> : /h(도움말), /u(접속자목록), /r 대화명 (대화명 변경)");
				socketOut.println("대화명을 입력하세요 !");
				strName = socketIn.readLine();
				broadcast("[" + strName + "] 님이 입장하셨습니다.");
				
				while((strInput = socketIn.readLine()) != null) {
					if(strInput.equals("/h")) {
						socketOut.println("<단축키> : /h(도움말), /u(접속자목록), /r 대화명 (대화명 변경)");
					}else if(strInput.equals("/u")) {
						sendUserList();
					}
					else if(strInput.regionMatches(0, "/r", 0, 2)) {
						String new_name = strInput.substring(2).trim();
						broadcast("접속자 " + strName + " 님의 대화명이 [" + new_name + "](으)로 바뀌었습니다.");
						strName = new_name;
					}
					else {
						broadcast("[" + strName + "]" + strInput);
					}
				}
			}else {
				socketOut.println("잘못된 Client입니다.");
			}
			socketOut.close();
			socketIn.close();
			clientSocket.close();
			removeClient();
		}catch(IOException e) {
			try {
				removeClient();
			}catch(IOException e1) {}
			System.out.println(" " + strName + "의 접속이 끊겼습니다.");
		}
	}
	
	public void broadcast(String msg) throws IOException{
		for(int i = 0; i < vClient.size(); i++) {
			ChatThread3 trd = ((ChatThread3)vClient.elementAt(i));
			trd.socketOut.println(msg);
		}
		System.out.println(msg);
	}
}