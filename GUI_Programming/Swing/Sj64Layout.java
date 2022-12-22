package Swing;
import javax.swing.*;
import java.awt.*;

public class Sj64Layout {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Layout Manager Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new SjswLayout(1), "North");
		frame.getContentPane().add(new SjswLayout(2), "East");
		frame.getContentPane().add(new SjswLayout(3), "South");
		frame.getContentPane().add(new SjswLayout(4), "Center");
		
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
}

class SjswLayout extends JPanel{
	JButton bt1, bt2, bt3, bt4;
	public SjswLayout() {}
	public SjswLayout(int n) {
		bt1 = new JButton("세  종");
		bt2 = new JButton("정보처리");
		bt3 = new JButton("컴퓨터");
		bt4 = new JButton("학  원");
		
		switch(n) {
		case 1:
			setLayout(new FlowLayout());
			break;
		case 2:
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			break;
		case 3:
			setLayout(new GridLayout(2, 2, 40, 10));
			break;
		case 4:
			setLayout(new BorderLayout());
			break;
		}
		add(bt1);
		add(bt2);
		add(bt3);
		add(bt4);
	}
}