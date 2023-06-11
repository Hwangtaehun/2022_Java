package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class LbDB_delivery_Frame extends LbDB_main_Frame{
	private int mat_no, lib_no;
	private JTextField tf_bookname, tf_memberid, tf_date;
	private Combobox_Manager manager;
	private JComboBox <String> lib_Box;
	private ResultSet rs;
	private LbDB_DAO database; //dialog할때만 사용
	private JButton completeBt;
	
	LbDB_delivery_Frame(){}
	LbDB_delivery_Frame(LbDB_DAO db, Client cl, String str){
		this.db = db;
		this.cl = cl;
		menu_title = str;
		pk = cl.primarykey();
		state = cl.state();
		
		setTitle(menu_title);
		menuform();
		Initform();
		baseform();
		addWindowListener(this);
	}
	LbDB_delivery_Frame(Client cl, String str, int mat_no){
		database = new LbDB_DAO();
		this.cl = cl;
		menu_title = str;
		this.mat_no = mat_no;
		pk = cl.primarykey();
		state = cl.state();
		
		dialog(menu_title);
		transfer();
		Initform();
		baseform();
		booksea();
		addWindowListener(this);
	}
	
	private void baseform(){
		manager = new Combobox_Manager(lib_Box, "library", "lib_no");
		JLabel label;
		
		setGrid(gbc,1,1,1,1);
		label = new JLabel("    상호 대차   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,0,2,1,1);
		label = new JLabel("    배송되는 도서관    ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,2,1,1);
		lib_Box = manager.combox;
		gbl.setConstraints(lib_Box, gbc);
		leftPanel.add(lib_Box);
		setGrid(gbc,0,3,1,1);
		label = new JLabel("    책이름    ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,3,1,1);
		tf_bookname = new JTextField(50);
		tf_bookname.setEnabled(false);
		gbl.setConstraints(tf_bookname, gbc);
		leftPanel.add(tf_bookname);
		setGrid(gbc,0,4,1,1);
		label = new JLabel("    회원아이디   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,4,1,1);
		tf_memberid = new JTextField(10);
		tf_memberid.setEnabled(false);
		gbl.setConstraints(tf_memberid, gbc);
		leftPanel.add(tf_memberid);
	}
	
	private void editform() {
		setGrid(gbc,1,3,1,1);
		
	}
	
	private void booksea() {
		tf_bookname.setEnabled(false);
		tf_memberid.setEnabled(false);
		
		setGrid(gbc,1,4,1,1);
		completeBt = new JButton("완료");
		completeBt.addActionListener(new completeButtonListener());
		gbl.setConstraints(completeBt, gbc);
		leftPanel.add(completeBt);
		
		cpane.add("West", leftPanel);
		pack();
	}
	
	private void transfer() {
		sql = "SELECT member.mem_no, member.mem_id, material.mat_no, book.book_name FROM member, material, book "
			+ "WHERE material.book_no = book.book_no AND member.mem_no LIKE " 
			+ pk + " AND material.mat_no LIEK " + mat_no;
		rs = database.getResultSet(sql);
		try {
			while(rs.next()) {
				tf_bookname.setText(rs.getString("book.book_name"));
				tf_memberid.setText(rs.getString("member.mem_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public class completeButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			lib_no = manager.foreignkey();
			int mat_lib_no = 0;
			sql = "SELECT * FROM `material` WHERE `mat_no` LIKE " + mat_no;
			rs = database.getResultSet(sql);
			try {
				while(rs.next()) {
					mat_lib_no = rs.getInt("lib_no");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(mat_lib_no == lib_no) {
				JOptionPane.showMessageDialog(null, "자료가 있는 도서관과 배송되는 도서관이 같습니다.",  "상호대차 오류", JOptionPane.PLAIN_MESSAGE);
			}
			else {
				sql = "INSERT INTO `delivery` (`men_no`, `mat_no`, `lib_no_arr`) VALUES (" +
					  pk + ", " + mat_no + ", " + lib_no + ")";
				database.Excute(sql);
				closeFrame();
			}
		}	
	}
	
	public class updateButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class deleteButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}