package File;
import java.io.*;

public class Sj9File3 {
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int inData;
		
		//FileInputStream fis = new FileInputStream("Sj9File1.txt");
		FileReader fis = new FileReader("Sj9File1.txt");
		
		while((inData = fis.read()) != -1) {
			System.out.print((char)inData);
		}
		fis.close();
	}
}
