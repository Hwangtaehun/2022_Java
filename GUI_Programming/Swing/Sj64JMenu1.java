package Swing;
import javax.swing.*;

public class Sj64JMenu1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjMenu frame = new SjMenu("�޴� ���� 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

class SjMenu extends JFrame{
	public SjMenu() {}
	public SjMenu(String str) {
		super(str);
		setSize(300, 200);
		
		JMenuBar mb = new JMenuBar();
		
		JMenu fileMenu = new JMenu("����");
		fileMenu.add(new JMenuItem("�� ����"));
		fileMenu.add("����");
		fileMenu.add("����");
		fileMenu.add("�ٸ� �̸����� ����");
		fileMenu.add("�ݱ�");
		fileMenu.addSeparator();
		fileMenu.add("�μ�");
		fileMenu.addSeparator();
		fileMenu.add("����");
		
		JMenu editMenu = new JMenu("����");
		editMenu.add("�߶󳻱�");
		editMenu.add("����");
		editMenu.add("�ٿ��ֱ�");
		editMenu.add("�����");
		
		mb.add(fileMenu);
		mb.add(editMenu);
		setJMenuBar(mb);
	}
}