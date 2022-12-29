package Thread;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Sj81Thread6 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjThread6 td1 = new SjThread6();
		td1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		td1.setSize(640, 480);
		td1.setVisible(true);
		Thread trd1 = new Thread(td1, "tName1");
		Thread trd2 = new Thread(td1, "tName2");
		trd1.start();
		trd2.start();
	}
}

class SjThread6 extends JFrame implements Runnable{
	private Random r;
	private int x, y, cnt;
	
	public SjThread6(){
		super("원 그리기 ");
		r = new Random();
		cnt = 0;
	}
	
	public void ppp(String tName) {
		Graphics g = getGraphics();
		if(tName.equals("tName1")) {
			g.setColor(Color.black);
			g.fillOval(x, y, 20, 20);
		}
		else {
			g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256), r.nextInt(256)));
			g.fillRect(x, y, 20, 20);
		}
		g.drawString(String.valueOf(cnt), x+22, y+22);
		System.out.println(tName + "위치 : " + x + ", " + y);
	}
	
	@Override
	//public synchronized void run() {
	public void run() {
		// TODO Auto-generated method stub
		Rectangle rec;
		for(int i = 0; i < 30; i++) {
			rec = getBounds();
			x = r.nextInt(rec.width);
			y = r.nextInt(rec.height);
			cnt = i;
			ppp(Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			}catch(Exception e) {}
		}
	}
	
}