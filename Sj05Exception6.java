class Sj05MyException2 extends Exception{
    private char ch;
    private int pos;
    
    public Sj05MyException2(){
        super("�μ����� ������");
    }
    public Sj05MyException2(char ch, int pos){
        super("����ڿ���2");
        this.ch = ch;
        this.pos = pos;
    }
    public String getMessage(){
        return String.valueOf(pos) + " ��°�� " + String.valueOf(ch) + " �� ���ڰ� �ƴմϴ�.";
    }
}

class Sj05Class6{
    public Sj05Class6(){}
    public void isNumber(char ch, int pos) throws Sj05MyException2{
        if(ch >= '0' && ch <= '9')
            System.out.println(pos + " ��° " + ch + " ����");
        else
            throw new Sj05MyException2(ch, pos);
    }
}

public class Sj05Exception6{
    public static void main(String[] args){
        String str = "123a456";
        Sj05Class6 sj = new Sj05Class6();
        for(int i = 0; i < str.length(); i++){
            try{
                sj.isNumber(str.charAt(i), i);
            }
            catch(Sj05MyException2 e){
                System.out.println(e.getMessage());
            }
        }
    }
}