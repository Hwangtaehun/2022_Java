package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import libraryDB.LbDB_mem_info_Frame.tableListener;

import java.sql.*;

public class LbDB_delivery_Frame extends LbDB_main_Frame{
	private int mat_no, lib_no;
	private JTextField tf_bookname, tf_memberid;
	private Combobox_Manager manager;
	private JComboBox <String> lib_Box;
	private ResultSet rs;
	private LbDB_DAO database; //dialog할때만 사용
	private JButton completeBt;
	private JRadioButton rb_dis, rb_app, rb_ret;
	private ButtonGroup gr_approve;
	private SwingItem si;
	
	LbDB_delivery_Frame(){}
	LbDB_delivery_Frame(LbDB_DAO db, Client cl, String str){ //상호대차관리
		this.db = db;
		this.cl = cl;
		menu_title = str;
		pk = cl.primarykey();
		state = cl.state();
		
		makesql();
		setTitle(menu_title);
		menuform();
		Initform();
		baseform();
		if(menu_title.equals("상호대차관리")) {
			editform();
		}
		else {
			
		}
		addWindowListener(this);
	}
	LbDB_delivery_Frame(Client cl, String str, int mat_no){ //회원 상호대차
		database = new LbDB_DAO();
		this.cl = cl;
		menu_title = str;
		this.mat_no = mat_no;
		pk = cl.primarykey();
		state = cl.state();
		
		dialog(menu_title);
		Initform();
		baseform();
		booksea_member_dialog();
	}
	
	LbDB_delivery_Frame(Client cl, String str, SwingItem si){ //관리자 상호대차
		database = new LbDB_DAO();
		this.cl = cl;
		menu_title = str;
		this.si = si;
		pk = cl.primarykey();
		state = cl.state();
		
		dialog(menu_title);
		Initform();
		booksea_manager_dialog();
	}
	
	private void baseform(){
		JLabel label;
		JButton bt;
		
		manager = new Combobox_Manager(lib_Box, "library", "lib_no");
		
		setGrid(gbc,1,1,1,1);
		label = new JLabel("    상호 대차   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,0,2,1,1);
		label = new JLabel("    회원아이디   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,2,1,1);
		tf_memberid = new JTextField(10);
		tf_memberid.setEnabled(false);
		gbl.setConstraints(tf_memberid, gbc);
		leftPanel.add(tf_memberid);
		setGrid(gbc,0,5,1,1);
		label = new JLabel("    책이름    ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,5,1,1);
		tf_bookname = new JTextField(50);
		tf_bookname.setEnabled(false);
		gbl.setConstraints(tf_bookname, gbc);
		leftPanel.add(tf_bookname);
		if(menu_title.equals("상호대차관리")) {
			setGrid(gbc,2,2,1,1);
			bt = new JButton("회원찾기");
			bt.addActionListener(new memberButtonListener());
			gbl.setConstraints(bt, gbc);
			leftPanel.add(bt);
			setGrid(gbc,1,3,1,1);
			bt = new JButton("검색");
			bt.addActionListener(new researchButtonListener());
			gbl.setConstraints(bt, gbc);
			leftPanel.add(bt);
			setGrid(gbc,1,4,1,1);
			label = new JLabel("        ");
			gbl.setConstraints(label, gbc);
			leftPanel.add(label);
			setGrid(gbc,2,5,1,1);
			bt = new JButton("자료검색");
		}
		setGrid(gbc,0,6,1,1);
		label = new JLabel("    배송되는 도서관    ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,6,1,1);
		lib_Box = manager.combox;
		gbl.setConstraints(lib_Box, gbc);
		leftPanel.add(lib_Box);
	}
	
	private void editform() {
		JPanel panel;
		JLabel label;
		JButton bt;
		
		setGrid(gbc,0,7,1,1);
		label = new JLabel("    도착일    ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,7,1,1);
		tf_date = new JTextField(10);
		tf_date.setEnabled(false);
		gbl.setConstraints(tf_date, gbc);
		leftPanel.add(tf_date);
		setGrid(gbc,2,7,1,1);
		bt = new JButton("오늘");
		bt.addActionListener(new todayButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		setGrid(gbc,0,8,1,1);
		label = new JLabel("     승인    ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,8,1,1);
		panel = new JPanel();
		gr_approve = new ButtonGroup();
		rb_dis = new JRadioButton("거절", true);
		rb_dis.addActionListener(new radiobuttonListener());
		rb_dis.addItemListener(new radiobuttonListener());
		rb_dis.setActionCommand("st-0");
		gr_approve.add(rb_dis);
		panel.add(rb_dis);
		rb_app = new JRadioButton("승인", false);
		rb_app.addActionListener(new radiobuttonListener());
		rb_app.addItemListener(new radiobuttonListener());
		rb_app.setActionCommand("st-1");
		gr_approve.add(rb_app);
		panel.add(rb_app);
		rb_ret = new JRadioButton("반송", false);
		rb_ret.addActionListener(new radiobuttonListener());
		rb_ret.addItemListener(new radiobuttonListener());
		rb_ret.setActionCommand("st-2");
		gr_approve.add(rb_ret);
		panel.add(rb_ret);
		gbl.setConstraints(panel, gbc);
		leftPanel.add(panel);
		setGrid(gbc,0,8,1,1);
		bt = new JButton("공백");
		bt.addActionListener(new completeButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		setGrid(gbc,1,8,1,1);
		bt = new JButton("수정");
		bt.addActionListener(new updateButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		setGrid(gbc,2,8,1,1);
		bt = new JButton("삭제");
		bt.addActionListener(new deleteButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		
		String columnName[] = {"책이름", "소장도서관", "수신도서관", "도착일", "상태"};
		tablemodel = new LbDB_TableMode(columnName.length, columnName);
		table = new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(700, 14*16));
		table.getSelectionModel().addListSelectionListener(new tableListener());
		JScrollPane scrollPane = new JScrollPane(table);
		centerPanel.add(scrollPane);
		
		cpane.add("West", leftPanel);
		cpane.add("Center", centerPanel);
		pack();
	}
	
	private void makesql() {
		sql = "SELECT * FROM delivery, material, member, book, library WHERE delivery.mat_no = material.mat_no AND " 
			+ "delivery.mem_no = member.mem_no AND material.book_no = book.book_no AND material.lib_no = library.lib_no AND "
			+ "delivery.lib_no_arr = library.lib_no AND len_no IS NULL";
		sortsql = " ORDER BY book.book_name";
	}
	
	private void booksea() {
		String columnName[] = {"책이름", "소장도서관", "수신도서관", "도착일", "상태"};
		tablemodel = new LbDB_TableMode(columnName.length, columnName);
		table = new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(700, 14*16));
		table.getSelectionModel().addListSelectionListener(new tableListener());
		JScrollPane scrollPane = new JScrollPane(table);
		centerPanel.add(scrollPane);
		
		sql  = "SELECT * FROM delivery, material, member, book, library WHERE delivery.mat_no = material.mat_no AND " 
			+ "delivery.mem_no = member.mem_no AND material.book_no = book.book_no AND material.lib_no = library.lib_no AND "
			+ "delivery.lib_no_arr = library.lib_no AND len_no IS NULL member.mem_no LIKE " + pk ;
		sortsql = " ORDER BY book.book_name";
	}
	
	private void booksea_member_dialog() {
		tf_bookname.setEnabled(false);
		tf_memberid.setEnabled(false);
		
		setGrid(gbc,1,8,1,1);
		completeBt = new JButton("완료");
		completeBt.addActionListener(new completeButtonListener());
		gbl.setConstraints(completeBt, gbc);
		leftPanel.add(completeBt);
		
		cpane.add("West", leftPanel);
		pack();
	}
	
	private void booksea_manager_dialog() {
		JPanel northPanel;
		
		String columnName[] = {"책이름", "소장도서관", "수신도서관"};
		tablemodel = new LbDB_TableMode(columnName.length, columnName);
		table = new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(700, 14*16));
		table.getSelectionModel().addListSelectionListener(new tableListener());
		JScrollPane scrollPane = new JScrollPane(table);
		centerPanel.add(scrollPane);
	}
	
	private void removeTableRow(int row) {
		if(menu_title.equals("상호대차")) {
			table.setValueAt(null, row, 0);
			table.setValueAt(null, row, 1);
			table.setValueAt(null, row, 2);
		}
		else {
			table.setValueAt(null, row, 0);
			table.setValueAt(null, row, 1);
			table.setValueAt(null, row, 2);
			table.setValueAt(null, row, 3);
			table.setValueAt(null, row, 4);
		}
	}
	
	private void MoveData() {
		try {
			String mem_name = result.getString("member.mem_name");
			String mem_id = result.getString("member.mem_id");
			String mem_pw = result.getString("member.mem_pw");
			String mem_detail = result.getString("member.mem_detail");
			String address = address(result);
			String zipcode = result.getString("address.zipcode");
			int del_app = result.getInt("delivery.del_app");
			tf_name.setText(mem_name);
			tf_Id.setText(mem_id);
			tf_Pw.setText(mem_pw);
			tf_Pw2.setText(mem_pw);
			tf_detail.setText(mem_detail);
			fk.insert_add_no(add_no);
			this.mem_lent = mem_lent;
			this.mem_state = mem_state;
			if(mem_lent == 0) {
				rb_active.setSelectedIcon(null);
			}
			else {
				rb_stop.setSelectedIcon(null);
			}
			
			if(mem_state == 5) {
				rb_normal.setSelectedIcon(null);
			}
			else {
				rb_special.setSelectedIcon(null);
			}
			tf_address.setText(address);
			tf_zipcode.setText(zipcode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void OutData() {
		try {
			tf_dialog.setText(result.getString("member.mem_id"));
			fk.insert_mem_no(result.getInt("member.mem_no"));
			closeFrame();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void LoadList(String now_sql) {
		String state, lent, address;
		System.out.println(now_sql);
		result = db.getResultSet(now_sql);
		
		for(int i = 0; i < dataCount; i++) {
			removeTableRow(i);
		}
		try {
			for(dataCount = 0; result.next(); dataCount++) {
				table.setValueAt(result.getString("member.mem_name"), dataCount, 0);
				table.setValueAt(result.getString("member.mem_id"), dataCount, 1);
				table.setValueAt(result.getString("member.mem_pw"), dataCount, 2);
				table.setValueAt(result.getString("address.zipcode"), dataCount, 3);
				table.setValueAt(result.getString("address.zipcode"), dataCount, 4);
				table.setValueAt(result.getString("member.mem_detail"), dataCount, 5);
			}
			repaint();
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
	
	public class clearButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			tf_bookname.setText("");
			tf_date.setText("");
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
	
	public class researchButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class memberButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class materialButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class tableListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting())
				return;
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			if(lsm.isSelectionEmpty())
				System.out.println("No columns are selected");
			else {
				selectedCol = lsm.getMinSelectionIndex();
				if(selectedCol >= dataCount)
					System.out.println("data is Empty");
				else {
					if(!menu_title.equals("회원찾기")) {
						tf_name.setText(table.getValueAt(selectedCol, 0).toString());
						tf_Id.setText(table.getValueAt(selectedCol, 1).toString());
						tf_Pw.setText(table.getValueAt(selectedCol, 2).toString());
						tf_Pw2.setText(table.getValueAt(selectedCol, 2).toString());
						tf_zipcode.setText(table.getValueAt(selectedCol, 3).toString());
						tf_address.setText(table.getValueAt(selectedCol, 4).toString());
						tf_detail.setText(table.getValueAt(selectedCol, 5).toString());
						
						if(table.getValueAt(selectedCol, 6).toString().equals("일반")) {
							rb_normal.setSelected(true);
						}
						else {
							rb_special.setSelected(true);
						}
						
						if(table.getValueAt(selectedCol, 7).toString().equals("활성화")) {
							rb_active.setSelected(true);
						}
						else {
							rb_stop.setSelected(true);
						}
					}
					
					try {
						result.absolute(selectedCol + 1);
						if(menu_title.equals("회원찾기")) {
							OutData();
						}
						else {
							MoveData();
						}						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					repaint();
				}
			}
		}
	}
}