package Swing;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Sj64JTabbedPane {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("JTabbedPaner Test");
		SjswTab panel = new SjswTab();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.setSize(300, 100);
		frame.setVisible(true);
	}

}

class SjswTab extends JPanel implements ChangeListener{
	JTabbedPane pane;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	
	public SjswTab() {
		pane = new JTabbedPane();
		setLayout(new BorderLayout());
		
		panel1 = new JPanel();
		panel1.add(new JLabel("��   ��."));
		pane.addTab("ù��°", panel1);
		
		panel2 = new JPanel();
		panel2.add(new JLabel("����ó��."));
		pane.addTab("�ι�°", panel2);
		
		panel3 = new JPanel();
		panel3.add(new JLabel("�п� �Դϴ�."));
		pane.addTab("����°", panel3);
		
		pane.setSelectedIndex(2);
		pane.addChangeListener(this);
		add(pane);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		int curSellIndex = pane.getSelectedIndex();
		String curPaneTitle = pane.getTitleAt(curSellIndex);
		
		System.out.println(curPaneTitle + "���� ���õǾ����ϴ�.");
	}
	
}