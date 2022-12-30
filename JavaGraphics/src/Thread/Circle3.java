package Thread;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Circle3 {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DrawTest frame = new DrawTest("원 그리기 3");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}
}

class DrawTest extends JFrame{
	public DrawTest() {}
	public DrawTest(String str) {
		super(str);
		addMouseListener(new MouseClick());
	}
	
	class MouseClick extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			Graphics gra = getGraphics();
			
			Thread1 td1 = new Thread1(x, y, gra);
			td1.start();
		}
	}
}

class Thread1 extends Thread{
	private int x;
	private int y;
	private Graphics gra;
	
	Thread1(){}
	Thread1(int x1, int y2, Graphics gra1){
		this.x = x1;
		this.y = y2;
		this.gra = gra1;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		int size = 30;
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
}