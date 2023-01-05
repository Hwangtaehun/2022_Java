package SocketProgram;
import java.net.*;
import java.io.*;

public class Sj10EchoServerM {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		Socket clientSocketet;
		ServerThread trd;
		boolean listening = true;
		try {
			serverSocket = new ServerSocket(1234);
		}
		catch(IOException e) {
			System.out.println("Server Socket 생성 오류 발생!");
			System.exit(1);
		}
		System.out.println("서버가 1234번 Port에서 연결을 기다립니다.");
		try {
			while(listening) {
				clientSocketet = serverSocket.accept();
				trd = new ServerThread(clientSocketet);
				trd.start();
			}
			serverSocket.close();
		}
		catch(IOException e) {
			System.err.println("연결 실패입니다.");
			System.exit(1);
		}
	}
}

class ServerThread extends Thread{
	Socket clientSocket = null;
	PrintWriter socketOut;
	BufferedReader socketIn;
	String strInput;
	
	public ServerThread(){}
	public ServerThread(Socket socket) {
		clientSocket = socket;
	}
	public void run() {
		try {
			System.out.println("Client : " + clientSocket.toString() + "\n에서 접속하였습니다.");
			socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			socketOut.println("Multi Echo Server와 연결되었습니다.");
			
			while((strInput = socketIn.readLine()) != null) {
				socketOut.println("Multi Echo Server : " + strInput);
			}
			socketOut.close();
			socketIn.close();
			clientSocket.close();
		}
		catch(IOException e) {
			System.out.println("Client\n" + clientSocket + "\n이 접속이 끊겼습니다.");
		}
	}
}