package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class LbDB_lent_Frame extends LbDB_main_Frame {
	public LbDB_lent_Frame() {}
	public LbDB_lent_Frame(LbDB_DAO db, Client cl, String title) {
		this.db = db;
		this.cl = cl;
		menu_title = title;
		menuform();
		Initform();
	}
	
	
}
