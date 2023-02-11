package Allowance;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class DBA_Dialog extends JDialog{
	private DBA_TableMode tablemodel;
	private DBA_DAO stuDB;
	private JTable table;
	private String tableName, tableId;
	private int dataCount, selectedCol, num;
	private ResultSet result;
	private JComboBox <String> combobox;
	private String dataModel[];
	private JButton newBt, addBt, updateBt, deleteBt;
	private JTextField tf_Name;
	private String titleName;
	
	public DBA_Dialog() {}
	public DBA_Dialog(DBA_DAO stuDB, String tableName, String dataModel[]) {
		this.stuDB = stuDB;
		this.tableName = tableName;
		this.dataModel = dataModel;
		this.result = result;
		
		if(tableName.equals("Manager"))
		{
			tableId = "manid";
			titleName = "기 초 자 료";
		}
		else
		{
			tableId = "conid";
			titleName = "거 래 처";
		}
		
		initform();
	}
	
	void initform()
	{
		Container cpane = getContentPane();
		JPanel leftPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JLabel label;
		JButton bt;
		selectedCol = -1;
		GridBagLayout gbl = new GridBagLayout();
		leftPanel.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		setGrid(gbc, 0, 2, 1, 1);
		label = new JLabel("      "+ titleName +"       ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 2, 1, 1);
		combobox = new JComboBox<String>(new DefaultComboBoxModel<String>(dataModel));
		combobox.addItemListener(new comboBoxListener());
		gbl.setConstraints(combobox, gbc);
		leftPanel.add(combobox);
		setGrid(gbc, 0, 4, 1, 1);
		label = new JLabel("     제  목          ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 4, 1, 1);
		tf_Name = new JTextField(5);
		gbl.setConstraints(tf_Name, gbc);
		leftPanel.add(tf_Name);
		setGrid(gbc, 0, 6, 1, 1);
		label = new JLabel(" ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 0, 8, 1, 1);
		label = new JLabel(" ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 0, 9, 1, 1);
		bt = new JButton("처음");
		bt.addActionListener(new FirstButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		setGrid(gbc, 1, 9, 1, 1);
		bt = new JButton("이전");
		bt.addActionListener(new PreviousButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		setGrid(gbc, 2, 9, 1, 1);
		bt = new JButton("다음");
		bt.addActionListener(new NextButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		setGrid(gbc, 3, 9, 1, 1);
		bt = new JButton("마지막");
		bt.addActionListener(new EndButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);

		setGrid(gbc, 3, 0, 1, 1);
		newBt = new JButton("새자료");
		newBt.addActionListener(new newButtonListener());
		gbl.setConstraints(newBt, gbc);
		leftPanel.add(newBt);
		setGrid(gbc, 3, 1, 1, 1);
		addBt = new JButton("등록");
		addBt.addActionListener(new addButtonListener());
		gbl.setConstraints(addBt, gbc);
		leftPanel.add(addBt);
		setGrid(gbc, 3, 2, 1, 1);
		updateBt = new JButton("수정");
		updateBt.addActionListener(new updateButtonListener());
		gbl.setConstraints(updateBt, gbc);
		leftPanel.add(updateBt);
		setGrid(gbc, 3, 3, 1, 1);
		deleteBt = new JButton("삭제");
		deleteBt.addActionListener(new deleteButtonListener());
		gbl.setConstraints(deleteBt, gbc);
		leftPanel.add(deleteBt);
		setGrid(gbc, 3, 7, 1, 1);
		bt = new JButton("종료");
		bt.addActionListener(new exitButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		
		setEnabledButton(true);
		
		String columnName[] = {"번호", "제목"};
		tablemodel = new DBA_TableMode(columnName.length, columnName);
		table = new JTable(tablemodel);
		
		table.setPreferredScrollableViewportSize(new Dimension(235, 14*16));
		table.getSelectionModel().addListSelectionListener(new tableListener());
		JScrollPane scrollPane = new JScrollPane(table);
		centerPanel.add(scrollPane);
		
		cpane.add("West", leftPanel);
		cpane.add("Center", centerPanel);
		pack();
		LoadList();
		try {
			result.first();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		MoveData();
	}
	
	private void setEnabledButton(Boolean bool) {
		newBt.setEnabled(bool);
		addBt.setEnabled(!bool);
		updateBt.setEnabled(bool);
		deleteBt.setEnabled(bool);
	}
	
	private void setGrid(GridBagConstraints gbc, int dx, int dy, int width, int height) {
		gbc.gridx = dx;
		gbc.gridy = dy;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		
	}
	
	private void inputTable(int cnt, int id, String title) {
		table.setValueAt(id, cnt, 0);
		table.setValueAt(title, cnt, 1);
	}
	
	private void removeTableRow(int row) {
		table.setValueAt(null, row, 0);
		table.setValueAt(null, row, 1);
	}
	
	private void LoadList() {
		String sql;
		sql = "Select * FROM " + tableName;
		result = stuDB.getResultSet(sql);
		
		for(int i = 0; i < dataCount; i++) {
			removeTableRow(i);
		}
		try {
			for(dataCount = 0; result.next(); dataCount++) {
				inputTable(dataCount, result.getInt(tableId), result.getString("title"));
			}
			repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void MoveData() {
		try {
//			String superid = null;
//			int id = result.getInt(tableId);
//			superid = FindSuper(id , 0);
			String title = result.getString("title");
			combobox.setSelectedItem(title);
			tf_Name.setText(title);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	private int Lastkey(String sql, String key) {
		int num = 0;
		ResultSet rs = stuDB.getResultSet(sql);
		try {
			while(rs.next()) 
			{
				num = rs.getInt(key);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	private String FindSuper(int id, int num)
	{
		int temp = id/10000;
		String number = Integer.toString(temp);
		
		temp = id%10000/1000;
		number += Integer.toString(temp);
		
		temp = id%10000%1000/100;
		number += Integer.toString(temp);
		
		if(num == 0)
		{
			number += "00";
			
			String sql = "Select * From " + tableName + " Where " + tableId + " = " + number;
			System.out.println(sql);
			ResultSet rs = stuDB.getResultSet(sql);
			try {
				rs.next();
				String title = rs.getString("title");
				System.out.println(title);
				return title;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(num == 1)
		{
			number += "%";
			return number;
		}
		return "error";
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
					combobox.setSelectedItem(table.getValueAt(selectedCol, 0).toString());
					tf_Name.setText(table.getValueAt(selectedCol, 1).toString());
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
	
	class comboBoxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
		}
	}

	class newButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			combobox.setSelectedIndex(0);
			tf_Name.setText(null);
			setEnabledButton(false);
		}
	}
	
	class addButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql, superid;
			int id;
			
			if(tf_Name.getText().isEmpty()) {
				System.out.println("이름이 입력되지 않았습니다.");
				return;
			}
			sql = "Select * FROM " + tableName + " where = " + combobox.getSelectedItem().toString() + " order by id";
			id = Lastkey(sql, tableId);
			superid = FindSuper(id, 1);
			sql = "Select * FROM " + tableName + " where = " + superid + " order by id";
			id = Lastkey(sql, tableId) + 1;
			sql = " INSERT INTO" + tableName + "VALUES(" + id + "," + tf_Name.getText() + ")";
			System.out.println(sql);
			stuDB.Excute(sql);
			setEnabledButton(true);
		}
	}

	class updateButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql, code, title, superid; 
			int id;
			if(selectedCol == -1) {
				System.out.println("변경할 셀이 선택되지 않았습니다.");
				return;
			}
			code = table.getValueAt(selectedCol, 0).toString();
			title = table.getValueAt(selectedCol, 1).toString();
			if(title.equals(combobox.getSelectedItem().toString())) {
				id = Integer.parseInt(code);
			}
			else {
				sql = "Select * FROM " + tableName + " where = " + combobox.getSelectedItem().toString() + " order by id";
				id = Lastkey(sql, tableId);
				superid = FindSuper(id, 1);
				sql = "Select * FROM " + tableName + " where = " + superid + " order by id";
				id = Lastkey(sql, tableId) + 1;
			}
			
			
			sql = " UPDATE " + tableName + " SET " + tableId + " = " + id + ", title = " + tf_Name.getText() + " WHERE " + tableId + " = " + code;
			System.out.println(sql);
			stuDB.Excute(sql);
		}
	}

	class deleteButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql, code;
			if(selectedCol == -1) {
				System.out.println("삭제할 셀이 선택되지 않았습니다.");
				return;
			}
			code = table.getValueAt(selectedCol, 0).toString();
			sql = "DELETE FROM" + tableName + "WHERE " + tableId +" = " + code;
			System.out.println("삭제 쿼리문 확인" + sql);
			stuDB.Excute(sql);
		}
	}
	
	class FirstButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				result.first();
			}catch(SQLException e1) {
				System.err.println("SQLException " + e1.getMessage());
			}
			MoveData();
		}
	}
	
	class PreviousButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				result.previous();
				if(result.isBeforeFirst())
					result.first();
			}catch(SQLException e1) {
				System.err.println("SQLException " + e1.getMessage());
			}
			MoveData();
		}
	}

	class NextButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				result.next();
				if(result.isAfterLast())
					result.last();
			}catch(SQLException e1) {
				System.err.println("SQLException " + e1.getMessage());
			}
			MoveData();
		}
	}

	class EndButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				result.last();
			}catch(SQLException e1) {
				System.err.println("SQLException " + e1.getMessage());
			}
			MoveData();
		}
	}
	
	class exitButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			stuDB.Close();
			System.exit(0);
		}
	}
}
