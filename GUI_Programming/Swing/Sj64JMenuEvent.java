package Swing;
import javax.swing.*;
import java.awt.event.*;

public class Sj64JMenuEvent {
	public static void main(String args[]) {
		SjMenuEvent frame = new SjMenuEvent("메뉴 Event 연습");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class SjMenuEvent extends JFrame implements ActionListener, ItemListener{
	private JCheckBoxMenuItem cm1 = new JCheckBoxMenuItem("대");
	private JCheckBoxMenuItem cm2 = new JCheckBoxMenuItem("중");
	private JCheckBoxMenuItem cm3 = new JCheckBoxMenuItem("소");
	
	public SjMenuEvent() {}
	public SjMenuEvent(String str) {
		super(str);
		setSize(300, 200);
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("파일");
		addItem("새 파일", fileMenu);
		addItem("열기", fileMenu);
		addItem("저장", fileMenu);
		addItem("종료", fileMenu);
		
		JMenu checkMenu = new JMenu("크기");
		cm1.addItemListener(this);
		cm2.addItemListener(this);
		cm3.addItemListener(this);
		checkMenu.add(cm1);
		checkMenu.add(cm2);
		checkMenu.add(cm3);
		
		mb.add(fileMenu);
		mb.add(checkMenu);
		setJMenuBar(mb);
	}
	
	void addItem(String str, JMenu menu) {
		JMenuItem m = new JMenuItem(str);
		m.addActionListener(this);
		menu.add(m);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand()+"메뉴가 선택 되었구먼");
		if(e.getActionCommand().equals("종료")) {
			System.out.println("good bye!!");
			System.exit(0);
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		String str = "";
		if(e.getSource() == cm1)
			str = cm1.getText();
		else if(e.getSource() == cm2)
			str = cm2.getText();
		else if(e.getSource() == cm3)
			str = cm3.getText();
		
		if(e.getStateChange() == ItemEvent.SELECTED)
			System.out.println(str + " 선택됨");
		else
			System.out.println(str + " 해제 됨");
	}
	
}