package GraphicsContext;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sj71Paint1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjPaint1 frame = new SjPaint1("Paint ���� ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

}

class SjPaint1 extends JFrame{
	private int cnt = 0;
	public SjPaint1() {}
	public SjPaint1(String str) {
		super(str);
		addMouseListener(new Sj71MouseClick());
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		int x, y;
		cnt++;
		x = (int)(Math.random()*300);
		y = (int)(Math.random()*300);
		g.drawLine(0, 0, x, y);
		g.drawString("�ȳ� ���� " + x + " " + y, 100, 50);
		System.out.println("paint �����   " + cnt);
	}
	
	class Sj71MouseClick extends MouseAdapter{
		public void mousePressed (MouseEvent arg0) {
			if(arg0.getButton() == MouseEvent.BUTTON1) {
				arg0.getComponent().repaint();
				//repaint();
			}
			else if(arg0.getButton() == MouseEvent.BUTTON3) {
				Graphics gra = arg0.getComponent().getGraphics();
				//Graphics gra = getGraphics();
				gra.drawString("������ ��ư ����", 100, 80);
			}
		}
	}
}