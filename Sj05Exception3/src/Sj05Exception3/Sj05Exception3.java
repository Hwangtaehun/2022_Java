package Sj05Exception3;

public class Sj05Exception3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sj05Class3 sj = new Sj05Class3();
		//sj.test1(4);
		try {
			System.out.println("try1 시작  ");
			sj.test1(4);
			System.out.println("try1 끝  ");
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("첨자범위  Error 1 ");
		}
		catch(ArithmeticException e){
			System.out.println("연산오류 1 ");
		}
		finally {
			System.out.println("finally 1 실행 ");
		}
		
		try {
			System.out.println("try2 시작  ");
			sj.test2();
			System.out.println("try2 끝  ");
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("첨자범위  Error 2 ");
		}
		catch(ArithmeticException e){
			System.out.println("연산오류 2 ");
		}
		finally {
			System.out.println("finally 2 실행 ");
		}
	}

}
