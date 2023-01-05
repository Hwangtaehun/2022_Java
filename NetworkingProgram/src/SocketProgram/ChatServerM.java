package SocketProgram;
import java.util.*;
import java.net.*;
import java.io.*;

public class ChatServerM {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket clientSocketet;
		CServerThread trd;
		boolean listening = true;
		
		LinkedList<Socket> list = new LinkedList<Socket>();
		int clientN = 1;
		
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
				list.addLast(clientSocketet);
				trd = new CServerThread(clientSocketet, clientN, list);
				clientN++;
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

class CServerThread extends Thread{
	Socket clientSocket = null;
	PrintWriter socketOut;
	BufferedReader socketIn;
	String strInput;
	
	LinkedList<Socket> list;
	int clientN;
	int indexNo;
	
	public CServerThread(){}
	public CServerThread(Socket socket, int num, LinkedList<Socket> numlist) {
		clientSocket = socket;
		clientN = num;
		list = numlist;
		for (int i = 0; i < list.size(); i++) {
			Socket inf = list.get(i);
			if(inf == clientSocket)
				indexNo = i;
		}
	}
	public void run() {
		try {
			System.out.println("Client : " + clientSocket.toString() + "\n에서 접속하였습니다.");
			socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			socketOut.println("Multi Echo Server와 연결되었습니다.");
			
			while((strInput = socketIn.readLine()) != null) {
				for (int i = 0; i < list.size(); i++) {
					Socket sendSocket = list.get(i);
					socketOut = new PrintWriter(sendSocket.getOutputStream(), true);
					if(indexNo != i)
					{
						socketOut.println(clientN + " : " + strInput);
					}
				}
			}
			socketOut.close();
			socketIn.close();
			clientSocket.close();
		}
		catch(IOException e) {
			list.remove(indexNo);
			System.out.println("Client\n" + clientSocket + "\n이 접속이 끊겼습니다.");
		}
	}
}
