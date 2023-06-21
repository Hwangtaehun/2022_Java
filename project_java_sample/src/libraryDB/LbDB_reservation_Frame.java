package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.time.*;
import java.time.format.*;

public class LbDB_reservation_Frame extends LbDB_main_Frame {
	LbDB_reservation_Frame(){}
	LbDB_reservation_Frame(LbDB_DAO db, Client cl, String title){
		this.db = db;
		this.cl = cl;
		menu_title = title;
		pk = cl.primarykey();
		state = cl.state();
		fk = new foreignkey();
		menuform();
		Initform();
	}
}
