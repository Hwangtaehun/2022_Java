package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

class Material_foreignkey{
	private int lib_no, book_no, kind_no, mem_no, lent_no, add_no;
	
	public void Material_foreignkey() {
		lib_no = 0;
		book_no = 0;
		kind_no = 0;
		mem_no = 0;
		lent_no = 0;
		add_no = 0;
	}
	
	public void insert_lib_no(int lib_no) {
		this.lib_no = lib_no;
	}
	
	public void insert_book_no(int book_no) {
		this.book_no = book_no;
	}
	
	public void insert_kind_no(int kind_no) {
		this.kind_no = kind_no;
	}
	
	public void insert_mem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	
	public void insert_add_no(int add_no) {
		this.add_no = add_no;
	}
	
	public int call_lib_no() {
		return lib_no;
	}
	
	public int call_book_no() {
		return book_no;
	}
	
	public int call_kind_no() {
		return kind_no;
	}
	
	public int call_mem_no() {
		return mem_no;
	}
	
	public int call_lent_no() {
		return lent_no;
	}
	
	public int call_add_no() {
		return add_no;
	}
}

public class LbDB_material_Frame extends LbDB_Frame {
	private LbDB_DAO db;
	private LbDB_TableMode tablemodel;
	private ResultSet result;
	private JTable table;
	private int dataCount, selectedCol;
	private String sql, menu_title; 
	private Material_foreignkey mf;
	private Container cpane;
	private JPanel leftPanel, centerPanel;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	private JTextField tf_bookname, tf_author, tf_publish;
	private JButton addBt, updateBt, deleteBt, researchBt, clearBt;
	private JComboBox <String> lib_Box;
	
private void bookresearch() {
		String sql, name_content = "";
		String[] libraryname;
		JLabel label;
		
		leftPanel = new JPanel();
		centerPanel = new JPanel();
		
		sql = "SELECT `lib_name` FROM `library`";
		result = db.getResultSet(sql);
		try {
			while(result.next()) {
				name_content += result.getString("lib_name");
				name_content += " ";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		libraryname = name_content.split(" ");
		mf = new Material_foreignkey();
		
		repaint();
		setGrid(gbc,0,1,1,1);
		label = new JLabel("    자료 검색   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,0,2,1,1);
		label = new JLabel("    도서관       ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,2,1,1);
		lib_Box = new JComboBox<String>(new DefaultComboBoxModel<String>(libraryname));
		lib_Box.addItemListener(new ComboboxListener());
		gbl.setConstraints(lib_Box, gbc);
		leftPanel.add(lib_Box);
		setGrid(gbc,0,3,1,1);
		label = new JLabel("    책이름    ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,3,1,1);
		tf_bookname = new JTextField(20);
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
		table.setPreferredScrollableViewportSize(new Dimension(470, 14*16));
		table.getSelectionModel().addListSelectionListener(new tableListener());
		
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
		table.setValueAt(null, row, 0);
		table.setValueAt(null, row, 1);
		table.setValueAt(null, row, 2);
		table.setValueAt(null, row, 3);
		table.setValueAt(null, row, 4);
	}
	
	private void MoveData() {
		try {
			String libraryname = result.getString("library.lib_name");
			String bookname = result.getString("book.book_name");
			String author = result.getString("book.book_author");
			String publish = result.getString("book.book_publish");
			lib_Box.setSelectedItem(libraryname);
			tf_bookname.setText(bookname);
			tf_author.setText(author);
			tf_publish.setText(publish);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setGrid(GridBagConstraints gbc, int dx, int dy, int width, int height) {
		// TODO Auto-generated method stub
		gbc.gridx = dx;
		gbc.gridy = dy;
		gbc.gridwidth = width;
		gbc.gridheight = height;
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
					if(result.getString("lent.len_re_st").isEmpty() || result.getString("lent.len_re_st").equals("1")) {
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
	
	public class ComboboxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			String lib_name, sql;
			ResultSet rs;
			
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED) {
				lib_name = e.getItem().toString();
				sql = "SELECT * FROM `library WHERE '" + lib_name + "'";
				rs = db.getResultSet(sql);
				
				try {
					while(rs.next()) {
						mf.insert_lib_no(rs.getInt("lib_no"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
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
