package LayoutManager;
import java.awt.*;
public class Sj6103 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frm = new Frame("FlowLayout ¿¬½À");
		frm.setSize(300, 200);
		
		frm.setLayout(new FlowLayout());
		
		//frm.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 4));
		
		//frm.setLayout(null);
		
		frm.add(new Button("Button1"));
		frm.add(new Button("Button2"));
		frm.add(new Button("Button3"));
		frm.add(new Button("Button4"));
		frm.add(new Button("Button5"));
		//frm.pack();
		frm.setVisible(true);
	}

}