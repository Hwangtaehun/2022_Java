package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import libraryDB.LbDB_book_Frame.researchButtonListener;
import libraryDB.LbDB_book_Frame.tableListener;

import java.sql.*;

public class LbDB_lent_Frame extends LbDB_main_Frame {
	private JPanel northPanel;
	private JTextField tf_research, tf_book_name, tf_mem_id;
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
	
	public class researchButtonListener implements ActionListener{

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
