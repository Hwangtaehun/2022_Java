package File;
import java.io.*;
import java.util.Date;

public class Sj9RandomAccess {
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		RandomAccessFile file = new RandomAccessFile("Sj9File3.txt", "rw");
		
		file.seek(3);
		file.skipBytes(5);
		System.out.println(file.length());
		
		file.seek(10);
		file.writeUTF(new Date().toString());
		file.seek(0);
		
		while(file.getFilePointer() < file.length()){
			System.out.print(file.getFilePointer() + " : ");
			System.out.println(file.readLine());
		}
		file.close();
	}
}
