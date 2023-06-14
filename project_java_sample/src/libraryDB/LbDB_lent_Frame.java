package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.time.*;

public class LbDB_lent_Frame extends LbDB_main_Frame {
	private JPanel northPanel;
	private Combobox_Manager lib_research, lib_select;
	private JTextField tf_research, tf_book_name, tf_mem_id, tf_lent_re_date, tf_memo;
	private JRadioButton rb_lent, rb_return, rb_etc, rb_normal, rb_extend;
	private ButtonGroup gr_return, gr_extend;
	private int ex, st;
	private String sortsql;
	
	public LbDB_lent_Frame() {}
	public LbDB_lent_Frame(LbDB_DAO db, Client cl, String title) {
		this.db = db;
		this.cl = cl;
		menu_title = title;
		pk = cl.primarykey();
		state = cl.state();
		fk = new foreignkey();
		menuform();
		Initform();
		baseform();
		
		if(state == 1) {
			
		}
		else {
			if(menu_title.equals("대출중도서")) {
				sql = "SELECT * FROM `library`, `book`, `material`, `member`, `lent` WHERE material.lib_no = library.lib_no" +
					  " AND material.book_no = book.book_no AND lent.mat_no = material.mat_no AND lent.mem_no = member.mem_no" + 
					  " AND lent.mem_no = " + pk + " AND lent.lent_re_state = 0";
				String columnName[] = {"책 이름", "소장도서관", "대출일", "반납일예정"};
				tableform(columnName);
			}
			else {
				sql = "SELECT * FROM `library`, `book`, `material`, `member`, `lent` WHERE material.lib_no = library.lib_no" +
					  " AND material.book_no = book.book_no AND lent.mat_no = material.mat_no AND lent.mem_no = member.mem_no" + 
					  " AND lent.mem_no = " + pk;
				String columnName[] = {"책 이름", "소장도서관", "대출일", "반납일", "반납상태"};
				tableform(columnName);
			}
			
		}
	}
	
	private void baseform() {
		JPanel titlePanel, researchPanel;
		JLabel label;
		
		titlePanel = new JPanel();
		label = new JLabel(menu_title);
		titlePanel.add(label);
		
		researchPanel = new JPanel();
		if(state == 1) {
			JComboBox <String> lib_Box = null;
			
			label = new JLabel("도서관");
			researchPanel.add(label);
			lib_research = new Combobox_Manager(lib_Box, "library", "lib_no");
			lib_Box = lib_research.combox;
			researchPanel.add(lib_Box);
		}
		label = new JLabel("검색");
		researchPanel.add(label);
		tf_research = new JTextField(20);
		researchPanel.add(tf_research);
		researchBt = new JButton();
		researchBt.addActionListener(new researchButtonListener());
		researchPanel.add(researchBt);
		
		northPanel = new JPanel();
		northPanel.add("North", titlePanel);
		northPanel.add("South", researchPanel);
	}
	
	private void managerform() {
		JLabel label;
		JButton bt;
		JPanel extendPanel;
		
		setGrid(gbc,1,1,1,1);
		label = new JLabel("    "+ menu_title + "   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,0,2,1,1);
		label = new JLabel("    자료이름        ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,2,1,1);
		tf_book_name = new JTextField(10);
		gbl.setConstraints(tf_book_name, gbc);
		leftPanel.add(tf_book_name);
		setGrid(gbc,2,2,1,1);
		bt = new JButton("자료검색");
		bt.addActionListener(new materialButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		setGrid(gbc,0,2,1,1);
		label = new JLabel("    회원아이디       ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,2,1,1);
		tf_mem_id = new JTextField(10);
		gbl.setConstraints(tf_mem_id, gbc);
		leftPanel.add(tf_mem_id);
		setGrid(gbc,2,2,1,1);
		bt = new JButton("회원검색");
		bt.addActionListener(new memberButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		setGrid(gbc,0,3,1,1);
		label = new JLabel("    연장          ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,3,1,1);
		extendPanel = new JPanel();
		gr_extend = new ButtonGroup();
		rb_normal = new JRadioButton("예", true);
		rb_normal.addActionListener(new radiobuttonListener());
		rb_normal.addItemListener(new radiobuttonListener());
		rb_normal.setActionCommand("ex-7");
		gr_extend.add(rb_normal);
		extendPanel.add(rb_normal);
		rb_extend = new JRadioButton("아니오", false);
		rb_extend.addActionListener(new radiobuttonListener());
		rb_extend.addItemListener(new radiobuttonListener());
		rb_extend.setActionCommand("ex-0");
		gr_extend.add(rb_extend);
		extendPanel.add(rb_extend);
		gbl.setConstraints(extendPanel, gbc);
		leftPanel.add(extendPanel);
	}
	
	private void editform() {
		JLabel label;
		JButton bt;
		JPanel extendPanel;
		JComboBox <String> lib_Box = null;
		
		setGrid(gbc,0,4,1,1);
		label = new JLabel("    반납도서관       ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,4,1,1);
		lib_select = new Combobox_Manager(lib_Box, "library", "lib_no", true);
		lib_Box = lib_select.combox;
		gbl.setConstraints(lib_Box, gbc);
		leftPanel.add(lib_Box);
		setGrid(gbc,0,5,1,1);
		label = new JLabel("    반납일         ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,5,1,1);
		tf_lent_re_date = new JTextField(10);
		gbl.setConstraints(tf_lent_re_date, gbc);
		leftPanel.add(tf_lent_re_date);
		setGrid(gbc,2,5,1,1);
		bt = new JButton("오늘");
		bt.addActionListener(new todayButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		setGrid(gbc,0,6,1,1);
		label = new JLabel("    반납상태        ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,6,1,1);
		extendPanel = new JPanel();
		gr_return = new ButtonGroup();
		rb_lent = new JRadioButton("대출중", true);
		rb_lent.addActionListener(new radiobuttonListener());
		rb_lent.addItemListener(new radiobuttonListener());
		rb_lent.setActionCommand("st-0");
		gr_return.add(rb_lent);
		extendPanel.add(rb_lent);
		rb_return = new JRadioButton("반납", true);
		rb_return.addActionListener(new radiobuttonListener());
		rb_return.addItemListener(new radiobuttonListener());
		rb_return.setActionCommand("st-1");
		gr_return.add(rb_return);
		extendPanel.add(rb_return);
		rb_etc = new JRadioButton("기타", true);
		rb_etc.addActionListener(new radiobuttonListener());
		rb_etc.addItemListener(new radiobuttonListener());
		rb_etc.setActionCommand("st-2");
		gr_return.add(rb_etc);
		extendPanel.add(rb_etc);
		gbl.setConstraints(extendPanel, gbc);
		leftPanel.add(extendPanel);
		setGrid(gbc,0,7,1,1);
		label = new JLabel("    메모          ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,7,1,1);
		tf_memo = new JTextField(20);
		gbl.setConstraints(tf_memo, gbc);
		leftPanel.add(tf_memo);
		setGrid(gbc,0,8,1,1);
		bt = new JButton("삭제");
		bt.addActionListener(new deleteButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		setGrid(gbc,1,8,1,1);
		bt = new JButton("수정");
		bt.addActionListener(new updateButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		setGrid(gbc,2,8,1,1);
		bt = new JButton("공백");
		bt.addActionListener(new clearButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
	}
	
	private void addform() {
		JButton bt;
		
		setGrid(gbc,2,4,1,1);
		bt = new JButton("추가");
		bt.addActionListener(new addButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
	}
	
	private void tableform(String columnName[]) {
		String now_sql;
		
		tablemodel = new LbDB_TableMode(columnName.length, columnName);
		table = new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(700, 14*16));
		table.getSelectionModel().addListSelectionListener(new tableListener());
		JScrollPane scrollPane = new JScrollPane(table);
		centerPanel.add(scrollPane);
		
		sortsql = " ORDER BY `mem_name`";
		now_sql = sql + sortsql;
		LoadList(now_sql);
	}
	
	private void tablefocus() {
		try {
			result.first();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MoveData();
	}
	
	private void baseform_fianl() {
		cpane.add("North", northPanel);
		cpane.add("West", leftPanel);
		cpane.add("Center", centerPanel);
		//cpane.add("South", southPanel);
		pack();
	}
	
	private void removeTableRow(int row) {
		if(menu_title.equals("대출중도서")) {
			table.setValueAt(null, row, 0);
			table.setValueAt(null, row, 1);
			table.setValueAt(null, row, 2);
			table.setValueAt(null, row, 3);
		}
		else {
			table.setValueAt(null, row, 0);
			table.setValueAt(null, row, 1);
			table.setValueAt(null, row, 2);
			table.setValueAt(null, row, 3);
			table.setValueAt(null, row, 4);
			table.setValueAt(null, row, 5);
		}
	}
	
	private void MoveData() {
		
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
	
	private boolean warning() {
		boolean bool;
		if(tf_book_name.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "책정보를 찾아주세요.", "추가 오류", JOptionPane.WARNING_MESSAGE);
			bool = false;
		}
		else if(tf_mem_id.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "회원정보를 찾아주세요.", "추가 오류", JOptionPane.WARNING_MESSAGE);
			bool = false;
		}
		else {
			bool = true;
		}
		
		return bool;
	}
	
	public class researchButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String basic_sql, now_sql, book_sql, mem_sql;
			String research;
			
			research = tf_research.getText();
			basic_sql = "SELECT * FROM lent, member, material, book, library "
					+ "WHERE lent.mat_no = material.mat_no AND lent.mem_no = member.mem_no AND material.book_no = book.book_no "
					+ "AND material.lib_no = library.lib_no ";
			book_sql = "AND book.book_name LIKE '%" + research + "%'";
			mem_sql = "AND member.mem_id LIKE '%" + research + "%'";
			
			
			if(state == 1) {
				String str;
				str = "AND `material`.`lib_no` = " + lib_research.foreignkey();
				now_sql = basic_sql + str + book_sql + " UNION " + basic_sql + mem_sql;
			}
			else {
				now_sql = basic_sql + book_sql + " UNION " + basic_sql + mem_sql;
			}
		}
	}
	
	public class addButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String now_sql;
			LocalDate len_date;
			
			if(warning()) {
				len_date = LocalDate.now(); 
				now_sql = "INSERT INTO `lent` ( mat_no, mem_no, len_ex, len_date ) VALUES(" + fk.call_mat_no() + ", "
						+ fk.call_mem_no() + ", " + ex + ", '" + len_date + "')";
				System.out.println(now_sql);
				db.Excute(now_sql);
				now_sql = sql + sortsql;
				LoadList(now_sql);
			}
		}
	}
	
	public class updateButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(selectedCol == -1) {
				System.out.println("변경할 셀이 선택되지 않았습니다.");
				return;
			}
		}
	}
	
	public class deleteButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(selectedCol == -1) {
				System.out.println("변경할 셀이 선택되지 않았습니다.");
				return;
			}
		}
	}
	
	public class clearButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class materialButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LbDB_material_Frame mat = new LbDB_material_Frame("자료검색", tf_book_name, fk);
			mat.setVisible(true);
		}
	}
	
	public class memberButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LbDB_mem_info_Frame men = new LbDB_mem_info_Frame("회원검색", tf_mem_id, fk);
			men.setVisible(true);
		}
		
	}
	
	public class todayButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LocalDate len_date;
			len_date = LocalDate.now(); 
			tf_lent_re_date.setText(len_date.toString());
		}
	}
	
	public class radiobuttonListener implements ItemListener, ActionListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd, str_array[];
			
			cmd = e.getActionCommand();
			str_array = cmd.split("-");
			
			for(int i = 0; i < str_array.length; i++) {
				System.out.print(str_array[i]);
			}
			System.out.println();
			
			if(str_array[0].equals("state")) {
				ex = Integer.parseInt(str_array[1]);
			}
			else {
				st = Integer.parseInt(str_array[1]);
			}
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
					/*
					 * tf_bookname.setText(table.getValueAt(selectedCol, 0).toString());
					 * tf_author.setText(table.getValueAt(selectedCol, 1).toString());
					 * tf_publish.setText(table.getValueAt(selectedCol, 2).toString());
					 * tf_price.setText(table.getValueAt(selectedCol, 3).toString());
					 * tf_year.setText(table.getValueAt(selectedCol, 4).toString());
					 */
					try {
						result.absolute(selectedCol + 1);
						//MoveData();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					repaint();
				}
			}
		}
	}
}