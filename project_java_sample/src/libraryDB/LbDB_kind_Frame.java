package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class LbDB_kind_Frame extends LbDB_main_Frame {
	private JComboBox <String> one_Box, two_Box, three_Box;
	private Combobox_Inheritance one_to_two, two_to_three;
	
	public LbDB_kind_Frame() {}
	public LbDB_kind_Frame(String str) {
		db = new LbDB_DAO();
		menu_title = str;
		dialog(str);
		Initform();
	}
	public LbDB_kind_Frame(LbDB_DAO db, Client cl, String str) {
		this.db = db;
		this.cl = cl;
		menu_title = str;
		pk = cl.primarykey();
		state = cl.state();
		
		if(state == 1) {
			manager_Initform();
		}
		else {
			member_Initform();
		}
		setTitle(str);
		Initform();
	}
	
	public String integerTokey(int num) {
		String str;
		
		if(num < 10) {
			str = "00" + num;
		}
		else if(10 <= num && num < 100) {
			str = "0" + num;
		}
		else {
			str = Integer.toString(num);
		}
		
		return str;
	}
}
