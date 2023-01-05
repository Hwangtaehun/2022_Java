package SocketProgram;
import java.io.*;
import java.net.*;

public class Sj10EchoClient2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket echoSocket = null;
		PrintStream socketOut = null;
		BufferedReader socketIn = null;
		BufferedReader stdIn;
		String strUser;
		Sj10ReceiveThread1 rec;
		
		try {
			echoSocket = new Socket("localhost", 1234);
			socketOut = new PrintStream(echoSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			
			rec = new Sj10ReceiveThread1(socketIn);
			rec.start();
		}
		catch(UnknownHostException e) {
			System.out.println("Server가 없습니다.");
			System.exit(1);
		}
		catch(IOException e) {
			System.out.println("입출력 에러입니다.");
			System.exit(1);
		}
		try {
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			while((strUser = stdIn.readLine()) != null) {
				socketOut.println(strUser);
			}
			socketOut.close();
			socketIn.close();
			stdIn.close();
			echoSocket.close();
		}
		catch(Exception e) {
			System.out.println("연결이 끊겼습니다.");
		}
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