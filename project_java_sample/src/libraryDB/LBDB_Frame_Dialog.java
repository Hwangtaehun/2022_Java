package libraryDB;
import java.awt.event.*;
import javax.swing.*;

public class LBDB_Frame_Dialog extends JFrame implements WindowListener {
	private String title;
	
	LBDB_Frame_Dialog() {}
	
	public void title(String str) {
		setTitle(str);
		this.title = str;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println(title + "종료됨 !!");
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
