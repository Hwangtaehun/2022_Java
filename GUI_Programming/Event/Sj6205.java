package Event;
import java.awt.event.*;
import java.awt.*;

public class Sj6205 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sj6Handler5 sj = new Sj6Handler5("inner Event 연습");
		sj.test();
	}

}

class Sj6Handler5 extends Frame{
	String name = "Sejong";
	
	public Sj6Handler5() {}
	public Sj6Handler5(String str) {
		super(str);
	}

	public void test() {
		Button bt = new Button("눌러보셔유..");
		bt.addActionListener(new MyHandler());
		addWindowListener(new MyWindowHandler());
		add(bt, "Center");
		pack();
		setVisible(true);
	}
	
	class MyHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("inner Action Event 발생(버튼이 눌림) " + name);
		}
		
	}
	
	class MyWindowHandler implements WindowListener{

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			System.out.println(name + " : Inner WindowListener에 의해 종료됨 !!");
			e.getWindow().setVisible(false);
			e.getWindow().dispose();
			System.exit(0);
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}