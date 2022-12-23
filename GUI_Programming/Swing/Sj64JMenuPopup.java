package Swing;
import javax.swing.*;
import java.awt.event.*;

public class Sj64JMenuPopup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjMenuPopup frame = new SjMenuPopup("Popup 메뉴 연습");
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
		
		addItem("새 파일", popMenu);
		addItem("열기", popMenu);
		addItem("저장", popMenu);
		addItem("다른 이름으로 저장", popMenu);
		addItem("닫기", popMenu);
		popMenu.addSeparator();
		addItem("인쇄", popMenu);
		popMenu.addSeparator();
		addItem("종료", popMenu);
	}
	
	void addItem(String str, JPopupMenu popMenu2) {
		JMenuItem m = new JMenuItem(str);
		m.addActionListener(this);
		popMenu2.add(m);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand() + "메뉴가 선택 되었구먼");
	}
	
	class MyMouseClick extends MouseAdapter{
		public void mouseClicked (MouseEvent e) {
			popMenu.show((JFrame)e.getSource(), e.getX(), e.getY());
		}
	}
}