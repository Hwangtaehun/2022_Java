class Sj02for3{
  public static void main(String argv[]){
    int b = 100;
    
    System.out.println("���α׷� ����");
    
    for(int a = 1; a <= 10; a++){
      System.out.println("Loop �պκ�, ���� a�� ����" + a);
      
      if(a == 2){
        System.out.println("continue ����");
        continue;
      }
      if(a == 4){
        System.out.println("break ����");
        break;
      }
      System.out.println("Loop�� ������ �κ�");
    }
    //b = a + 100;
    System.out.println("���α׷� ����");
  }
}
