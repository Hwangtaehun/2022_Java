public class Sj05Test01{
  public static void main(String args[]){
    boolean bool = true;
    Boolean wb = new Boolean(bool);
    char ch = 'A';
    Character wc = new Character(ch);
    
    int i = 33, j;
    Integer wn = i;
    Integer wn2 = new Integer("333");
    i = wn.intValue();
    j = i + wn2;
    System.out.println("i = " + i + ", j = " + j);
    
    Float wf = new Float((float)123.45);
    Double wd = new Double(456.78);
    
    System.out.println("wc.toString()= " + wc.toString());
    System.out.println("wb.toString()= " + wb.toString());
    System.out.println("wf.toString()= " + wf.toString());
    System.out.println("wd.toString()= " + wd.toString());
    
    System.out.println("wb.booleanValue()= " + wb.booleanValue());
    System.out.println("wf.floatValue()= " + wf.floatValue());
    System.out.println("wd.doubleValue()= " + wd.doubleValue());
    String str = wn.toString();
    System.out.println("str= " + str);
  }
}
