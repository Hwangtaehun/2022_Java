package Event;
import java.awt.event.*;
import java.awt.*;

public class Sj6204 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sj6Handler4 sj = new Sj6Handler4("Event ���� 4");
		sj.test();
	}

}

class Sj6Handler4 extends Frame implements ActionListener, WindowListener{
	Button bt;
	
	public Sj6Handler4() {
		
	}
	public Sj6Handler4(String str) {
		super(str);
	}
	void test() {
		bt = new Button("����������..");
		bt.addActionListener(this);
		addWindowListener(this);
		add(bt, "Center");
		pack();
		setVisible(true);
	}
	
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
		System.out.println("����� !!");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Action Event �߻�(��ư�� ����)");
		System.out.println("Button�� ���� ���� = " + e.getActionCommand());
	}
	
}