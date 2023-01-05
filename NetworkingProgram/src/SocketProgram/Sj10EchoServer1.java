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
			System.out.println("Server Socket ���� ���� �߻� !");
		}
		System.out.println("������ 1234�� Port���� ������ ��ٸ��ϴ�.");
		try {
			clientSocket = serverSocket.accept();
		}
		catch(IOException e) {
			System.out.println("���� �����Դϴ�.");
			System.exit(1);
		}
		try {
			System.out.println("Client:" + clientSocket.toString() + "\n���� �����Ͽ����ϴ�.");
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
			System.out.println("������ ������ϴ�.");
		}
	}
}
