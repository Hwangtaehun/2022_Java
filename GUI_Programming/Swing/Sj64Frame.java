package Swing;
import java.awt.*;
import javax.swing.*;

public class Sj64Frame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frm = new  JFrame("Swing JFrame 연습 ");
		JLabel label1 = new JLabel("Label Test : ");
		JButton button1 = new JButton("Button Test");
		
		Container cpane;
		cpane = frm.getContentPane();
		
		cpane.setLayout(new FlowLayout());
		
		cpane.add(label1);
		cpane.add(button1);
		label1.setText("Label 연습 : ");
		
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//frm.pack();
		frm.setSize(300, 200);
		frm.setVisible(true);
	}

}
