package File;
import java.util.Scanner;

public class Sj8Scanner1 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		System.out.println("���� ���� �Է� =" + str1);
		
		int n = sc.nextInt();
		double d = sc.nextDouble();
		String str = sc.next();
		System.out.println("n=" + n + " d=" + d + " str=" + str);
		
		char ch = sc.next().charAt(2);
		System.out.println("�ѱ��� �Է� = " + ch);
		
		sc.close();
	}
}
