package Event;
import java.awt.event.*;
import java.awt.*;

class Sj6Handler3 implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action Event �߻�(��ư�� ����)");
	}
}

class Sj6WindowHandler implements WindowListener{

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
	
}

public class Sj6203 {
	public static void main(String[] args) {
		Frame frm = new Frame("Event ����3");
		Button bt = new Button(" ����������.. ");
		
		bt.addActionListener(new Sj6Handler3());
		frm.addWindowListener(new Sj6WindowHandler());
		
		frm.add(bt, "Center");
		frm.pack();
		frm.setVisible(true);
	}
}
