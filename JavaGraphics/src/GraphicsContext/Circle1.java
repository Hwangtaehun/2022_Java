package GraphicsContext;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import GraphicsContext.Sj71DrawTest.Sj71MouseClick;

public class Circle1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjDrawTest frame = new SjDrawTest("원 그리기 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}

}

class SjDrawTest extends JFrame{
	private int x = 220;
	private int y = 230;
	private int size = 20;
	
	public SjDrawTest() {}
	public SjDrawTest(String str) {
		super(str);
	}
	
	public void paint(Graphics gra) {
		//super.paint(gra);
		for(int i = 0; i < 4; i++)
		{
			gra.drawOval(x, y, size, size);
			x -= 10;
			y -= 10;
			size += 20;
		}
		
		//gra.drawOval(x-5, y-5, 40, 40);
		System.out.println("paint");
	}
}