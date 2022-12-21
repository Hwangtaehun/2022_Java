package LayoutManager;
import java.awt.*;
public class Sj6104 {
	public static void main(String[] args) {
		Frame frm = new Frame("BorderLayout ¿¬½À");
		frm.setSize(300, 200);
		
		frm.add(new Button("µ¿"), BorderLayout.EAST);
		frm.add(new Button("¼­"), BorderLayout.WEST);
		frm.add(new Button("³²"), BorderLayout.SOUTH);
		frm.add(new Button("ºÏ"), BorderLayout.NORTH);
		
		//frm.add(new Button("Áß¾Ó"), BorderLayout.CENTER);
		frm.add(BorderLayout.CENTER, new Button("Áß¾Ó"));
		//frm.add("Center", new Button("Áß¾Ó"));
		//frm.pack();
		frm.setVisible(true);
	}
}