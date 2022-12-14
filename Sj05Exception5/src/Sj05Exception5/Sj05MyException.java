package Sj05Exception5;

class Sj05MyException extends Exception{
    public Sj05MyException(){
        super("인수없는 생성자");
    }
    public Sj05MyException(String str){
        super(str);
    }
}
