package DNSandURL;
import java.io.*;
import java.net.*;

public class Sj10URL {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String urlName, data;
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		URL url;
		while(true) {
			try {
				System.out.print("http://");
				urlName = br1.readLine();
				if(urlName == null)
					break;
				url = new URL("http://" + urlName);
				BufferedReader br2 = new BufferedReader(new InputStreamReader(url.openStream()));
				
				while((data = br2.readLine()) != null) {
					System.out.println(data);
				}
				System.out.println("Protocol  : " + url.getProtocol());
				System.out.println("Host Name : " + url.getHost());
				System.out.println("Port No   : " + url.getPort());
				System.out.println("File      : " + url.getFile());
				System.out.println("Reference : " + url.getRef());
			}
			catch(MalformedURLException e) {
				System.out.println("URL 형식 오류");
			}
			catch(IOException e)
			{
				System.out.println("I/O 오류");
			}
			catch(Exception e)
			{
				System.out.println("기타 오류 발생");
			}
		}
	}
}
