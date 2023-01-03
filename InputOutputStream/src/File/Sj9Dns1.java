package File;
import java.io.*;
import java.net.*;

public class Sj9Dns1 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		String data;
		BufferedWriter bw = new BufferedWriter(new FileWriter("mbc1.html"));
		
		URL url = new URL("http://www.cjpmg.kr");
		BufferedReader br2 = new BufferedReader(new InputStreamReader(url.openStream()));
		
		while((data = br2.readLine()) != null) {
			System.out.println(data);
			bw.write(data);
		}
		br2.close();
		bw.close();
	}

}
