package IpOpS;
import java.io.*;

public class Sj9io3 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int inData, cnt = 0;
		char ch;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedInputStream br = new BufferedInputStream(System.in);
		try {
			/*while(true) {
				inData = br.read();
				if(inData == -1)
					break;
				cnt++;
				ch = (char)inData;
				System.out.print(ch);
				br.skip(2);
			}*/
			while(true) {
				String s=  br.readLine();
				if(s == null)
					break;
				cnt++;
				System.out.println(s);
			}
			br.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
		System.out.println("입력된 문자수/행수 = " + cnt);
	}
}
