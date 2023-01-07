package SocketProgram;
import java.net.*;
import java.util.Vector;
import java.io.*;

public class Sj10ChatServer3 {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
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
		System.out.println("Chatting Server3기 1234번 Port에서 접속을 기다립니다.");
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