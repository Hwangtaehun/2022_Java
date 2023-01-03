package DNSandURL;
import java.net.*;
import java.io.*;

public class Sj10Dns2 {
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		InetAddress [] iv;
		String name = "";
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.print("Domain Name : ");
			name = rd.readLine();
			if(name == null)
				break;
			try {
				iv = InetAddress.getAllByName(name);
				for(int i = 0; i < iv.length; i++) {
					System.out.println(name + "의 IP 주소: " + iv[i].getHostAddress());
					System.out.println(name + "의 Host Name: " + iv[i].getHostName());
				}
			}
			catch(Exception e) {
				System.out.println(name + " 해당 Host가 없네요 !");
			}
		}
	}
}
