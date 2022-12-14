package Sj05Exception6;

class Sj05Class6 {
	public Sj05Class6(){}
    public void isNumber(char ch, int pos) throws Sj05MyException2{
        if(ch >= '0' && ch <= '9')
            System.out.println(pos + " 번째 " + ch + " 숫자");
        else
            throw new Sj05MyException2(ch, pos);
    }
}
