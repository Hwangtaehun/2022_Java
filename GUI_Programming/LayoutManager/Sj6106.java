package LayoutManager;
import java.awt.*;
import java.awt.event.*;

public class Sj6106 {
	public static void main(String[] args) {
		Sj06Class6 sj = new Sj06Class6();
		sj.test();
	}
}

class Sj06Class6 implements ActionListener{
	CardLayout card;
	Frame frm;
	
	public void test() {
		Button bt;
		frm = new Frame("CardLayout 연습");
		card = new CardLayout();
		frm.setLayout(card);
		frm.setSize(300, 200);
		for(int i = 1;i <= 5;i++) {
			bt = new Button(String.valueOf(i));
			bt.addActionListener(this);
			frm.add("sejong", bt);
		}
		//frm.pack();
		frm.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		card.next(frm);
		//card.previous(frm);
	}
}
