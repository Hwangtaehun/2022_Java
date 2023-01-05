package DNSandURL;
import java.io.*;
import java.net.*;

public class Sj10URLcon {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String urlName, data;
		URL url;
		URLConnection urlCon;
		
		InputStream is;
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try {
				System.out.print("http://");
				urlName = br1.readLine();
				if(urlName == null)
					break;
				url = new URL("http://" + urlName);
				urlCon = url.openConnection();
				
				urlCon.connect();
				is = urlCon.getInputStream();
				
				BufferedReader br2 = new BufferedReader(new InputStreamReader(is));
				while((data = br2.readLine()) != null) {
					System.out.println(data);
				}
				System.out.println("ContentType : " + urlCon.getContentType());
				for(int i = 0; i < 5; i++) {
					System.out.println("getHeaderField(" + i +") = " + urlCon.getHeaderField(i));
				}
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
