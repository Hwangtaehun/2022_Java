package File;
import java.io.*;

public class Sj9File1 {
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int inData;
		InputStreamReader isr = new InputStreamReader(System.in);
		
		//FileOutputStream fout = new FileOutputStream("Sj9File1.txt");
		FileWriter fout = new FileWriter("Sj9File1.txt");
		//FileWriter fout = new FileWriter("C:\\������\\Ȳ����\\Java\\TextFile\\Sj9File1.txt");
		
		while((inData = isr.read()) != -1){
			fout.write(inData);
			//System.out.println(inData);
		}
		isr.close();
		fout.close();
	}
}
