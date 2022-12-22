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
		panel1.add(new JLabel("세   종."));
		pane.addTab("첫번째", panel1);
		
		panel2 = new JPanel();
		panel2.add(new JLabel("정보처리."));
		pane.addTab("두번째", panel2);
		
		panel3 = new JPanel();
		panel3.add(new JLabel("학원 입니다."));
		pane.addTab("세번째", panel3);
		
		pane.setSelectedIndex(2);
		pane.addChangeListener(this);
		add(pane);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		int curSellIndex = pane.getSelectedIndex();
		String curPaneTitle = pane.getTitleAt(curSellIndex);
		
		System.out.println(curPaneTitle + "탭이 선택되었습니다.");
	}
	
}