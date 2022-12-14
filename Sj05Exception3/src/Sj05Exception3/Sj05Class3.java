package Sj05Exception3;

class Sj05Class3{
	String str[] = {"Sejong", "Computer", "Academy"};
	
	void test1(int n) throws ArrayIndexOutOfBoundsException, ArithmeticException{
		for(int i = 0; i < n; i++) {
			System.out.println("test1 str[" + i + "] = " + str[i]);
		}
		n = 10/0;
		System.out.println("Test1 끝");
	}
	
	 void test2() throws ArrayIndexOutOfBoundsException, ArithmeticException{
		 test1(3);
	 }
}
