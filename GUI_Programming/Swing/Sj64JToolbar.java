package Swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sj64JToolbar {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Toolbar Test");
		SjswToolbar panel = new SjswToolbar();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.setSize(panel.getPreferredSize());
		frame.setVisible(true);
	}
}

class SjswToolbar extends JPanel{
	JToolBar toolBar;
	JComboBox<String>comboBox;
	JButton button1;
	JButton button2;
	
	public SjswToolbar() {
		toolBar = new JToolBar();
		ImageIcon buttonImage1 = new ImageIcon("duke2.gif");
		ImageIcon buttonImage2 = new ImageIcon("dukeWave.gif");
		setLayout(new GridLayout(1, 3, 5, 5));
		button1 = new JButton();
		button2 = new JButton();
		button1.setIcon(buttonImage1);
		button2.setIcon(buttonImage2);
		button1.setPressedIcon(buttonImage2);
		button2.setPressedIcon(buttonImage1);
		button1.addActionListener(new ActionButton());
		button2.addActionListener(new ActionButton());
		comboBox = new JComboBox<String>();
		comboBox.addItem("세종");
		comboBox.addItem("정보처리");
		comboBox.addItem("컴퓨터");
		comboBox.addItem("학원");
		comboBox.insertItemAt("열림기획", 1);
		comboBox.addItemListener(new ComboItem());
		toolBar.add(button1);
		toolBar.addSeparator();
		toolBar.add(comboBox);
		toolBar.add(button2);
		add(toolBar);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(400, 100);
	}
	
	class ComboItem implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange()==ItemEvent.SELECTED) {
				System.out.println(e.getItem() + "선택됨");
			}
		}
	}
	
	class ActionButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			String str;
			if(event.getSource().equals(button1))
				str = "왼쪽";
			else
				str = "오른쪽";
			System.out.println(str + "ImageButton 눌림 ");
		}
	}
}