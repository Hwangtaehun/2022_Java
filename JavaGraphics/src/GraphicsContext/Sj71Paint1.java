package GraphicsContext;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sj71Paint1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjPaint1 frame = new SjPaint1("Paint 연습 ");
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
		g.drawString("안녕 세종 " + x + " " + y, 100, 50);
		System.out.println("paint 실행됨   " + cnt);
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
				gra.drawString("오른쪽 버튼 눌림", 100, 80);
			}
		}
	}
}