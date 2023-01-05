package SocketProgram;
import java.io.*;
import java.net.*;

public class Sj10EchoClient1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket echoSocket = null;
		PrintStream socketOut = null;
		BufferedReader socketIn = null;
		BufferedReader stdIn;
		String strInput, strOutput;
		try {
			echoSocket = new Socket("localhost", 1234);
			socketOut = new PrintStream(echoSocket.getOutputStream(), true);
			System.out.println("echo Client Socket = " + echoSocket);
			socketIn = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		}
		catch(UnknownHostException e) {
			System.err.println("Server가 없습니다.");
			System.exit(1);
		}
		catch(IOException e) {
			System.err.println("입출력 에러입니다.");
			System.exit(1);
		}
		try {
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			while((strInput = stdIn.readLine())!=null) {
				socketOut.println(strInput);
				strOutput = socketIn.readLine();
				System.out.println(strOutput);
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
