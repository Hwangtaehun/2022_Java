package File;
import java.io.*;

public class Sj9File4 {
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String str;
		int cnt;
		byte byteBuffer[] = new byte[10];
		char charBuffer[] = new char[10];
		
		FileInputStream fis = new FileInputStream("Sj9File1.txt");
		BufferedReader br = new BufferedReader(new FileReader("Sj9File1.txt"));
		
		fis.skip(3);
		//cnt = fis.read(byteBuffer, 5, 3);
		cnt = fis.read(byteBuffer);
		
		str = new String(byteBuffer);
		System.out.println(str);
		System.out.println("입력된 Byte = " + cnt);
		
		br.skip(3);
		//cnt = br.read(charBuffer, 5, 3);
		//cnt = br.read(charBuffer);
		
		str = new String(charBuffer);
		System.out.println(charBuffer);
		System.out.println("입력된 Byte = " + cnt);
		
		while((str = br.readLine()) != null) {
			System.out.println(str);
		}
		fis.close();
		br.close();
	}
}
