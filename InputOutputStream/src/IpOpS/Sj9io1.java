package IpOpS;
import java.io.*;

public class Sj9io1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream is = System.in;
		OutputStream os = System.out;
		int inData;
		try {
			while((inData = is.read()) != -1)
			//while((inData = System.in.read()) != -1)
			{
				os.write(inData);
				os.write('\n');
				//os.flush();
			}
			is.close();
			os.close();
		}
		catch(IOException e) {
			System.err.println(e);
		}
	}
}
