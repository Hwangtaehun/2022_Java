package Event;
import java.awt.*;
import java.awt.event.*;

public class Sj6207 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sj6Handler7 sj = new Sj6Handler7("Event Adapter 연습2");
		sj.test();
	}
}

abstract class mouseMoAdapter7 implements MouseMotionListener{
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
}

abstract class mouseLiAdapter7 implements MouseListener{
	public void mouseClicked(MouseEvent e) {}
	public void mousePressd(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}

class MouseClickH7 extends mouseLiAdapter7{
	MouseClickH7(){}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("마우스를 클릭");
	}
}

class MyWindowHandler7 extends WindowAdapter{
	MyWindowHandler7(){}
	
	public void windowClosing(WindowEvent e) {
		System.out.println("종료됨 !! ");
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);
	}
}

class Sj6Handler7 extends Frame{
	Frame frm;
	TextField tf;
	public Sj6Handler7() {}
	public Sj6Handler7(String str) {
		super(str);
	}
	public void test() {
		addWindowListener(new MyWindowHandler7());
		add(new Label("Mouse를 Click 그리고 drage 하세요"), BorderLayout.NORTH);
		tf = new TextField(30);
		add(tf, BorderLayout.SOUTH);
		addMouseMotionListener(
			new mouseMoAdapter7() {
				public void mouseDragged(MouseEvent e) {
					tf.setText("x = " + e.getX() + " y = " + e.getY());
				}
			}
		);
		addMouseListener(new MouseClickH7());
		setSize(300, 200);
		setVisible(true);
	}
}