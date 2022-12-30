package Thread;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sj82Circle4 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CircleTest4 frame = new CircleTest4("Thread 원 그리기 4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}
}

class CircleTest4 extends JFrame{
	public CircleTest4() {}
	public CircleTest4(String str) {
		super(str);
		addMouseListener(new Sj82MouseClick4());
		addMouseMotionListener(new Sj82MouseMove4());
	}
	
	class CircleThread4 implements Runnable{
		private int x;
		private int y;
		
		CircleThread4(){}
		CircleThread4(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public void run() {
			// TODO Auto-generated method stub
			Graphics gra = getGraphics();
			for(int i = 20; i < 200; i += 20)
			{
				gra.setColor(Color.blue);
				gra.drawOval(x-i/2, y-i/2, i, i);
				try {
					Thread.sleep(300);
				}
				catch(Exception e) {}
				gra.setColor(Color.white);
				gra.drawOval(x-i/2, y-i/2, i, i);
			}
		}
	}
	
	class Sj82MouseClick4 extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			CircleThread4 rtd = new CircleThread4(e.getX(), e.getY());
			Thread td = new Thread(rtd);
			td.start();
			System.out.println("Mouse Down");
		}
		public void mouseClicked (MouseEvent e) {
			System.out.println("Mouse Up");
		}
	}
	
	class Sj82MouseMove4 extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e) {}
		public void mouseDragged(MouseEvent e) {
			CircleThread4 rtd = new CircleThread4(e.getX(), e.getY());
			Thread td = new Thread(rtd);
			td.start();
		}
	}
}
