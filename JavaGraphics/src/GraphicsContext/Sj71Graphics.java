package GraphicsContext;
import java.awt.*;
import javax.swing.*;

public class Sj71Graphics {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjGraphic frame = new SjGraphic("도형그리기");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}
}

class SjGraphic extends JFrame{
	public SjGraphic() {}
	public SjGraphic(String str) {
		super(str);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.drawLine(0, 0, 100, 100);
		
		g.setColor(Color.black);
		g.drawRect(10, 30, 50, 90);
		
		g.setColor(Color.red);
		g.fillRect(100, 30, 50, 90);
		g.clearRect(110, 50, 30, 30);
		
		g.setColor(Color.pink);
		g.drawRoundRect(200, 30, 50, 90, 50, 25);
		g.fillRoundRect(300, 30, 50, 90, 25, 45);
		g.draw3DRect(400, 30, 50, 50, true);
		
		g.setColor(Color.orange);
		g.fill3DRect(500, 30, 50, 50, false);
		g.fill3DRect(500, 100, 50, 50, true);
		
		g.setColor(Color.blue);
		g.drawArc(10, 200, 50, 50, 30, 180);
		g.fillArc(100, 200, 50, 50, 90, 270);
		
		g.setColor(Color.green);
		g.drawOval(10, 270, 50, 50);
		g.fillOval(100, 270, 50, 50);
		
		int [] x = {300, 350, 400, 450, 250};
		int [] y = {250, 200, 250, 400, 350};
		g.drawPolygon(x, y, x.length);
		g.setColor(Color.lightGray);
		g.fillPolygon(x, y, 4);
		
		int [] xx = {500, 550, 600, 550};
		int [] yy = {300, 200, 250, 300};
		g.setColor(Color.blue);
		g.drawPolyline(xx, yy, xx.length);
		
		char [] cc = {'세', '종', 's', 'e', 'j', 'o', 'n', 'g'};
		byte [] bb = {65, 66, 67, 68, 97, 98, 99, 100};
		g.drawChars(cc, 0, cc.length, 10, 400);
		g.drawBytes(bb, 0, bb.length, 10, 450);
	}
}