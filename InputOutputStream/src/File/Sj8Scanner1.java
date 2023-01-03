package File;
import java.util.Scanner;

public class Sj8Scanner1 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		System.out.println("공백 포함 입력 =" + str1);
		
		int n = sc.nextInt();
		double d = sc.nextDouble();
		String str = sc.next();
		System.out.println("n=" + n + " d=" + d + " str=" + str);
		
		char ch = sc.next().charAt(2);
		System.out.println("한글자 입력 = " + ch);
		
		sc.close();
	}
}
