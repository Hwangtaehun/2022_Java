public class Sj05Test03{
  public static void main(String[] args){
    String sa = new String("sejong");
    String sb = new String("sejong");
    String sc = "computer";
    String sd = "computer";
    
    if(sa == sb)
      System.out.println("sa == sb 비교 : sa와 sb는 같다.");
    else
      System.out.println("sa == sb 비교 : sa와 sb는 다르다.");
      
    if(sa.equals(sb))
      System.out.println("sa.equals(sb) 비교 : sa와 sb는 같다.");
    else
      System.out.println("sa.equals(sb) 비교 : sa와 sb는 다르다.");
      
    if(sc == sd)
      System.out.println("sc == sd 비교 : sc와 sd는 같다.");
    else
      System.out.println("sc == sd 비교 : sc와 sd는 다르다.");
      
    if(sc.equals(sd))
      System.out.println("sc.equals(sd) 비교 : sc와 sd는 같다.");
    else
      System.out.println("sc.equals(sd) 비교 : sc와 sd는 다르다.");
      
    System.out.println("sa : " + sa);
    System.out.println("sa.toString() : " + sa.toString());
    System.out.println("sa.charAt(2) : " + sa.charAt(2));
    System.out.println("sa.length() : " + sa.length());
    System.out.println("sa.substring(2,4) : " + sa.substring(2,4));
    System.out.println("sa.substring(2) : " + sa.substring(2));
    System.out.println("sa.concat(sd) : " + sa.concat(sd));
    sb = " "+sa+" "+sd+" ";
    System.out.println("sb=        |" + sb+"|");
    System.out.println("sb.trim()= |" + sb.trim()+"|");
    sc = sc.valueOf(123) + "456";
    System.out.println("sc : " + sc);
    
    StringBuffer strBuf = new StringBuffer("Sejong");
    System.out.println("strBuf.capacity(): " + strBuf.capacity());
    System.out.println("strBuf.length(): " + strBuf.length());
    strBuf.append("Computer");
    System.out.println("strBuf.append(): " + strBuf);
    strBuf.insert(3, "CCC");
    System.out.println("strBuf.insert(): " + strBuf);
    strBuf.replace(3, 5, "DDD");
    System.out.println("strBuf.replace(): " + strBuf);
    strBuf.delete(3, 7);
    System.out.println("strBuf.delete() : " + strBuf);
    strBuf.reverse();
    System.out.println("strBuf.reverse(): " + strBuf);
  }
}
