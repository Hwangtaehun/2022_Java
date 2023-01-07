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
			System.out.println("Server Socket ���� ���� �߻� !");
			System.exit(1);
		}
		System.out.println("Chatting Server3�� 1234�� Port���� ������ ��ٸ��ϴ�.");
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
			System.out.println("���� �����Դϴ�.");
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
		broadcast("[" + strName + "] ���� �����ϼ̽��ϴ�.");
	}
	
	public void sendUserList() throws IOException{
		socketOut.println("< ���� ������ " + vClient.size() + "�� ��� >");
		for(int i = 0; i < vClient.size(); i++) {
			ChatThread3 trd = ((ChatThread3)vClient.elementAt(i));
			socketOut.println(trd.strName);
		}
	}
	
	public void run() {
		try {
			System.out.println("Client:" + clientSocket.toString() + "\n���� �����Ͽ����ϴ�.");
			socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			socketOut.println("Sj10ChatServer");
			strInput = socketIn.readLine();
			if(strInput.equals("Sj10EchoClient3")) {
				socketOut.println("<����Ű> : /h(����), /u(�����ڸ��), /r ��ȭ�� (��ȭ�� ����)");
				socketOut.println("��ȭ���� �Է��ϼ��� !");
				strName = socketIn.readLine();
				broadcast("[" + strName + "] ���� �����ϼ̽��ϴ�.");
				
				while((strInput = socketIn.readLine()) != null) {
					if(strInput.equals("/h")) {
						socketOut.println("<����Ű> : /h(����), /u(�����ڸ��), /r ��ȭ�� (��ȭ�� ����)");
					}else if(strInput.equals("/u")) {
						sendUserList();
					}
					else if(strInput.regionMatches(0, "/r", 0, 2)) {
						String new_name = strInput.substring(2).trim();
						broadcast("������ " + strName + " ���� ��ȭ���� [" + new_name + "](��)�� �ٲ�����ϴ�.");
						strName = new_name;
					}
					else {
						broadcast("[" + strName + "]" + strInput);
					}
				}
			}else {
				socketOut.println("�߸��� Client�Դϴ�.");
			}
			socketOut.close();
			socketIn.close();
			clientSocket.close();
			removeClient();
		}catch(IOException e) {
			try {
				removeClient();
			}catch(IOException e1) {}
			System.out.println(" " + strName + "�� ������ ������ϴ�.");
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