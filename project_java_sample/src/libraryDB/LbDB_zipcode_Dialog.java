package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

/*참조 사이트: https://qh5944.tistory.com/44*/

public class LbDB_zipcode_Dialog extends JDialog implements WindowListener{
	private LbDB_DAO db;
	private JTextField tf_zipcode, tf_address, tf_detail;
	
	public LbDB_zipcode_Dialog() {}
	public LbDB_zipcode_Dialog(LbDB_DAO db, JTextField tf_zipcode, JTextField tf_address, JTextField tf_detail) {
		this.db = db;
		this.tf_zipcode = tf_zipcode;
		this.tf_address = tf_address;
		this.tf_detail = tf_detail;
		addWindowListener(this);
	}
	
	void initform() {
		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("우편검색 페이지 종료!!");
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
