package File;
import java.io.*;

public class Sj9File2 {
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String str;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new FileWriter("SjFile2.txt"));
		
		while((str = br.readLine()) != null) {
			bw.write(str);
			bw.newLine();
			System.out.println(str);
		}
		br.close();
		bw.close();
	}
}
