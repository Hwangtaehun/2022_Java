package Allowance;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class DBA_Frame extends JFrame{
	private DBA_DAO stuDB;
	private DBA_TableMode tablemodel;
	private ResultSet result;
	private JTable table;
	private int dataCount, selectedCol, sw = 1;
	private JTextField tf_Price, tf_Date, tf_Inform;
	private JButton newBt, addBt, updateBt, deleteBt;
	
	public DBA_Frame() {}
	public DBA_Frame(DBA_DAO db) {
		super();
		stuDB = db;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String sql = "Select Incomes.deposit, Expenses.spend, Incomes.incomedate, Expenses.expensedate, Incomes.in_inform, Expenses.ex_inform, Banks.balance "
				+ "From Banks left join Expenses on Banks.expenseid = Expenses.expenseid left join Incomes on Banks.incomeid = Incomes.incomeid";
		result = stuDB.getResultSet(sql);
		
		initForm();
	}
	
	void initForm() {
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
		setGrid(gbc, 0, 1, 1, 1);
		label = new JLabel("      금  액     ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 1, 1, 1);
		tf_Price = new JTextField(5);
		gbl.setConstraints(tf_Price, gbc);
		leftPanel.add(tf_Price);
		setGrid(gbc, 0, 2, 1, 1);
		label = new JLabel("      날 짜     ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 2, 1, 1);
		tf_Date = new JTextField(10);
		gbl.setConstraints(tf_Date, gbc);
		leftPanel.add(tf_Date);
		setGrid(gbc, 0, 3, 1, 1);
		label = new JLabel("      내  역     ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 3, 1, 1);
		tf_Inform = new JTextField(5);
		gbl.setConstraints(tf_Inform, gbc);
		leftPanel.add(tf_Inform);
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
		setGrid(gbc, 3, 5, 1, 1);
		bt = new JButton("정렬");
		bt.addActionListener(new sortButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		setGrid(gbc, 3, 7, 1, 1);
		bt = new JButton("종료");
		bt.addActionListener(new exitButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		
		setEnabledButton(true);
		
		String columnName[] = {"수입", "지출", "수입날짜", "지출날짜", "수입내역", "지출내역", "잔고"};
		tablemodel = new DBA_TableMode(columnName.length, columnName);
		table = new JTable(tablemodel);
		
		table.setPreferredScrollableViewportSize(new Dimension(470, 14*16));
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
	
	private void inputTable(int cnt, int deposit, int spend, Date incomedate, Date expensedate, String in_inform, String ex_inform, int balance) {
		table.setValueAt(deposit, cnt, 0);
		table.setValueAt(spend, cnt, 1);
		table.setValueAt(incomedate, cnt, 2);
		table.setValueAt(expensedate, cnt, 3);
		table.setValueAt(in_inform, cnt, 4);
		table.setValueAt(ex_inform, cnt, 5);
		table.setValueAt(balance, cnt, 6);
	}
	
	private void removeTableRow(int row) {
		table.setValueAt(null, row, 0);
		table.setValueAt(null, row, 1);
		table.setValueAt(null, row, 2);
		table.setValueAt(null, row, 3);
		table.setValueAt(null, row, 4);
		table.setValueAt(null, row, 5);
		table.setValueAt(null, row, 6);
	}
	
	private void LoadList() {
		String sql;
		if(sw == 1)
		{
			sql = "Select Incomes.deposit, Expenses.spend, Incomes.incomedate, Expenses.expensedate, Incomes.in_inform, Expenses.ex_inform, Banks.balance "
					  + "From Banks left join Expenses on Banks.expenseid = Expenses.expenseid left join Incomes on Banks.incomeid = Incomes.incomeid";
				result = stuDB.getResultSet(sql);
		}
		else
		{
			sql = "Select Incomes.deposit, Expenses.spend, Incomes.incomedate, Expenses.expensedate, Incomes.in_inform, Expenses.ex_inform, Banks.balance "
					  + "From Banks left join Expenses on Banks.expenseid = Expenses.expenseid left join Incomes on Banks.incomeid = Incomes.incomeid";
				result = stuDB.getResultSet(sql);
		}
		
		for(int i = 0; i < dataCount; i++) {
			removeTableRow(i);
		}
		try {
			for(dataCount = 0; result.next(); dataCount++) {
				inputTable(dataCount, result.getInt("Incomes.deposit"), result.getInt("Expenses.spend"), result.getDate("Incomes.incomedate"), result.getDate("Expenses.expensedate"),
						   result.getString("Incomes.in_inform"), result.getString("Expenses.ex_inform"), result.getInt("Banks.balance"));
			}
			repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void balance() {
		int tot = 0, rank1 = 0, rank2 = 0;
		String sql;
		ResultSet rs = stuDB.getResultSet("Select * FROM Score order by nTotal desc ");
		try {
			while(rs.next()) {
				rank1++;
				if(tot != rs.getInt("nTotal")) {
					tot = rs.getInt("nTotal");
					rank2 = rank1;
				}
				sql = "UPDATE Score Set nRank = " + rank2 + " where strCode ='" + rs.getString("strCode") + "'";
				stuDB.Excute(sql);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void MoveData() {
		try {
			int check = result.getInt("Incomes.deposit");
			if(check == 0) {
				String price = String.valueOf(result.getInt("Expenses.spend"));
				String date = String.valueOf(result.getDate("Expenses.expensedate"));
				String inform = result.getString("Expenses.ex_inform");
				tf_Price.setText(price);
				tf_Date.setText(date);
				tf_Inform.setText(inform);
			}
			else {
				String price = String.valueOf(result.getInt("Incomes.deposit"));
				String date = String.valueOf(result.getDate("Incomes.incomedate"));
				String inform = result.getString("Incomes.in_inform");
				tf_Price.setText(price);
				tf_Date.setText(date);
				tf_Inform.setText(inform);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public int Lastkey(String sql, String key) {
		int num = 0;
		Statement smt = null;
		ResultSet rs;
		
		try {
			
			rs = smt.executeQuery(sql);
			while(result.next()) 
			{
				num = rs.getInt(key);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return num + 1;
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
					String text = table.getValueAt(selectedCol, 0).toString();
					if(text.equals("0"))
					{
						tf_Price.setText(table.getValueAt(selectedCol, 1).toString());
						tf_Date.setText(table.getValueAt(selectedCol, 3).toString());
						tf_Inform.setText(table.getValueAt(selectedCol, 5).toString());
					}
					else 
					{
						tf_Price.setText(table.getValueAt(selectedCol, 0).toString());
						tf_Date.setText(table.getValueAt(selectedCol, 2).toString());
						tf_Inform.setText(table.getValueAt(selectedCol, 4).toString());
					}
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
	
	class newButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			tf_Price.setText(null);
			tf_Date.setText(null);
			tf_Inform.setText(null);
			setEnabledButton(false);
		}
	}

	class addButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql, check;
			int bankid, foreignid;
			
			if(tf_Price.getText().isEmpty()) {
				System.out.println("번호기 입력되지 않았습니다.");
				return;
			}
			check = tf_Price.getText();
			bankid = Lastkey("Select * FROM Bank order by bankid", "Bank.bankid");
			
			if(Integer.parseInt(check) > 0)
			{
				foreignid = Lastkey("Select * FROM Incomes order by incomeid", "Incomes.Incomes");
				sql = " INSERT INTO Incomes (incomeid,  deposit, incomedate, in_inform) VALUES(" + foreignid + "," + tf_Price.getText() 
					  + "STR_TO_DATE('" + tf_Date + "','%Y-%m-%d'),'" + tf_Inform + "')";
				System.out.println(sql);
				stuDB.Excute(sql);
				sql = " INSERT INTO Banks (bankid, incomeid) VALUES(" + bankid + "," + foreignid + ")";
			}
			else 
			{
				foreignid = Lastkey("Select * FROM Expenses order by incomeid", "Expenses.expenseid");
				sql = " INSERT INTO Expenses (expenseid, spend, expensedate, ex_inform) VALUES(" + foreignid + "," + tf_Price.getText() 
				  + "STR_TO_DATE('" + tf_Date + "','%Y-%m-%d'),'" + tf_Inform + "')";
				System.out.println(sql);
				stuDB.Excute(sql);
				sql = " INSERT INTO Banks (bankid, expenseid) VALUES(" + bankid + "," + foreignid + ")";
			}
			System.out.println(sql);
			stuDB.Excute(sql);
			balance();
			LoadList();
			setEnabledButton(true);
		}
	}

	class updateButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql, code, check;
			if(selectedCol == -1) {
				System.out.println("변경할 셀이 선택되지 않았습니다.");
				return;
			}
			check = tf_Price.getText();
			if(Integer.parseInt(check) > 0)
			{
				sql = " INSERT INTO Incomes (incomeid,  deposit, incomedate, in_inform) VALUES(" + ")";
			}
			else 
			{
				sql = " INSERT INTO Expenses (expenseid, spend, expensedate, ex_inform) VALUES(" + ")";
			}
//			code = table.getValueAt(selectedCol, 0).toString();
//			int total = Integer.parseInt(tf_Kor.getText()) + Integer.parseInt(tf_Mat.getText()) + Integer.parseInt(tf_Eng.getText());
//			sql = "UPDATE Score SET strName = '" + tf_Name.getText() + "', nKor = " + tf_Kor.getText() + ", nMat = " + tf_Mat.getText() + 
//					", nEng = " + tf_Eng.getText() + ", nTotal = " + Integer.toString(total) + ", dAverage = " + Double.toString(total/3.0) + 
//					" WHERE strCode = '" + code + "'";
//			System.out.println(sql);
//			stuDB.Excute(sql);
			balance();
			LoadList();
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
			sql = "DELETE FROM Score WHERE strCode = '" + code + "'";
			stuDB.Excute(sql);
			balance();
			LoadList();
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

	class sortButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			sw *= -1;
			LoadList();
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


