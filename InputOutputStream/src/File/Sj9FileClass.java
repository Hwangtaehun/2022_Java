package File;
import java.io.*;

public class Sj9FileClass {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String name = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("파 일 명  : ");
		name = in.readLine();
		File file = new File(name);
		
		if(file.exists()) {
			if(file.isFile()) {
				System.out.println("파일 이름 : " + file.getName());
				System.out.println("파일 길이 : " + file.length() + " 바이트");
			}
			
			System.out.println("상대 경로 : " + file.getPath());
			System.out.println("절대 경로 : " + file.getAbsolutePath());
			System.out.println("쓰기 가능 : " + file.canWrite());
			System.out.println("읽기 가능 : " + file.canRead());
			System.out.println("파일 길이 : " + file.length() + " 바이트");
			
			if(file.isDirectory()) {
				String[]dir = file.list();
				for(int i = 0; i < dir.length; i++) {
					System.out.println(dir[i]);
				}
			}
		}
		else {
			System.out.println("해당 파일은 존재하지 않습니다.");
		}
		
		File newFile = new File("..", "newFile2.txt");
		boolean bFile = newFile.createNewFile();
		
		System.out.println("File 생성 여부 : " + bFile);
		System.out.println("절대 경로 : " + newFile.getAbsolutePath());
		System.out.println("절대 경로 : " + newFile.getAbsoluteFile());
	}
}
