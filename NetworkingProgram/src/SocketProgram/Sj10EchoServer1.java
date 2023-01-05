package SocketProgram;
import java.net.*;
import java.io.*;

public class Sj10EchoServer1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		PrintWriter socketOut;
		BufferedReader socketIn;
		String strData;
		try {
			serverSocket = new ServerSocket(1234);
		}
		catch(IOException e) {
			System.out.println("Server Socket 생성 오류 발생 !");
		}
		System.out.println("서버가 1234번 Port에서 연결을 기다립니다.");
		try {
			clientSocket = serverSocket.accept();
		}
		catch(IOException e) {
			System.out.println("연결 실패입니다.");
			System.exit(1);
		}
		try {
			System.out.println("Client:" + clientSocket.toString() + "\n에서 접속하였습니다.");
			socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			while((strData = socketIn.readLine()) != null) {
				socketOut.println("Echo Server : " + strData);
				System.out.println("Client : " + strData);
			}
			socketOut.close();
			socketIn.close();
			clientSocket.close();
			serverSocket.close();
		}
		catch(Exception e) {
			System.out.println("연결이 끊겼습니다.");
		}
	}
}
