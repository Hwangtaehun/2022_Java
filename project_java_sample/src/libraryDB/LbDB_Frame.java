package libraryDB;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;


public class LbDB_Frame extends JFrame{
	private Client cl;
	private LbDB_DAO db;
	private LbDB_TableMode tablemodel;
	private ResultSet result;
	private JTable table;
	private int state, pk;
	
	public LbDB_Frame() {}
	public LbDB_Frame(LbDB_DAO db, Client cl) {
		super("메인 화면");
		this.db = db;
		this.cl = cl;
		state = cl.state();
		pk = cl.primarykey();
		
		if(state == 1) {
			
		}
		else {
			
		}
	}
	
	private void manager_Initform(){
		
	}
	
	private void manber_Initform() {
		
	}
}