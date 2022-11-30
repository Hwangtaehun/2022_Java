public class Sj02if5{
  public static void main(String[] args){
    int num1, num2, num3, maxNum, minNum;
    
    if(args.length == 0){
      System.out.println("명령줄 인수를 입력해주세요!!");
      System.exit(0);
    }
    num1 = Integer.parseInt(args[0]);
    num2 = Integer.parseInt(args[1]);
    
    num3 = num1 + num2;
    System.out.println("입력된 인수는 " + num1 + "과 " + num2 + "입니다.");
    System.out.println("입력된 인수의 합은 " + num3 + "입니다.");
    
    if(num1 > num2){
      maxNum = num1;
    }
    else{
      maxNum = num2;
    }
    System.out.println("두개의 인수 중 큰수는 " + maxNum + "입니다.");
    
    minNum = (num1 < num2) ? num1 : num2;
    System.out.println("두개의 인수 중 작은 수는 " + minNum + "입니다.");
  }
}
