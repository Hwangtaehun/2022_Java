package libraryDB;
import java.awt.event.*;
import javax.swing.*;

public class LbDB_Frame extends JFrame implements WindowListener {
	private String title = "";
	
	public LbDB_Frame() {};
	
	public void dialog(String str) {
		title = str;
		setTitle(title);
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		if(!title.isEmpty()) {
			System.out.println(title + "종료됨 !!");
			e.getWindow().setVisible(false);
			e.getWindow().dispose();
		}
		else {
			System.out.println("종료됨 !!");
			e.getWindow().setVisible(false);
			e.getWindow().dispose();
			System.exit(0);
		}
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
