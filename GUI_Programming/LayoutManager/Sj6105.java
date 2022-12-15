package LayoutManager;
import java.awt.*;
public class Sj6105 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frm = new Frame("GridLayout 연습");
		
		//frm.setLayout(new GridLayout());
		//frm.setLayout(new GridLayout(2, 3));
		frm.setLayout(new GridLayout(2, 1));
		
		frm.add(new Button("하나"));
		frm.add(new Button("둘"));
		frm.add(new Button("셋"));
		frm.add(new Button("넷"));
		frm.add(new Button("다섯"));
		frm.add(new Button("여섯"));
		frm.add(new Button("일곱"));
		frm.pack();
		frm.setVisible(true);
	}
}