package GraphicsContext;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Circle2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawTest frame = new DrawTest("원 그리기 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}

}

class DrawTest extends JFrame{
	private int x;
	private int y;
	private int size = 30;
	
	public DrawTest() {}
	public DrawTest(String str) {
		super(str);
		addMouseListener(new MouseClick());
	}
	
	public void paint(Graphics gra) {
		super.paint(gra);
		for(int i = 0; i < 9; i++)
		{
			gra.drawOval(x, y, size, size);
			x -= 10;
			y -= 10;
			size += 20;
			try {
				Thread.sleep(500);
			}
			catch(InterruptedException e) {}
		}
		size = 30;
		System.out.println("paint");
	}
	
	class MouseClick extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			
			repaint();
		}
	}
}