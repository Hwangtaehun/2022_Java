public class Sj02if4{
  public static void main(String[] args){
    int data;
    if(args.length > 0){
      data = Integer.parseInt(args[0]);
      if(data % 2 == 0)
        System.out.println("짝수 입니다.");
      else
        System.out.println("홀수 입니다.");
        
      if(data % 2 == 0 || data % 3 == 0)
        System.out.println("2의 배수 또는 3의 배수입니다.");
      else
        System.out.println("2의 배수 또는 3의 배수가 아닙니다.");
        
      if(data >= 65 && data <= 90)
        System.out.println("65에 90 사이의 수입니다.");
      else
        System.out.println("65에 90 사이의 수가 아닙니다.");
        
      if(data >= 10 && data < 20)
        System.out.println("10대 입니다.");
      else if(data >= 20 && data < 30)
        System.out.println("20대 입니다.");
      else if(data >= 30 && data < 40)
        System.out.println("30대 입니다.");
      else
        System.out.println("기타 입니다.");
    }
    else{
      System.out.println("명령줄 인수가 없네요!!");
    }
  }
}
