package File;
import java.io.*;

public class Sj9FileClass {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String name = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("�� �� ��  : ");
		name = in.readLine();
		File file = new File(name);
		
		if(file.exists()) {
			if(file.isFile()) {
				System.out.println("���� �̸� : " + file.getName());
				System.out.println("���� ���� : " + file.length() + " ����Ʈ");
			}
			
			System.out.println("��� ��� : " + file.getPath());
			System.out.println("���� ��� : " + file.getAbsolutePath());
			System.out.println("���� ���� : " + file.canWrite());
			System.out.println("�б� ���� : " + file.canRead());
			System.out.println("���� ���� : " + file.length() + " ����Ʈ");
			
			if(file.isDirectory()) {
				String[]dir = file.list();
				for(int i = 0; i < dir.length; i++) {
					System.out.println(dir[i]);
				}
			}
		}
		else {
			System.out.println("�ش� ������ �������� �ʽ��ϴ�.");
		}
		
		File newFile = new File("..", "newFile2.txt");
		boolean bFile = newFile.createNewFile();
		
		System.out.println("File ���� ���� : " + bFile);
		System.out.println("���� ��� : " + newFile.getAbsolutePath());
		System.out.println("���� ��� : " + newFile.getAbsoluteFile());
	}
}
