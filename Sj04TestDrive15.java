public class Sj04TestDrive15
{
  public static void main(String[] args)
  {
    Sj04Class15 ref1 = new Sj04Class15();
    int val1;
    String strName;
    ref1.voidMethod(0);
    ref1.voidMethod(1);
    val1 = ref1.intMethod(5, 3);
    System.out.println("intMethod실행 후 val1 = " + val1);
    strName = ref1.stringMethod("세종");
    System.out.println("stringMethod실행 후 strName = \n" + strName);
  }
}

class Sj04Class15
{
    String name;
    void voidMethod(int n){
        System.out.println(n + " 이 전달 된 void형 Method 실행 ");
        if(n == 0){
            System.out.println("n == 0으로 void형 Method 종료됨");
            return;
        }
        System.out.println("n != 0 일때 void형 Method 실행됨");
        return;
    }

    int intMethod(int n1, int n2){
        int n3;
        System.out.println("int를 Return하는 Method 실행");
        n3 = n1 + n2;
        return n3;
    }

    String stringMethod(String name){
        this.name = "전달 된 이름은 " + name + "입니다.";
        System.out.println("문자열을 Return 하는 Method 실행 ");
        return this.name;
    }
}