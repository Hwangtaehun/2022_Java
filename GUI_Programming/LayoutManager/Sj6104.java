package LayoutManager;
import java.awt.*;
public class Sj6104 {
	public static void main(String[] args) {
		Frame frm = new Frame("BorderLayout ����");
		frm.setSize(300, 200);
		
		frm.add(new Button("��"), BorderLayout.EAST);
		frm.add(new Button("��"), BorderLayout.WEST);
		frm.add(new Button("��"), BorderLayout.SOUTH);
		frm.add(new Button("��"), BorderLayout.NORTH);
		
		//frm.add(new Button("�߾�"), BorderLayout.CENTER);
		frm.add(BorderLayout.CENTER, new Button("�߾�"));
		//frm.add("Center", new Button("�߾�"));
		//frm.pack();
		frm.setVisible(true);
	}
}