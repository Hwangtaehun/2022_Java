import java.util.*;
public class Sj05Test07{
  public static void main(String[] args){
    int i;
    Vector<Object> v = new Vector<>();
    //Vector<Object> v = new Vector<>(4);
    //Vector<Object> v = new Vector<>(4, 4);
    String a = "aaa";
    v.addElement(a);
    v.addElement((Object)"bbb");
    v.addElement(new Integer(1));
    v.addElement(3.3);
    v.addElement("ccc");
    v.insertElementAt("insertElementAt", 1);
    v.setElementAt("setElementAt", 2);
    v.ensureCapacity(23);
    v.trimToSize();
    System.out.println("현재 요소의 갯수는 " + v.size());
    System.out.println("현재 Vector 의 크기는 " + v.capacity());
    
    for(i = 0; i < v.size(); i++){
      System.out.println("Vector " + i + "번째는" + v.elementAt(i));
    }
    System.out.println("Vector 의 처음은 " + v.firstElement());
    System.out.println("Vector 의 마지막은 " + v.lastElement());
    if(v.contains("ccc")){
      int n = v.indexOf("ccc");
      System.out.println("ccc가 " + n + "번째에 있습니다.");
    }
    else{
      System.out.println("해당 객체가 없습니다.");
    }
    v.removeAllElements();
    //v.removeElement("ccc");
    //v.removeElementAt(2);
    System.out.println("삭제후 ...");
    
    Enumeration<Object> e = v.elements();
    while(e.hasMoreElements()){
      System.out.println(e.nextElement());
    }
  }
}
