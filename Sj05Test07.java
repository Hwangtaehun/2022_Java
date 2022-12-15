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
    System.out.println("���� ����� ������ " + v.size());
    System.out.println("���� Vector �� ũ��� " + v.capacity());
    
    for(i = 0; i < v.size(); i++){
      System.out.println("Vector " + i + "��°��" + v.elementAt(i));
    }
    System.out.println("Vector �� ó���� " + v.firstElement());
    System.out.println("Vector �� �������� " + v.lastElement());
    if(v.contains("ccc")){
      int n = v.indexOf("ccc");
      System.out.println("ccc�� " + n + "��°�� �ֽ��ϴ�.");
    }
    else{
      System.out.println("�ش� ��ü�� �����ϴ�.");
    }
    v.removeAllElements();
    //v.removeElement("ccc");
    //v.removeElementAt(2);
    System.out.println("������ ...");
    
    Enumeration<Object> e = v.elements();
    while(e.hasMoreElements()){
      System.out.println(e.nextElement());
    }
  }
}
