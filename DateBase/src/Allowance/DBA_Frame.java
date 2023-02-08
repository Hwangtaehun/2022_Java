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
		
		String columnName[] = {"번호", "수입", "지출", "수입날짜", "지출날짜", "수입내역", "지출내역", "잔고"};
		tablemodel = new DBA_TableMode(columnName.length, columnName);
		table = new JTable(tablemodel);
		
		table.setPreferredScrollableViewportSize(new Dimension(530, 14*16));
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
	
	private void inputTable(int cnt, int bankid, int deposit, int spend, Date incomedate, Date expensedate, String in_inform, String ex_inform, int balance) {
		table.setValueAt(bankid, cnt, 0);
		table.setValueAt(deposit, cnt, 1);
		table.setValueAt(spend, cnt, 2);
		table.setValueAt(incomedate, cnt, 3);
		table.setValueAt(expensedate, cnt, 4);
		table.setValueAt(in_inform, cnt, 5);
		table.setValueAt(ex_inform, cnt, 6);
		table.setValueAt(balance, cnt, 7);
	}
	
	private void removeTableRow(int row) {
		table.setValueAt(null, row, 0);
		table.setValueAt(null, row, 1);
		table.setValueAt(null, row, 2);
		table.setValueAt(null, row, 3);
		table.setValueAt(null, row, 4);
		table.setValueAt(null, row, 5);
		table.setValueAt(null, row, 6);
		table.setValueAt(null, row, 7);
	}
	
	private void LoadList() {
		String sql;
		if(sw == 1)
		{
			sql = "Select Banks.bankid, Incomes.deposit, Expenses.spend, Incomes.incomedate, Expenses.expensedate, Incomes.in_inform, Expenses.ex_inform, Banks.balance "
					  + "From Banks left join Expenses on Banks.expenseid = Expenses.expenseid left join Incomes on Banks.incomeid = Incomes.incomeid";
				result = stuDB.getResultSet(sql);
		}
		else
		{
			sql = "Select Banks.bankid, Incomes.deposit, Expenses.spend, Incomes.incomedate, Expenses.expensedate, Incomes.in_inform, Expenses.ex_inform, Banks.balance "
					  + "From Banks left join Expenses on Banks.expenseid = Expenses.expenseid left join Incomes on Banks.incomeid = Incomes.incomeid";
				result = stuDB.getResultSet(sql);
		}
		
		for(int i = 0; i < dataCount; i++) {
			removeTableRow(i);
		}
		try {
			for(dataCount = 0; result.next(); dataCount++) {
				inputTable(dataCount, result.getInt("Banks.bankid"), result.getInt("Incomes.deposit"), result.getInt("Expenses.spend"), result.getDate("Incomes.incomedate"), result.getDate("Expenses.expensedate"),
						   result.getString("Incomes.in_inform"), result.getString("Expenses.ex_inform"), result.getInt("Banks.balance"));
			}
			repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void balance() {
		int num = 0, tot = 0;
		String sql = "Select Banks.bankid, Incomes.deposit, Expenses.spend, Incomes.incomedate, Expenses.expensedate, Incomes.in_inform, Expenses.ex_inform, Banks.balance "
				  + "From Banks left join Expenses on Banks.expenseid = Expenses.expenseid left join Incomes on Banks.incomeid = Incomes.incomeid order by Banks.bankid";
		ResultSet rs = stuDB.getResultSet(sql);
		try {
			while(rs.next()) {
				num++;
				if(num != rs.getInt("Banks.bankid"))
				{
					tot += rs.getInt("Incomes.deposit");
					tot += rs.getInt("Expenses.spend");
					sql = "UPDATE Banks Set balance = " + tot + " where bankid = " + rs.getString("Banks.bankid");
					stuDB.Excute(sql);
					sql = "UPDATE Banks Set bankid = " + num + " where bankid = " + rs.getString("Banks.bankid");
					stuDB.Excute(sql);
				}
				tot += rs.getInt("Incomes.deposit");
				tot += rs.getInt("Expenses.spend");
				sql = "UPDATE Banks Set balance = " + tot + " where bankid = " + rs.getString("Banks.bankid");
				stuDB.Excute(sql);
//				else 
//				{
//					if(rs.getInt("Banks.balance") == 0)
//					{
//						tot += rs.getInt("Incomes.deposit");
//						tot += rs.getInt("Expenses.spend");
//						sql = "UPDATE Banks Set balance = " + tot + " where bankid = " + rs.getString("Banks.bankid");
//						stuDB.Excute(sql);
//					}
//					else 
//					{
//						tot = rs.getInt("Banks.balance");
//					}
//				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void MoveData() {
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
	
	private String Lastkey(String sql, String key) {
		int num = 0;
		String fianl = null;
		ResultSet rs = stuDB.getResultSet(sql);
		try {
			while(rs.next()) 
			{
				num = rs.getInt(key);
				num++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		fianl = Integer.toString(num);
		
		return fianl;
	}
	
	private String Foreignkey(String bankid) {
		String sql  = "Select * FROM Banks WHERE bankid = " + bankid;
		String fianl = null;
		
		ResultSet rs = stuDB.getResultSet(sql);
		try {
			rs.next();
			if(rs.getInt("incomeid") == 0) {
				fianl = Integer.toString(rs.getInt("expenseid"));
			}
			else {
				fianl = Integer.toString(rs.getInt("incomeid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fianl;
	}
	
	private String CheckTable(String bankid) {
		String fianl = null;
		String sql  = "Select * FROM Banks WHERE bankid = " + bankid;
		ResultSet rs = stuDB.getResultSet(sql);
		try {
			rs.next();
			if(rs.getInt("incomeid") == 0) {
				fianl = "Expenses";
			}
			else {
				fianl = "Incomes";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fianl;
	}
	
	private String CheckDate(String date) {
		String year = null, month = null, day = null, fianl = null;
		String[] array_word;
		boolean number = false;
		int count = date.length();
		array_word = date.split("");
		
		number = CheckNumber(date);
		if(number) {
			if(count == 8)
			{
				for(int i = 0; i < 4; i++) {
					if(i == 0)
						year = array_word[i];
					else
						year += array_word[i];
				}
				for(int i = 4 ; i < 6; i++) {
					if(i == 4)
						month = array_word[i];
					else
						month += array_word[i];
				}
				for(int i = 6; i < 8; i++) {
					if(i == 6)
						day = array_word[i];
					else
						day += array_word[i];
				}
			}
			else if(count == 6) 
			{
				year = "20";
				for(int i = 0; i < 2; i++) {
					year += array_word[i];
				}
				for(int i = 2 ; i < 4; i++) {
					if(i == 2)
						month = array_word[i];
					else
						month += array_word[i];
				}
				for(int i = 4; i < 6; i++) {
					if(i == 4)
						day = array_word[i];
					else
						day += array_word[i];
				}
			}
			else {
				fianl = "error";
				return fianl;
			}
		}
		else {
			if(count == 8 ) 
			{
				year = "20";
				for(int i = 0; i < 2; i++) {
					year += array_word[i];
				}
				for(int i = 3 ; i < 5; i++) {
					if(i == 3)
						month = array_word[i];
					else
						month += array_word[i];
				}
				for(int i = 6; i < 8; i++) {
					if(i == 6)
						day = array_word[i];
					else
						day += array_word[i];
				}
			}
			else if(count == 10)
			{
				for(int i = 0; i < 4; i++) {
					if(i == 0)
						year = array_word[i];
					else
						year += array_word[i];
				}
				for(int i = 5; i < 7; i++) {
					if(i == 5)
						month = array_word[i];
					else
						month += array_word[i];
				}
				for(int i = 8; i < 10; i++) {
					if(i == 8)
						day = array_word[i];
					else
						day += array_word[i];
				}
			}
			else {
				fianl = "error";
				return fianl;
			}
		}
		fianl = year + "-" + month + "-" + day;
		return fianl;
	}
	
	private boolean CheckNumber(String date) {
		try {
		      Integer.parseInt(date);
		      return true;
		    } catch (NumberFormatException ex) {
		      return false;
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
					String text = table.getValueAt(selectedCol, 1).toString();
					if(text.equals("0"))
					{
						tf_Price.setText(table.getValueAt(selectedCol, 2).toString());
						tf_Date.setText(table.getValueAt(selectedCol, 4).toString());
						tf_Inform.setText(table.getValueAt(selectedCol, 6).toString());
					}
					else
					{
						tf_Price.setText(table.getValueAt(selectedCol, 1).toString());
						tf_Date.setText(table.getValueAt(selectedCol, 3).toString());
						tf_Inform.setText(table.getValueAt(selectedCol, 5).toString());
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
			String sql, check, bankid, foreignid, date;
			
			if(tf_Price.getText().isEmpty()) {
				System.out.println("번호기 입력되지 않았습니다.");
				return;
			}
			check = tf_Price.getText();
			bankid = Lastkey("Select * FROM Banks order by bankid", "Banks.bankid");
			date = CheckDate(tf_Date.getText());
			
			
			if(Integer.parseInt(check) > 0)
			{
				foreignid = Lastkey("Select * FROM Incomes order by incomeid", "Incomes.Incomeid");
				sql = " INSERT INTO Incomes (incomeid,  deposit, incomedate, in_inform) VALUES(" + foreignid + "," + tf_Price.getText() + ","
					  + "STR_TO_DATE('" + date + "','%Y-%m-%d'),'" + tf_Inform.getText() + "')";
				System.out.println(sql);
				stuDB.Excute(sql);
				sql = " INSERT INTO Banks (bankid, incomeid) VALUES(" + bankid + "," + foreignid + ")";
			}
			else 
			{
				foreignid = Lastkey("Select * FROM Expenses order by expenseid", "Expenses.expenseid");
				sql = " INSERT INTO Expenses (expenseid, spend, expensedate, ex_inform) VALUES(" + foreignid + "," + tf_Price.getText() + ","
				  + "STR_TO_DATE('" + date + "','%Y-%m-%d'),'" + tf_Inform.getText() + "')";
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
			String sql, code, checkprice, checkkey, key, date;
			if(selectedCol == -1) {
				System.out.println("변경할 셀이 선택되지 않았습니다.");
				return;
			}
			code = table.getValueAt(selectedCol, 0).toString();
			key = Foreignkey(code);
			checkkey = CheckTable(code);
			checkprice = tf_Price.getText();
			date = CheckDate(tf_Date.getText());
			if(checkkey.equals("Incomes")) //수입
			{
				if(Integer.parseInt(checkprice) < 0) 
				{
					String expenseid= Lastkey("Select * FROM Expenses order by expenseid", "Expenses.expenseid");
					
					sql = "DELETE FROM Banks WHERE bankid = '" + code + "'";
					System.out.println(sql);
					stuDB.Excute(sql);
					
					sql = "DELETE FROM Incomes WHERE incomeid = '" + key + "'";
					System.out.println(sql);
					stuDB.Excute(sql);
					
					sql = " INSERT INTO Expenses (expenseid, spend, expensedate, ex_inform) VALUES(" + expenseid + "," + tf_Price.getText() + ","
					  + "STR_TO_DATE('" + date + "','%Y-%m-%d'),'" + tf_Inform.getText() + "')";
					System.out.println(sql);
					stuDB.Excute(sql);
					
					sql = " INSERT INTO Banks (bankid, expenseid) VALUES(" + code + "," + expenseid + ")";
					System.out.println(sql);
					stuDB.Excute(sql);
				}
				else 
				{
					sql = " UPDATE Incomes SET deposit = "+ tf_Price.getText() + ", incomedate = STR_TO_DATE('" + date + "','%Y-%m-%d')" +", in_inform = '" + tf_Inform.getText() + "' WHERE incomeid =" + key;
					System.out.println(sql);
					stuDB.Excute(sql);
				}
			}
			else 
			{
				if(Integer.parseInt(checkprice) > 0) //지출
				{
					String incomeid = Lastkey("Select * FROM Incomes order by incomeid", "Incomes.incomeid");
					
					sql = "DELETE FROM Banks WHERE bankid = '" + code + "'";
					System.out.println(sql);
					stuDB.Excute(sql);
					
					sql = "DELETE FROM Expenses WHERE expenseid = '" + key + "'";
					System.out.println(sql);
					stuDB.Excute(sql);
					
					sql = " INSERT INTO Incomes (incomeid,  deposit, incomedate, in_inform) VALUES(" + incomeid + "," + tf_Price.getText() + ","
					  + "STR_TO_DATE('" + date + "','%Y-%m-%d'),'" + tf_Inform.getText() + "')";
					System.out.println(sql);
					stuDB.Excute(sql);
					
					sql = " INSERT INTO Banks (bankid, incomeid) VALUES(" + code + "," + incomeid + ")";
					System.out.println(sql);
					stuDB.Excute(sql);
				}
				else
				{
					sql = " UPDATE Expenses SET spend = "+ tf_Price.getText() + ", expensedate = STR_TO_DATE('" + date + "','%Y-%m-%d')" +", ex_inform = '" + tf_Inform.getText() + "' WHERE expenseid =" + code;
					System.out.println(sql);
					stuDB.Excute(sql);
				}
			}
			balance();
			LoadList();
		}
	}

	class deleteButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql, code, key, tablename, keyname;
			if(selectedCol == -1) {
				System.out.println("삭제할 셀이 선택되지 않았습니다.");
				return;
			}
			code = table.getValueAt(selectedCol, 0).toString();
			key = Foreignkey(code);
			tablename = CheckTable(code);
			
			if(tablename.equals("Incomes"))
				keyname = "incomeid";
			else
				keyname = "expenseid";
			
			sql = "DELETE FROM Banks WHERE bankid = " + code;
			System.out.println("삭제 쿼리문 확인" + sql);
			stuDB.Excute(sql);
			
			sql = "DELETE FROM " + tablename + " WHERE "+ keyname + " = " + key ;
			System.out.println("삭제 쿼리문 확인" + sql);
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


