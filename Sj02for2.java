public class Sj02for2{
  public static void main(String[] args){
    int i = 0, sum = 0, s = 1;
    int oddTotal = 0, evenTotal = 0;
    for(i = 1; i <= 100; i++){
      sum += i;
    }
    System.out.println("1 - 100 ÇÕ = " + sum);
    
    sum = 0;
    for(i = 1; i <= 100; i += 2){
      sum += i;
    }
    System.out.println("1 - 100 È¦¼ö ÇÕ = " + sum);
    
    for(i = 1; i <= 100; i++)
    {
      if(i % 2 == 0)
        evenTotal += i;
      else
        oddTotal += i;
    }
    System.out.println("1 - 100 È¦¼ö ÇÕ = " + oddTotal + "\tÂ¦¼öÀÇ ÇÕ = " + evenTotal);
    
    sum = 0;
    for(i = 1; i <= 100; i++){
      sum += i * s;
      s = -s;
    }
    System.out.println("1-2+3-4+...+99-100 = " + sum);
    
    String str[] = {"aa", "bb", "cc"};
    for(String ss : str)
    {
      System.out.println(ss);
    }
  }
}
