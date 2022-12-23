package Swing;
import javax.swing.*;
import java.awt.event.*;

public class Sj64JMenuPopup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjMenuPopup frame = new SjMenuPopup("Popup �޴� ����");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

class SjMenuPopup extends JFrame implements ActionListener{
	JPopupMenu popMenu;
	
	public SjMenuPopup() {}
	public SjMenuPopup(String str) {
		super(str);
		setSize(500, 300);
		
		addMouseListener(new MyMouseClick());
		popMenu = new JPopupMenu();
		
		addItem("�� ����", popMenu);
		addItem("����", popMenu);
		addItem("����", popMenu);
		addItem("�ٸ� �̸����� ����", popMenu);
		addItem("�ݱ�", popMenu);
		popMenu.addSeparator();
		addItem("�μ�", popMenu);
		popMenu.addSeparator();
		addItem("����", popMenu);
	}
	
	void addItem(String str, JPopupMenu popMenu2) {
		JMenuItem m = new JMenuItem(str);
		m.addActionListener(this);
		popMenu2.add(m);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand() + "�޴��� ���� �Ǿ�����");
	}
	
	class MyMouseClick extends MouseAdapter{
		public void mouseClicked (MouseEvent e) {
			popMenu.show((JFrame)e.getSource(), e.getX(), e.getY());
		}
	}
}