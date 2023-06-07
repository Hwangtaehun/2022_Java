package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class LbDB_book_Frame extends LbDB_main_Frame{
	private JTextField tf_bookname, tf_author, tf_publish, tf_price, tf_year, tf_research;
	private JButton sortBt;
	private foreignkey fk;
	private String sortsql = "";
	private int sw = 1;
	
	public LbDB_book_Frame() {}
	public LbDB_book_Frame(String str, JTextField tf, foreignkey fk) {
		db = new LbDB_DAO();
		menu_title = str;
		tf_bookname = tf;
		this.fk = fk;
		dialog(str);
		Initform();
		dialogform();
	}
	public LbDB_book_Frame(LbDB_DAO db, Client cl, String str) {
		this.db = db;
		this.cl = cl;
		menu_title = str;
		pk = cl.primarykey();
		state = cl.state();
		
		setTitle(str);
		Initform();
		baseform();
		
		if(state == 1) {
			manager_Initform();
		}
		else {
			member_Initform();
		}
		
		if(menu_title.equals("책추가")) {
			addform();
		}
		else {
			managerform();
			tableform();
			baseform_final();
			tableform_final();
		}
		addWindowListener(this);
	}
	
	private void baseform() {
		JLabel label;
		
		setGrid(gbc,1,1,1,1);
		label = new JLabel("    " + menu_title + "   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		if(menu_title.equals("책관리")) {
			setGrid(gbc,0,2,1,1);
			label = new JLabel(" 검색");
			gbl.setConstraints(label, gbc);
			leftPanel.add(label);
			setGrid(gbc,1,2,1,1);
			tf_research = new JTextField(50);
			gbl.setConstraints(tf_research, gbc);
			leftPanel.add(tf_research);
			setGrid(gbc,2,2,1,1);
			researchBt = new JButton("검색");
			researchBt.addActionListener(new researchButtonListener());
			gbl.setConstraints(researchBt, gbc);
			leftPanel.add(researchBt);
			setGrid(gbc,2,3,1,1);
			sortBt = new JButton("정렬");
			sortBt.addActionListener(new sortButtonListener());
			gbl.setConstraints(sortBt, gbc);
			leftPanel.add(sortBt);
			setGrid(gbc,0,3,1,1);
			label = new JLabel("        ");
			gbl.setConstraints(label, gbc);
			leftPanel.add(label);
			
		}
		setGrid(gbc,0,4,1,1);
		label = new JLabel("책이름");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,4,1,1);
		tf_bookname = new JTextField(50);
		gbl.setConstraints(tf_bookname, gbc);
		leftPanel.add(tf_bookname);
		setGrid(gbc,0,5,1,1);
		label = new JLabel(" 저자");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,5,1,1);
		tf_author = new JTextField(20);
		gbl.setConstraints(tf_author, gbc);
		leftPanel.add(tf_author);
		setGrid(gbc,0,6,1,1);
		label = new JLabel("출판사");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,6,1,1);
		tf_publish = new JTextField(20);
		gbl.setConstraints(tf_publish, gbc);
		leftPanel.add(tf_publish);
		setGrid(gbc,0,7,1,1);
		label = new JLabel(" 가격");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,7,1,1);
		tf_price = new JTextField(10);
		gbl.setConstraints(tf_price, gbc);
		leftPanel.add(tf_price);
		setGrid(gbc,0,8,1,1);
		label = new JLabel(" 년도");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,8,1,1);
		tf_year = new JTextField(10);
		gbl.setConstraints(tf_year, gbc);
		leftPanel.add(tf_year);
		
	}
	
	private void tableform() {
		String columnName[] = {"책 이름", "저자", "출판사", "가격", "출판년도"};
		tablemodel = new LbDB_TableMode(columnName.length, columnName);
		table = new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(700, 14*16));
		table.getSelectionModel().addListSelectionListener(new tableListener());
		JScrollPane scrollPane = new JScrollPane(table);
		centerPanel.add(scrollPane);
	}
	
	private void baseform_final() {
		cpane.add("West", leftPanel);
		cpane.add("Center", centerPanel);
		pack();
	}
	
	private void tableform_final() {
		String now_sql;
		
		sql = "SELECT * FROM `book`";
		sortsql = " ORDER BY `book_name`";
		now_sql = sql + sortsql;
		LoadList(now_sql);
		
		try {
			result.first();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MoveData();
	}
	
	private void dialogform() {
		JLabel label;
		
		JPanel northPanel = new JPanel();
		label = new JLabel("책검색");
		northPanel.add("Center", label);
		
		setGrid(gbc,0,0,1,1);
		tf_research = new JTextField(50);
		gbl.setConstraints(tf_research, gbc);
		centerPanel.add(tf_research);
		setGrid(gbc,1,0,1,1);
		researchBt = new JButton("검색");
		researchBt.addActionListener(new researchButtonListener());
		gbl.setConstraints(researchBt, gbc);
		centerPanel.add(researchBt);
		setGrid(gbc,2,0,1,1);
		sortBt = new JButton("정렬");
		sortBt.addActionListener(new sortButtonListener());
		gbl.setConstraints(sortBt, gbc);
		centerPanel.add(sortBt);
		
		String columnName[] = {"책 이름", "저자", "출판사", "가격", "출판년도"};
		tablemodel = new LbDB_TableMode(columnName.length, columnName);
		table = new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(700, 14*16));
		table.getSelectionModel().addListSelectionListener(new tableListener());
		JScrollPane scrollPane = new JScrollPane(table);
		leftPanel.add(scrollPane);
		
		cpane.add("North", northPanel);
		cpane.add("Center", centerPanel);
		cpane.add("South", leftPanel);
		pack();
	}
	
	private void addform() {
		setGrid(gbc,0,9,1,1);
		addBt = new JButton("추가");
		addBt.addActionListener(new addButtonListener());
		gbl.setConstraints(addBt, gbc);
		leftPanel.add(addBt);
		setGrid(gbc,2,9,1,1);
		clearBt = new JButton("공백");
		clearBt.addActionListener(new clearButtonListener());
		gbl.setConstraints(clearBt, gbc);
		leftPanel.add(clearBt);
		
		cpane.add("Center", leftPanel);
		pack();
	}
	
	private void managerform() {
		setGrid(gbc,0,9,1,1);
		deleteBt = new JButton("삭제");
		deleteBt.addActionListener(new deleteButtonListener());
		gbl.setConstraints(deleteBt, gbc);
		leftPanel.add(deleteBt);
		setGrid(gbc,1,9,1,1);
		updateBt = new JButton("수정");
		updateBt.addActionListener(new updateButtonListener());
		gbl.setConstraints(updateBt, gbc);
		leftPanel.add(updateBt);
		setGrid(gbc,2,9,1,1);
		clearBt = new JButton("공백");
		clearBt.addActionListener(new clearButtonListener());
		gbl.setConstraints(clearBt, gbc);
		leftPanel.add(clearBt);
	}
	
	private void removeTableRow(int row) {
		table.setValueAt(null, row, 0);
		table.setValueAt(null, row, 1);
		table.setValueAt(null, row, 2);
		table.setValueAt(null, row, 3);
		table.setValueAt(null, row, 4);
	}
	
	private void MoveData() {
		try {
			String bookname = result.getString("book_name");
			String author = result.getString("book_author");
			String publish = result.getString("book_publish");
			int price = result.getInt("book_price");
			int year = result.getInt("book_year");
			tf_bookname.setText(bookname);
			tf_author.setText(author);
			tf_publish.setText(publish);
			tf_price.setText(Integer.toString(price));
			tf_year.setText(Integer.toString(year));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void OutData() {
		try {
			tf_bookname.setText(result.getString("book_name"));
			fk.insert_book_no(result.getInt("book_no"));
			closeFrame();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void LoadList(String now_sql) {
		result = db.getResultSet(now_sql);
		
		for(int i = 0; i < dataCount; i++) {
			removeTableRow(i);
		}
		try {
			for(dataCount = 0; result.next(); dataCount++) {
				table.setValueAt(result.getString("book_name"), dataCount, 0);
				table.setValueAt(result.getString("book_author"), dataCount, 1);
				table.setValueAt(result.getString("book_publish"), dataCount, 2);
				table.setValueAt(result.getInt("book_price"), dataCount, 3);
				table.setValueAt(result.getInt("book_year"), dataCount, 4);
			}
			repaint();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean isYear(String strValue) {
		boolean bool = false;
		
		if(isInteger(strValue) && strValue.length() == 4)
			bool = true;
		
		return bool;
	}
	
	public class researchButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String book_name = "%" + tf_research.getText() + "%";
			String book_author = "%" + tf_research.getText() + "%";
			String book_publish = "%" + tf_research.getText() + "%";
			
			sql = "SELECT * FROM book WHERE `book_name` LIKE '" + book_name + "' OR `book_author` LIKE '" +
				  book_author + "' OR `book_publish` LIKE '" + book_publish + "'";
			String now_sql = sql + sortsql;
			System.out.println(now_sql);
			LoadList(now_sql);
			
			try {
				result.first();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(menu_title.equals("책관리")) {
				MoveData();
			}
		}
	}
	
	public class sortButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String now_sql;
			
			if(sw == 1) {
				sortsql = " ORDER BY `book_name`";
				now_sql = sql + sortsql;				
			}
			else {
				sortsql = " ORDER BY `book_year` DESC";
				now_sql = sql + sortsql;
			}
			LoadList(now_sql);
			sw *= -1;
			
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
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String now_sql;
			
			if(tf_bookname.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "책이름을 넣어주세요.", "추가 오류", JOptionPane.WARNING_MESSAGE);
			}
			else if(tf_author.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "저자를 넣어주세요.", "추가 오류", JOptionPane.WARNING_MESSAGE);
			}
			else if(tf_publish.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "출판사를 넣어주세요.", "추가 오류", JOptionPane.WARNING_MESSAGE);
			}
			else if(!isInteger(tf_price.getText())) {
				JOptionPane.showMessageDialog(null, "가격부분이 숫자가 아닙니다.", "추가 오류", JOptionPane.WARNING_MESSAGE);
			}
			else if(!isYear(tf_year.getText())){
				JOptionPane.showMessageDialog(null, "년도부분이 잘못되었습니다.", "추가 오류", JOptionPane.WARNING_MESSAGE);
			}
			else {
				now_sql = "INSERT INTO `book` (`book_name`, `book_author`, `book_publish`, `book_price`, `book_year`) VALUES ('"
						+ tf_bookname.getText() + "', '" + tf_author.getText() + "', '" + tf_publish.getText() + "', "
						+ tf_price.getText() + ", " + tf_year.getText() + ")";
				System.out.println(now_sql);
				db.Excute(now_sql);
			}
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
				code = result.getInt("book_no");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!isInteger(tf_price.getText())) {
				JOptionPane.showMessageDialog(null, "가격부분이 숫자가 아닙니다.", "추가 오류", JOptionPane.WARNING_MESSAGE);
			}
			else if(!isYear(tf_year.getText())){
				JOptionPane.showMessageDialog(null, "년도부분이 잘못되었습니다.", "추가 오류", JOptionPane.WARNING_MESSAGE);
			}
			else {
				now_sql = "UPDATE `book` SET `book_name` = '" + tf_bookname.getText() + "', `book_author` = '" +
						  tf_author.getText() + "', `book_publish` = '" + tf_publish.getText() + "', `book_price` = " +
						  tf_price.getText() + ", `book_year` = " + tf_year.getText() + " WHERE `book_no` = " + code;
				System.out.println(now_sql);
				db.Excute(now_sql);
				now_sql = sql + sortsql;
				LoadList(now_sql);
			}
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
				code = result.getInt("book_no");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			now_sql = "DELETE FROM `book` WHERE `book_no` = " + code;
			db.Excute(now_sql);
			now_sql = sql + sortsql;
			LoadList(now_sql);
		}	
	}
	
	public class clearButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			tf_bookname.setText(null);
			tf_author.setText(null);
			tf_publish.setText(null);
			tf_price.setText(null);
			tf_year.setText(null);
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
					if(menu_title.equals("책검색")) {
						OutData();
					}
					else {
						tf_bookname.setText(table.getValueAt(selectedCol, 0).toString());
						tf_author.setText(table.getValueAt(selectedCol, 1).toString());
						tf_publish.setText(table.getValueAt(selectedCol, 2).toString());
						tf_price.setText(table.getValueAt(selectedCol, 3).toString());
						tf_year.setText(table.getValueAt(selectedCol, 4).toString());
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
}
