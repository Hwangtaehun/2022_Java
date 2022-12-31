package IpOpS;
import java.io.*;

public class Sj9io2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStreamReader isr = new InputStreamReader(System.in);
		OutputStreamWriter osw = new OutputStreamWriter(System.out);
		int inData;
		try {
			while((inData = isr.read()) != -1) {
				osw.write(inData);
				osw.write('\n');
				//osw.flush();
			}
			isr.close();
			osw.close();
		}
		catch(IOException e) {
			System.err.println(e);
		}
	}
}
