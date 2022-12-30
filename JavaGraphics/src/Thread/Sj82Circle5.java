package Thread;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Sj82Circle5 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CircleTest5 frame = new CircleTest5("Thread 원 그리기 4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
		frame.init();
	}
}

class CircleTest5 extends JFrame{
	private boolean bRunning = true;
	
	public CircleTest5() {}
	public CircleTest5(String str) {
		super(str);
		addMouseListener(new Circle5MouseClick());
	}
	
	boolean isRunning() {
		return bRunning;
	}
	
	void setRunning(boolean bSet) {
		bRunning = bSet;
	}
	
	class Circle5MouseClick extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			setRunning(false);
		}
	}
	
	public void init() {
		int x, y;
		Random rand = new Random();
		while(bRunning) {
			Rectangle rec = getBounds();
			x = rand.nextInt((int)rec.getWidth());
			y = rand.nextInt((int)rec.getHeight());
			
			CircleThread5 rtd = new CircleThread5(x, y, this);
			Thread td = new Thread(rtd);
			td.start();
			try {
				Thread.sleep(rand.nextInt(100)*10);
			}
			catch(Exception e) {}
		}
	}
}

class CircleThread5 implements Runnable{
	private int x;
	private int y;
	CircleTest5 frame;
	
	CircleThread5(){}
	CircleThread5(int x, int y, CircleTest5 frm){
		this.x = x;
		this.y = y;
		this.frame = frm;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		Graphics gra = frame.getGraphics();
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
