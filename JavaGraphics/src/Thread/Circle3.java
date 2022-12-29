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
		Thread trd1 = new Thread(frame, "tName1");
		trd1.start();
		Thread trd2 = new Thread(frame, "tName2");
		trd2.start();
		Thread trd3 = new Thread(frame, "tName3");
		trd3.start();
	}
}

class DrawTest extends JFrame implements Runnable{
	private int x;
	private int y;
	private int size = 30;
	
	public DrawTest() {}
	public DrawTest(String str) {
		super(str);
		addMouseListener(new MouseClick());
	}
	
	class MouseClick extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String tName = Thread.currentThread().getName();
		Graphics gra = getGraphics();
		if(tName.equals("tName2")) {
			synchronized(this) {
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
		else {
			synchronized(this) {
				for(int i = 0; i < 5; i++)
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
		//DrawCircle();
	}
}