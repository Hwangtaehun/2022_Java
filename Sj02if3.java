public class Sj02if3{
  public static void main(String[] args){
    int data;
    
    if(args.length > 0){
    data = Integer.parseInt(args[0]);
    if(data > 0){
      System.out.println("양수 입니다.");
      }
    else if(data < 0){
      System.out.println("음수 입니다.");
      }
    else{
      System.out.println("영 입니다.");
      }
    if(data >= 90)
      System.out.println("A 학점입니다.");
    else if(data >= 80)
      System.out.println("B 학점입니다.");
    else if(data >= 70)
      System.out.println("C 학점입니다.");
    else if(data >= 60)
      System.out.println("D 학점입니다.");
    else
      System.out.println("F 학점입니다.");
    }
    else{
      System.out.println("명령줄 인수가 없네요!!");
    }
  }
}
