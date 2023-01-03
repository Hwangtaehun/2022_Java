package File;
import java.lang.System;
import java.io.*;

public class Sj9DataIO {
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		File file = new File("tmp_file");
		FileOutputStream fos = new FileOutputStream(file);
		
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.writeBoolean(true);
		dos.writeByte(123);
		dos.writeShort(456);
		dos.writeInt(123456);
		dos.writeChar('A');
		dos.writeFloat(1234.56f);
		dos.writeDouble(1234.56);
		dos.writeUTF("sejong컴퓨터");
		
		System.out.println(dos.size() + " 바이트가 기록됨");
		dos.close();
		fos.close();
		
		FileInputStream fis = new FileInputStream(file);
		DataInputStream dis = new DataInputStream(fis);
		System.out.println("Boolean :\t" + dis.readBoolean());
		System.out.println("Byte    :\t" + dis.readByte());
		System.out.println("Short   :\t" + dis.readShort());
		System.out.println("int     :\t" + dis.readInt());
		System.out.println("Char    :\t" + dis.readChar());
		System.out.println("Float   :\t" + dis.readFloat());
		System.out.println("Double  :\t" + dis.readDouble());
		System.out.println("UTF     :\t" + dis.readUTF());
		dis.close();
		fis.close();
		file.delete();
	}
}
