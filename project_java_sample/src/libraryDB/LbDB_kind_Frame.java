package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import libraryDB.LbDB_library_Frame.tableListener;
import libraryDB.LbDB_material_Frame.reservationButtonListener;

import java.sql.*;

public class LbDB_kind_Frame extends LbDB_main_Frame {
	private JComboBox <String> one_Box, two_Box, three_Box;
	private Combobox_Inheritance one_to_two, two_to_three;
	private Combobox_Manager one_manager, two_manager, three_manager;
	private JTextField tf_name, tf_kind_num, tf_research;
	
	public LbDB_kind_Frame() {}
	public LbDB_kind_Frame(String str, JTextField tf) {
		db = new LbDB_DAO();
		menu_title = str;
		tf = tf_kind_num;
		dialog(str);
		Initform();
		baseform();
		dialogform();
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
		if(str.equals("종류추가")) {
			addform();
		}
		else {
			
		}
	}
	
	private void baseform() {
		JLabel label;
		
		setGrid(gbc,1,1,1,1);
		label = new JLabel("    " + menu_title + "   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,0,4,1,1);
		label = new JLabel("    소분류    ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,4,1,1);
		String where = "WHERE `kind_num` LIKE '00_'";
		three_manager = new Combobox_Manager(three_Box, "kind", "kind_no", where, false);
		three_Box = three_manager.combox;
		gbl.setConstraints(three_Box, gbc);
		leftPanel.add(three_Box);
		setGrid(gbc,0,3,1,1);
		label = new JLabel("    중분류    ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,3,1,1);
		where = "WHERE `kind_num` LIKE '0_0'";
		two_to_three = new Combobox_Inheritance (three_manager, three_Box, "중분류");
		two_to_three.insert_nothing(true);
		two_manager = new Combobox_Manager(two_to_three, two_Box, "kind", "kind_no", where); 
		two_Box = two_manager.combox;
		gbl.setConstraints(two_Box, gbc);
		leftPanel.add(two_Box);
		setGrid(gbc,0,2,1,1);
		label = new JLabel("    대분류    ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,2,1,1);
		where = "WHERE `kind_num` LIKE '_00";
		one_to_two = new Combobox_Inheritance (two_manager, two_Box, "대분류");
		one_manager = new Combobox_Manager(one_to_two, one_Box, "kind", "kind_no", where); 
		one_Box = one_manager.combox;
		gbl.setConstraints(one_Box, gbc);
		leftPanel.add(one_Box);
	}
	
	private void dialogform() {
		JButton bt;
		setGrid(gbc,1,5,1,1);
		bt = new JButton("입력");
		gbl.setConstraints(bt, gbc);
		bt.addActionListener(new inputButtonListener());
		leftPanel.add(bt);
		
		cpane.add("Center", leftPanel);
		pack();
	}
	
	private void addform() {
		JLabel label;
		setGrid(gbc,0,5,1,1);
		label = new JLabel("    이름  ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,5,1,1);
		tf_name = new JTextField(20);
		gbl.setConstraints(tf_name, gbc);
		leftPanel.add(tf_name);
		setGrid(gbc,1,6,1,1);
		addBt = new JButton("추가");
		gbl.setConstraints(addBt, gbc);
		addBt.addActionListener(new addButtonListener());
		leftPanel.add(addBt);
		
		cpane.add("Center", leftPanel);
		pack();
	}
	
	private void editform() {
		String columnName[] = {"번호", "이름"};
		tablemodel = new LbDB_TableMode(columnName.length, columnName);
		table = new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(300, 14*16));
		table.getSelectionModel().addListSelectionListener(new tableListener());
		JScrollPane scrollPane = new JScrollPane(table);
		centerPanel.add(scrollPane);
		
		cpane.add("West", leftPanel);
		cpane.add("Center", centerPanel);
		pack();
		
		sql = "SELECT * FROM `kind` ";
		LoadList(sql);
		
		try {
			result.first();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MoveData();
	}
	
	private void removeTableRow(int row) {
		table.setValueAt(null, row, 0);
		table.setValueAt(null, row, 1);
	}
	
	private String numToname(String str) {
		LbDB_DAO Database = new LbDB_DAO();
		ResultSet rs;
		String now_sql, text = "";
		
		now_sql = "SELECT * FROM `kind` WHERE `kind_num` LIKE " + str;
		rs = Database.getResultSet(now_sql);
		
		try {
			while(rs.next()) {
				text = rs.getString("kind_num");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return text;
	}
	
	private void MoveData() {
		try {
			String kind_num = result.getString("kind_num");
			String oneclass = numToname(String.valueOf(kind_num.charAt(0)) + "00");
			String twoclass = numToname(String.valueOf(kind_num.charAt(0)) + String.valueOf(kind_num.charAt(1)) + "0");
			String threeclass = numToname(kind_num);
			one_Box.setSelectedItem(oneclass);
			two_Box.setSelectedItem(twoclass);
			three_Box.setSelectedItem(threeclass);
			tf_name.setText(kind_num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void LoadList(String now_sql) {
		System.out.println(now_sql);
		result = db.getResultSet(now_sql);
		
		for(int i = 0; i < dataCount; i++) {
			removeTableRow(i);
		}
		try {
			for(dataCount = 0; result.next(); dataCount++) {
				table.setValueAt(result.getString("kind_num"), dataCount, 0);
				table.setValueAt(result.getString("kind_name"), dataCount, 1);
			}
			repaint();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String integerTokey(int num) {
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
	
	private String strTonumTostr(String str) {
		String text = "문제발생";
		if(isInteger(str)) {
			int num = Integer.parseInt(str);
			num++;
			text = integerTokey(num);
		}
		else if(isFloat(str)) {
			String[] str_array = str.split(".");
			int num = Integer.parseInt(str_array[1]);
			num++;
			text = str_array[0] + "." + num; 
		}
		return text;
	}
	
	private boolean isInteger(String strValue) {
	    try {
	      Integer.parseInt(strValue);
	      return true;
	    } catch (NumberFormatException ex) {
	      return false;
	    }
	}
	
	private boolean isFloat(String strValue) {
	    try {
	      Float.parseFloat(strValue);
	      return true;
	    } catch (NumberFormatException ex) {
	      return false;
	    }
	}
	
	public class inputButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			sql = "SELECT * FROM `kind` WHERE `kind_no` LIKE " + three_manager.foreignkey();
			result = db.getResultSet(sql);
			try {
				while(result.next()) {
					tf_kind_num.setText(result.getString("kind_num"));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			closeFrame();
		}
	}
	
	public class researchButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String lib_name = "%" + tf_research.getText() + "%";
			String now_sql = sql + " AND library.lib_name LIKE '" + lib_name + "'";
			LoadList(now_sql);
			
			try {
				result.first();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MoveData();
		}
	}
	
	public class addButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String next, previous = null, temp = "", now_sql;
			if(three_Box.getSelectedItem().equals("없음")) {
				now_sql = "SELECT * FROM `kind` WHERE `kind_no` LIKE " + two_manager.foreignkey();
			}
			else {
				now_sql = "SELECT * FROM `kind` WHERE `kind_no` LIKE " + three_manager.foreignkey();
			}
			result = db.getResultSet(now_sql);
			try {
				while(result.next()) {
					previous = result.getString("kind_num");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			next = strTonumTostr(previous);
			
			now_sql = "SELECT * FROM `kind` WHERE `kind_num` LIKE " + next;
			result = db.getResultSet(now_sql);
			try {
				while(result.next()) {
					temp = result.getString("kind_num");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(temp.isEmpty()) {
				now_sql = "INSERT INTO `kind` ( `kind_num`, `kind_name` ) VALUES('" + next + "', '" + tf_name.getText() +"')";
			}
			else {
				next = previous + ".1";
				now_sql = "INSERT INTO `kind` ( `kind_num`, `kind_name` ) VALUES('" + next + "', '" + tf_name.getText() +"')";
			}
			db.Excute(now_sql);
		}
	}
	
	public class updateButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String now_sql;
			int code = 0;
			
			if(selectedCol == -1) {
				System.out.println("변경할 셀이 선택되지 않았습니다.");
				return;
			}
			try {
				code = result.getInt("kind_no");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(textfield_check(tf_lib_date.getText())) {
				if(push_addbt) {
					now_sql = "UPDATE `library` SET `lib_name` = '" + tf_lib_name.getText() + "', `lib_date` = '" + tf_lib_date.getText() +
							  "', `add_no` = " + fk.call_add_no() + ", `lib_detail` = '" + tf_lib_detail.getText() + "' WHERE `lib_no` = " + code;
				}
				else {
					now_sql = "UPDATE `library` SET `lib_name` = '" + tf_lib_name.getText() + "', `lib_date` = '" + tf_lib_date.getText() +
							  "', `lib_detail` = '" + tf_lib_detail.getText() + "' WHERE `lib_no` = " + code;
				}
				System.out.println(now_sql);
				db.Excute(now_sql);
			}
			LoadList(sql);
			
			try {
				result.absolute(selectedCol + 1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			MoveData();
		}	
	}
	
	public class deleteButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String now_sql;
			int code = 0;
			if(selectedCol == -1) {
				System.out.println("변경할 셀이 선택되지 않았습니다.");
				return;
			}
			try {
				code = result.getInt("kind_no");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			now_sql = "DELETE FROM `kind` WHERE `kind_no` = " + code;
			System.out.println(now_sql);
			db.Excute(now_sql);
			LoadList(sql);
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
					tf_lib_name.setText(table.getValueAt(selectedCol, 0).toString());
					tf_lib_date.setText(table.getValueAt(selectedCol, 1).toString());
					tf_zipcode.setText(table.getValueAt(selectedCol, 2).toString());
					tf_address.setText(table.getValueAt(selectedCol, 3).toString());
					tf_lib_detail.setText(table.getValueAt(selectedCol, 4).toString());
					try {
						result.absolute(selectedCol + 1);
						MoveData();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					repaint();
				}
			}
		}
	}
}
