package GraphicsContext;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sj71Draw {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sj71DrawTest frame = new Sj71DrawTest("Mouse Button Click 위치에 사각형, 원 그리기");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}
}

class Sj71DrawTest extends JFrame{
	private int x;
	private int y;
	private int size = 30;
	
	public Sj71DrawTest() {}
	public Sj71DrawTest(String str) {
		super(str);
		addMouseListener(new Sj71MouseClick());
	}
	
	public void paint(Graphics gra) {
		//super.paint(gra);
		gra.drawRect(x, y, size, size);
		System.out.println("paint");
	}
	
	public void sj71DrawOval() {
		Graphics gra = getGraphics();
		gra.drawOval(x, y, size, size);
	}
	
	class Sj71MouseClick extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			
			repaint();
			sj71DrawOval();
		}
	}
}