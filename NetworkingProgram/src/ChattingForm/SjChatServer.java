package ChattingForm;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.util.Vector;
import java.io.*;
import java.awt.event.*;

public class SjChatServer {
	public static void main(String[] args) {
		//모양
		ChatServerFrame cf = new ChatServerFrame("Sejong Chatting Server");
		cf.addWindowListener(new WindowExit());
		cf.setVisible(true);
		
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

class ChatServerFrame extends Frame{
	Panel pan1, pan2, pan3, pan11, pan12, pan21, pan22;
	TextArea showText;
	TextField severAddr, portNo, talkName, messageBox;
	Button connectButton, disconnectButton, sendButton;
	
	ChatServerFrame(){}
	ChatServerFrame(String str){
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
		talkName = new TextField("Server", 10);
		messageBox = new TextField(30);
		connectButton = new Button("Server Start");
		connectButton.addActionListener(new SStartBHandler());
		disconnectButton = new Button("Server Stop");
		disconnectButton.addActionListener(new SStopBHandler());
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
	
	public class SStartBHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			connectButton.setEnabled(false);
			disconnectButton.setEnabled(true);
	}
	
	public class SStopBHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			connectButton.setEnabled(true);
			disconnectButton.setEnabled(false);
		}
	}
}

class ChatThread3 extends Thread{
	Socket clientSocket = null;
	PrintWriter socketOut;
	BufferedReader socketIn;
	String strInput, strName = "NoName";
	Vector<ChatThread3> vClient;
	TextArea showText;
	
	public ChatThread3() {}
	public ChatThread3(Socket socket, Vector<ChatThread3> v, TextArea showText) {
		clientSocket = socket;
		this.vClient = v;
		this.showText = showText;
	}
	
	public void removeClient() throws IOException{
		vClient.removeElement(this);
		broadcast("[" + strName + "] 님이 퇴장하셨습니다.\n");
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
			showText.append("Client:" + clientSocket.toString() + "\n에서 접속하였습니다.\n");
			socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			socketOut.println("SjChatServer");
			strInput = socketIn.readLine();
			if(strInput.equals("SjChatClient")) {
				socketOut.println("<단축키> : /h(도움말), /u(접속자목록), /r 대화명 (대화명 변경)");
				socketOut.println("대화명을 입력하세요 !");
				strName = socketIn.readLine();
				broadcast("[" + strName + "] 님이 입장하셨습니다.\n");
				
				while((strInput = socketIn.readLine()) != null) {
					if(strInput.equals("/h")) {
						socketOut.println("<단축키> : /h(도움말), /u(접속자목록), /r 대화명 (대화명 변경)");
					}else if(strInput.equals("/u")) {
						sendUserList();
					}
					else if(strInput.regionMatches(0, "/r", 0, 2)) {
						String new_name = strInput.substring(2).trim();
						broadcast("접속자 " + strName + " 님의 대화명이 [" + new_name + "](으)로 바뀌었습니다.\n");
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
		showText.append(msg);
	}
}