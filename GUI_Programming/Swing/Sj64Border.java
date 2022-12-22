package Swing;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Sj64Border {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frm = new JFrame("���� ����");
		SjswBorder border = new SjswBorder();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add(border, "Center");
		
		frm.setSize(500, 100);
		//frm.pack();
		frm.setVisible(true);
	}
}

class SjswBorder extends JPanel{
	BevelBorder border;
	EtchedBorder eborder;
	TitledBorder tborder;
	JLabel label;
	
	public SjswBorder() {
		setLayout(new GridLayout(1,4));
		
		label = new JLabel(" ���� ���� ");
		add(label);
		
		label = new JLabel("beveled border");
		border = new BevelBorder(BevelBorder.RAISED);
		label.setBorder(border);
		add(label);
		
		label = new JLabel("������ etched border");
		eborder = new EtchedBorder(EtchedBorder.RAISED);
		label.setBorder(eborder);
		add(label);
		
		label = new JLabel("titled border");
		tborder = new TitledBorder("����");
		tborder.setTitlePosition(TitledBorder.ABOVE_TOP);
		tborder.setTitleJustification(TitledBorder.CENTER);
		label.setBorder(tborder);
		add(label);
	}
}