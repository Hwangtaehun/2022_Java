package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class LbDB_material_Frame extends LbDB_main_Frame {
	private LbDB_TableMode tablemodel;
	private ResultSet result;
	private JTable table;
	private int dataCount, selectedCol;
	private String sql, menu_title; 
	private JTextField tf_bookname, tf_author, tf_publish;
	private JButton addBt, updateBt, deleteBt, researchBt, clearBt;
	private JComboBox <String> lib_Box;
	private Container cpane;
	private JPanel leftPanel, centerPanel;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	
	public LbDB_material_Frame () {}
	public LbDB_material_Frame (LbDB_DAO db, Client cl, String str) {
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
		
		initform();
		
		if(str.equals("자료검색")) {
			bookresearch();
		}
		else if(str.equals("자료관리")) {
			
		}
		else if(str.equals("자료추가")) {
			
		}
		
		setTitle(str);
		addWindowListener(this);
	}
	
	private void initform() {
		cpane = getContentPane();
		leftPanel = new JPanel();
		centerPanel = new JPanel();
		gbl = new GridBagLayout();
		leftPanel.setLayout(gbl);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		Combobox_Manager manager = new Combobox_Manager(lib_Box, "library", "lib_no");
		JLabel label;
		
		setGrid(gbc,1,1,1,1);
		label = new JLabel("    자료 검색   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,0,2,1,1);
		label = new JLabel("    도서관       ");
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
		gbl.setConstraints(tf_bookname, gbc);
		leftPanel.add(tf_bookname);
		setGrid(gbc,0,4,1,1);
		label = new JLabel("    저자   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,4,1,1);
		tf_author = new JTextField(20);
		gbl.setConstraints(tf_author, gbc);
		leftPanel.add(tf_author);
		setGrid(gbc,0,5,1,1);
		label = new JLabel("    출판사  ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,5,1,1);
		tf_publish = new JTextField(20);
		gbl.setConstraints(tf_publish, gbc);
		leftPanel.add(tf_publish);
	}
	
	private void bookresearch() {
		setGrid(gbc,1,6,1,1);
		researchBt = new JButton("검색");
		researchBt.addActionListener(new researchButtonListener());
		gbl.setConstraints(researchBt, gbc);
		leftPanel.add(researchBt);
		setGrid(gbc,2,6,1,1);
		clearBt = new JButton("지우기");
		clearBt.addActionListener(new clearButtonListener());
		gbl.setConstraints(clearBt, gbc);
		leftPanel.add(clearBt);
		
		String columnName[] = {"도서관", "책 이름", "저자", "출판사", "대출가능"};
		tablemodel = new LbDB_TableMode(columnName.length, columnName);
		table = new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(700, 14*16));
		table.getSelectionModel().addListSelectionListener(new tableListener());
		JScrollPane scrollPane = new JScrollPane(table);
		centerPanel.add(scrollPane);
		
		cpane.add("West", leftPanel);
		cpane.add("Center", centerPanel);
		pack();
		
		LoadList();
		
		try {
			result.first();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MoveData();
	}
	
	private void removeTableRow(int row) {
		if(menu_title.equals("자료검색")) {
			table.setValueAt(null, row, 0);
			table.setValueAt(null, row, 1);
			table.setValueAt(null, row, 2);
			table.setValueAt(null, row, 3);
			table.setValueAt(null, row, 4);
		}
		else {
			
		}
	}
	
	private void MoveData() {
		try {
			if(menu_title.equals("자료검색")) {
				String libraryname = result.getString("library.lib_name");
				String bookname = result.getString("book.book_name");
				String author = result.getString("book.book_author");
				String publish = result.getString("book.book_publish");
				lib_Box.setSelectedItem(libraryname);
				tf_bookname.setText(bookname);
				tf_author.setText(author);
				tf_publish.setText(publish);
			}
			else {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void LoadList() {
		if(menu_title.equals("자료검색")) {
			String lent_re_state = "대출불가";
			sql = "SELECT library.lib_name, book.book_name, book.book_author, book.book_publish, lent.len_re_st " +
				  "FROM library, book, material LEFT JOIN lent ON material.mat_no = lent.mat_no " + 
				  "WHERE library.lib_no = material.lib_no AND book.book_no = material.book_no";
			result = db.getResultSet(sql);
			
			for(int i = 0; i < dataCount; i++) {
				removeTableRow(i);
			}
			try {
				for(dataCount = 0; result.next(); dataCount++) {
					table.setValueAt(result.getString("library.lib_name"), dataCount, 0);
					table.setValueAt(result.getString("book.book_name"), dataCount, 1);
					table.setValueAt(result.getString("book.book_author"), dataCount, 2);
					table.setValueAt(result.getString("book_publish"), dataCount, 3);
					String str = result.getString("lent.len_re_st");
					if(str == null || str.equals("1")) {
						lent_re_state = "대출가능";
					}
					else {
						lent_re_state = "대출불가";
					}
					table.setValueAt(lent_re_state, dataCount, 4);
				}
				repaint();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			
		}
	}
	
	public class researchButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class addButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}	
	}
	
	public class updateButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}	
	}
	
	public class deleteButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}	
	}
	
	public class clearButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}	
	}
	
	class tableListener implements ListSelectionListener{
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
					lib_Box.setSelectedItem(table.getValueAt(selectedCol, 0).toString());
					tf_bookname.setText(table.getValueAt(selectedCol, 1).toString());
					tf_author.setText(table.getValueAt(selectedCol, 2).toString());
					tf_publish.setText(table.getValueAt(selectedCol, 3).toString());
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
