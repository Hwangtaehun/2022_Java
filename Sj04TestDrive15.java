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
    System.out.println("intMethod���� �� val1 = " + val1);
    strName = ref1.stringMethod("����");
    System.out.println("stringMethod���� �� strName = \n" + strName);
  }
}

class Sj04Class15
{
    String name;
    void voidMethod(int n){
        System.out.println(n + " �� ���� �� void�� Method ���� ");
        if(n == 0){
            System.out.println("n == 0���� void�� Method �����");
            return;
        }
        System.out.println("n != 0 �϶� void�� Method �����");
        return;
    }

    int intMethod(int n1, int n2){
        int n3;
        System.out.println("int�� Return�ϴ� Method ����");
        n3 = n1 + n2;
        return n3;
    }

    String stringMethod(String name){
        this.name = "���� �� �̸��� " + name + "�Դϴ�.";
        System.out.println("���ڿ��� Return �ϴ� Method ���� ");
        return this.name;
    }
}