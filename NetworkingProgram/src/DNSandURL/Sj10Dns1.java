package DNSandURL;
import java.net.*;

public class Sj10Dns1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte [] addr;
		InetAddress inet;
		try {
			inet = InetAddress.getLocalHost();
			addr = inet.getAddress();
			for(int i = 0; i < addr.length; i++) {
				int aa = (int)addr[i];
				if(aa < 0) {
					aa = aa + 256;
				}
				System.out.print(aa + " ");
			}
			System.out.println("\n내컴퓨터의 IP 주소  : " + inet.getHostAddress());
			System.out.println("내컴퓨터의 Host 이름  : " + inet.getHostName());
		}
		catch(Exception e) {
			System.out.println("LocalHost 오류발생!");
		}
		try {
			inet = InetAddress.getByName("daum.net");
			System.out.println("daum.net의  IP 주소    : "+ inet.getHostAddress());
			System.out.println("daum.net  Host 주소  : "+ inet.getHostName());
		}
		catch(Exception e) {
			System.out.println("daum.net 오류발생 !");
		}
	}
}
