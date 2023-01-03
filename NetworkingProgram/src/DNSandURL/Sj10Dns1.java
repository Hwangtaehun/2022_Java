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
			System.out.println("\n����ǻ���� IP �ּ�  : " + inet.getHostAddress());
			System.out.println("����ǻ���� Host �̸�  : " + inet.getHostName());
		}
		catch(Exception e) {
			System.out.println("LocalHost �����߻�!");
		}
		try {
			inet = InetAddress.getByName("daum.net");
			System.out.println("daum.net��  IP �ּ�    : "+ inet.getHostAddress());
			System.out.println("daum.net  Host �ּ�  : "+ inet.getHostName());
		}
		catch(Exception e) {
			System.out.println("daum.net �����߻� !");
		}
	}
}
