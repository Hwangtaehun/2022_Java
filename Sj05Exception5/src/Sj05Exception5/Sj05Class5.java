package Sj05Exception5;

class Sj05Class5{
    Sj05Class5(){}

    void test1() throws Sj05MyException{
        System.out.println("Test1()에서 인수 없는 Sj05MyException 객체 생성");
        throw new Sj05MyException();
    }

    void test2() throws Sj05MyException{
        System.out.println("Test2()에서 Error 발생시킴");
        throw new Sj05MyException("인수있는 생성자");
    }
}
