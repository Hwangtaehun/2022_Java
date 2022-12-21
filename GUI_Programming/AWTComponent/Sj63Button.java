package AWTComponent;
import java.awt.*;
import java.awt.event.*;

public class Sj63Button {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frm = new Frame("Button Test");
		Button bt1 = new Button("ù°");
		Button bt2 = new Button("��°");
		
		frm.setLayout(new FlowLayout());
		bt1.addActionListener(new Sj6Handler63());
		bt2.addActionListener(new Sj6Handler63());
		bt2.addMouseListener(new MouseClickH63());
		frm.addMouseListener(new MouseClickH63());
		frm.addWindowListener(new WindowHandler63());
		
		frm.add(bt1);
		frm.add(bt2);
		frm.setSize(200, 100);
		frm.setVisible(true);
		
		bt1.setLabel("�ϳ�");
		System.out.println("Button 1�� ������ " + bt1.getLabel());
	}

}

class Sj6Handler63 implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Action Event �߻�(��ư�� ����)");
	}
}

class MouseClickH63 extends MouseAdapter{
	public void mouseClicked(MouseEvent e) {
		System.out.println("���콺�� Ŭ��");
	}
}

class WindowHandler63 extends WindowAdapter{
	public void windowClosing(WindowEvent e) {
		System.out.println("����� !! ");
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);
	}
}