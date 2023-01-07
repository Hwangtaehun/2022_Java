package SocketProgram;
import java.net.*;
import java.util.Vector;
import java.io.*;

public class Sj10ChatServer1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		Socket clientSocketet;
		ChatThread1 trd;
		int port = 1234;
		boolean listening = true;
		Vector v = new Vector();
		
		try {
			serverSocket = new ServerSocket(port);
		}
		catch(IOException e) {
			System.out.println("Server Socket 생성 오류 발생!");
			System.exit(1);
		}
		System.out.println("서버가 " + port + "번 Port에서 연결을 기다립니다.");
		
		try {
			while(listening) {
				clientSocketet = serverSocket.accept();
				trd = new ChatThread1(clientSocketet, v);
				trd.start();
				v.addElement(trd);
			}
			serverSocket.close();
		}
		catch(IOException e) {
			System.err.println("연결 실패입니다.");
			System.exit(1);
		}
	}
}

class ChatThread1 extends Thread{
	Socket clientSocket = null;
	PrintWriter socketOut;
	BufferedReader socketIn;
	String strInput, strName;
	Vector v;
	
	public ChatThread1() {}
	public ChatThread1(Socket socket, Vector v) {
		clientSocket = socket;
		this.v = v;
	}
	
	public void broadcast(String msg) throws IOException{
		for(int i = 0; i < v.size(); i++) {
			ChatThread1 trd = ((ChatThread1)v.elementAt(i));
			if(trd.socketOut != null)
				trd.socketOut.println(msg);
		}
		System.out.println(msg);
	}
	
	public void run() {
		try {
			System.out.println("Client : " + clientSocket.toString()+"\n에서 접속하였습니다.");
			socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			socketOut.println("Chatting Server와 연결되었습니다.");
			socketOut.println("대화명 : ");
			strName = socketIn.readLine();
			broadcast("[" + strName + "] 님이 입장하셨습니다.");
			
			while((strInput = socketIn.readLine()) != null) {
				broadcast("[" + strName + "]" + strInput);
			}
			socketOut.close();
			socketIn.close();
			clientSocket.close();
		}
		catch(IOException e) {
			System.out.println("Client\n" + clientSocket + " \n의 접속이 끊겼습니다.");
		}
	}
}

