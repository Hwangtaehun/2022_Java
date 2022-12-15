import java.util.*;
public class Sj05Test05{
  public static void main(String[] args){
    ArrayList aList = new ArrayList();
    aList.add(new Integer(1));
    aList.add(3.3);
    aList.add("sejong");
    aList.add("sejong");
    System.out.println("�ߺ����� �߰� �� aList ����� ���� = " + aList.size());
    System.out.println("����aList ���� = " + aList);
    for(int i = 0; i < aList.size(); i++)
      System.out.println("aList�� " + i + " ��° ��Ҵ� " + aList.get(i));
      
    ArrayList<String> bList = new ArrayList<>();
    bList.add("����");
    bList.add("��ǻ��");
    bList.add(0, "�п�");
    System.out.println("����bList ���� = " + bList);
    
    ArrayList<Object> cList = new ArrayList<>();
    cList.add(new Integer(123));
    cList.add(5.5);
    cList.add("Sejong");
    cList.add('A');
    System.out.println("����cList ���� = " + cList);
    
    Object obj1 = cList.get(2);
    Object obj2 = cList.get(3);
    if(obj1 instanceof String){
      System.out.println("obj1 = String Type");
      String str = (String)obj1;
      System.out.println("str = " + str);
    }
    if(obj2 instanceof Character){
      System.out.println("obj2 = Character Type");
      char ch = ((Character) obj2).charValue();
      System.out.println("ch=" + ch);
    }
    else{
      System.out.println("obj2 = Character Type �ƴ�");
    }
    System.out.println("Sejong�� �ִ��� : " + cList.contains("Sejong"));
    
    System.out.println("Sejong�� ��ġ : " + cList.indexOf("Sejong"));
    System.out.println("cList�� ����ִ��� : " + cList.isEmpty());
    cList.remove("Sejong");
    System.out.println("����cList ���� = " + cList);
    cList.removeAll(cList);
    System.out.println("cList�� ����ִ��� : " + cList.isEmpty());
    Collections.sort(bList);
    System.out.println("Sort�� bList ���� = " + bList);
  }
}
