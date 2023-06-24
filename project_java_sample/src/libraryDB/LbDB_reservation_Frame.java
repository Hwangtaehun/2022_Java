package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.time.*;
import java.time.format.*;

//reservation 테이블과 관련있는 event처리 클래스
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
	
	
	private void baseform() {
		
	}
	
}
