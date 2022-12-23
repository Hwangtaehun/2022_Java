package Swing;
import javax.swing.*;
import java.awt.event.*;

public class Sj64JMenuEvent {
	public static void main(String args[]) {
		SjMenuEvent frame = new SjMenuEvent("�޴� Event ����");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class SjMenuEvent extends JFrame implements ActionListener, ItemListener{
	private JCheckBoxMenuItem cm1 = new JCheckBoxMenuItem("��");
	private JCheckBoxMenuItem cm2 = new JCheckBoxMenuItem("��");
	private JCheckBoxMenuItem cm3 = new JCheckBoxMenuItem("��");
	
	public SjMenuEvent() {}
	public SjMenuEvent(String str) {
		super(str);
		setSize(300, 200);
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("����");
		addItem("�� ����", fileMenu);
		addItem("����", fileMenu);
		addItem("����", fileMenu);
		addItem("����", fileMenu);
		
		JMenu checkMenu = new JMenu("ũ��");
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
		System.out.println(e.getActionCommand()+"�޴��� ���� �Ǿ�����");
		if(e.getActionCommand().equals("����")) {
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
			System.out.println(str + " ���õ�");
		else
			System.out.println(str + " ���� ��");
	}
	
}