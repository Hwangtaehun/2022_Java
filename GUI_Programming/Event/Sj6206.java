package Event;
import java.awt.event.*;
import java.awt.*;

public class Sj6206 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sj6Handler6 sj = new Sj6Handler6();
		sj.test();
	}

}

class MouseClickH6 extends MouseAdapter{
	MouseClickH6(){}
	
	public void mouseClicked (MouseEvent e) {
		if(e.getClickCount() == 2)
			System.out.println("Double Click");
		else if(e.getButton() == MouseEvent.BUTTON1)
			System.out.println("���콺 ���� Ŭ��");
		else if(e.getButton() == MouseEvent.BUTTON2)
			System.out.println("���콺 ��� Ŭ��");
		else if(e.getButton() == MouseEvent.BUTTON3)
			System.out.println("���콺 ������ Ŭ��");
	}
}

class Sj6Handler6 extends WindowAdapter{
	Sj6Handler6(){}
	
	void test() {
		Frame frm = new Frame("Event Adapter ����1");
		frm.addWindowListener(this);
		frm.add(new Label("Mouse�� ����������."), "North");
		frm.addMouseListener(new MouseClickH6());
		frm.setSize(300, 200);
		frm.setVisible(true);
	}
	
	public void windowClosing(WindowEvent e) {
		System.out.println("����� !!");
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);
	}
}