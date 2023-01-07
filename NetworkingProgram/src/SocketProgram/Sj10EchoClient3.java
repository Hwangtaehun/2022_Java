package SocketProgram;
import java.io.*;
import java.net.*;

public class Sj10EchoClient3 {
	public static void main(String[] args) {
		Socket echoSocket = null;
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
		}
	}
}

/*class Sj10ReceiveThread1 extends Thread{
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
}*/