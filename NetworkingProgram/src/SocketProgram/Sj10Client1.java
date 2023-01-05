package SocketProgram;
import java.io.IOException;
import java.net.*;

public class Sj10Client1 {
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Socket echoSocket = null;
		echoSocket = new Socket("localhost", 1234);
		echoSocket.close();
	}
}
