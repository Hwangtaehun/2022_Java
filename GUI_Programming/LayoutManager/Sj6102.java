package LayoutManager;
import java.awt.*;
public class Sj6102 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frm = new Frame("Frame°ú Panel");
		Panel pan1 = new Panel();
		Panel pan2 = new Panel();
		
		frm.setSize(500, 500);
		frm.setBackground(Color.yellow);
		frm.setLayout(null);
		
		pan1.setSize(100, 200);
		pan1.setBackground(Color.blue);
		pan2.setBounds(200, 200, 250, 250);
		pan2.setBackground(Color.green);
		
		frm.add(pan1);
		frm.add(pan2);
		frm.setVisible(true);
	}

}