package Sj05Exception6;

class Sj05MyException2 extends Exception{
    private char ch;
    private int pos;
    
    public Sj05MyException2(){
        super("인수없는 생성자");
    }
    public Sj05MyException2(char ch, int pos){
        super("사용자연습2");
        this.ch = ch;
        this.pos = pos;
    }
    public String getMessage(){
        return String.valueOf(pos) + " 번째의 " + String.valueOf(ch) + " 는 숫자가 아닙니다.";
    }
}
