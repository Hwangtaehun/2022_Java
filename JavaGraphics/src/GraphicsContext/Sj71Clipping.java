package GraphicsContext;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Sj71Clipping {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjClippingClass frame = new SjClippingClass("Clipping Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}
}

class SjClippingClass extends JFrame{
	public SjClippingClass() {}
	public SjClippingClass(String str) {
		super(str);
	}
	
	public void paint(Graphics gra) {
		int x;
		int y;
		
		Random rand = new Random();
		//Random rand = new Random(1);
		
		//gra.clipRect(100, 100, 300, 300);
		
		for(int i = 0; i < 500 ; i++) {
			//x = (int)(Math.random()*640);
			x = rand.nextInt(640);
			y = rand.nextInt(480);
			
			Color c = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
			
			gra.setColor(c);
			gra.drawRect(x, y, 10, 10);
		}
	}
}