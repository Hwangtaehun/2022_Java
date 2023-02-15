package Allowance;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import Allowance.DBA_Dialog.comboBoxListener;
import Allowance.DBA_Dialog.tableListener;

import java.sql.*;

public class DBA_Dialog2 extends JDialog{
	private DBA_TableMode tablemodel;
	private DBA_DAO stuDB;
	private DBA_Frame frame;
	private JComboBox <String> manBox, conBox, yearBox, monthBox, dayBox;
	private JTable table;
	private JButton newBt, addBt, updateBt, deleteBt;
	private JTextField tf_Balance;
	private String tableName, tableId, titleName;
	private int dataCount, selectedCol, cnt, year, month, day;
	private String yearModel[], monthModel[], dayModel[];
	private ResultSet result;
	
	public DBA_Dialog2() {}
	public DBA_Dialog2(DBA_Frame frame, String titleName, boolean modal)
	{
		super(frame, titleName, modal);
		manBox = frame.manBox;
		conBox = frame.conBox;
		stuDB = new DBA_DAO();
		year = 2023;
		month = 1;
		day = 1;
		yearModel = new String[201];
		for(int i = 0; i < 201; i++)
		{
			yearModel[i] = Integer.toString(i + 1900);
		}
		for(int i = 0; i < 201; i++)
		{
			System.out.println(yearModel[i]);
		}
		
		initialDate();
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
		setGrid(gbc, 0, 1, 1, 1);
		label = new JLabel("     기  초  자  료   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 1, 1, 1);
		gbl.setConstraints(manBox, gbc);
		leftPanel.add(manBox);
		setGrid(gbc, 0, 2, 1, 1);
		label = new JLabel("     거  래  처     ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 2, 1, 1);
		gbl.setConstraints(conBox, gbc);
		leftPanel.add(conBox);
		setGrid(gbc, 1, 3, 1, 1);
		label = new JLabel("        날  짜         ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 0, 4, 1, 1);
		label = new JLabel("        년");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 4, 1, 1);
		yearBox = new JComboBox<String>(new DefaultComboBoxModel<String>(yearModel));
		yearBox.addItemListener(new yearBoxListener());
		yearBox.setSelectedItem(year);
		gbl.setConstraints(yearBox, gbc);
		leftPanel.add(yearBox);
		setGrid(gbc, 0, 5, 1, 1);
		label = new JLabel("        월");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 5, 1, 1);
		monthBox = new JComboBox<String>(new DefaultComboBoxModel<String>(monthModel));
		monthBox.addItemListener(new yearBoxListener());
		monthBox.setSelectedItem(month);
		gbl.setConstraints(monthBox, gbc);
		leftPanel.add(monthBox);
		setGrid(gbc, 0, 6, 1, 1);
		label = new JLabel("        일");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 6, 1, 1);
		dayBox = new JComboBox<String>(new DefaultComboBoxModel<String>(dayModel));
		dayBox.addItemListener(new yearBoxListener());
		dayBox.setSelectedItem(day);
		gbl.setConstraints(dayBox, gbc);
		leftPanel.add(dayBox);
		
		String columnName[] = {"번호", "관리구분", "금액", "날짜", "거래처", "내용", "잔액"};
		tablemodel = new DBA_TableMode(columnName.length, columnName);
		table = new JTable(tablemodel);
		
		table.setPreferredScrollableViewportSize(new Dimension(530, 14*16));
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
	}
	
	private void setGrid(GridBagConstraints gbc, int dx, int dy, int width, int height) {
		gbc.gridx = dx;
		gbc.gridy = dy;
		gbc.gridwidth = width;
		gbc.gridheight = height;
	}
	
	private void inputTable(int cnt, String man_title, int price, Date date, String con_title, String inform, int balance) {
		table.setValueAt(man_title, cnt, 0);
		table.setValueAt(price, cnt, 1);
		table.setValueAt(date, cnt, 2);
		table.setValueAt(con_title, cnt, 3);
		table.setValueAt(inform, cnt, 4);
		table.setValueAt(balance, cnt, 5);
	}
	
	private void removeTableRow(int row) {
		table.setValueAt(null, row, 0);
		table.setValueAt(null, row, 1);
		table.setValueAt(null, row, 2);
		table.setValueAt(null, row, 3);
		table.setValueAt(null, row, 4);
		table.setValueAt(null, row, 5);
	}
	
	private void LoadList() {
		String sql = "Select Banks.id, Manager.manid, Manager.title, Banks.price, Banks.date, Connection.title, Banks.inform, Banks.balance "
				   + "From Banks left join Manager on Banks.manid = Manager.manid left join Connection on Banks.conid = Connection.conid "
				   + "order by Banks.date";
		result = stuDB.getResultSet(sql);
		
		for(int i = 0; i < dataCount; i++) {
			removeTableRow(i);
		}
		try {
			for(dataCount = 0; result.next(); dataCount++) {
				inputTable(dataCount, result.getString("Manager.title"), result.getInt("Banks.price"), result.getDate("Banks.date"),
						   result.getString("Connection.title"), result.getString("Banks.inform"), result.getInt("Banks.balance"));
			}
			repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void Balance() {
		int tot = 0, man_id =0;
		String sql = "Select Banks.id, Manager.manid, Manager.title, Banks.price, Banks.date, Connection.title, Banks.inform, Banks.balance "
				   + "From Banks left join Manager on Banks.manid = Manager.manid left join Connection on Banks.conid = Connection.conid "
				   + "order by Banks.date";
		ResultSet rs = stuDB.getResultSet(sql);
		try {
			while(rs.next()) {
				man_id = rs.getInt("Manager.manid");
				
				if(man_id == 999999)
					tot += 0;
				else if(man_id > 20000)
					tot += rs.getInt("Banks.price");
				else
					tot -= rs.getInt("Banks.price");
				
				sql = "UPDATE Banks Set balance = " + tot + " where id = " + rs.getString("Banks.id");
				System.out.println(sql);
				stuDB.Excute(sql);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String FindSuper(int id, int select, String dataList[][])
	{
		int temp = id/10000;
		String number = Integer.toString(temp);
		
		if(select == 2)
		{
			number += "__00";
			return number;
		}
		else if(select == 4)
		{
			number += "0000";
			return number;
		}
		else if(select == 5)
		{
			number += "%";
			return number;
		}
		
		temp = id%10000/1000;
		number += Integer.toString(temp);
		
		temp = id%10000%1000/100;
		number += Integer.toString(temp);
		
		if(select == 0)
		{
			number += "00";
			for(int i = 0; i < cnt; i++) {				
				if(dataList[0][i].equals(number))
				{
					return dataList[1][i];
				}
			}
		}
		else if(select == 1)
		{
			number += "%";
			return number;
		}
		else if(select == 3)
		{
			number += "00";
			return number;
		}
		return "error";
	}
	
	private boolean leapyearCheck(int year) {
		if((year / 4 == 0 && year / 100 != 0) || year / 400 == 0) {
			return true;	
		}
		else {
			return false;
		}
	}
	
	private void initialDate() {
		int i, size;
		
		monthModel = new String[12];
		for(i = 0; i < 12; i++) {
			monthModel[i] = Integer.toString(i + 1);
		}
		
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			size = 31;
			dayModel = new String[size];
			for(i = 0; i < size; i++) {
				dayModel[i] = Integer.toString(i + 1);
			}
		}else if(month == 4 || month == 6 || month == 9 || month == 11) {
			size = 30;
			dayModel = new String[size];
			for(i = 0; i < size; i++) {
				dayModel[i] = Integer.toString(i + 1);
			}
		}else if(month == 2) {
			if(leapyearCheck(year)) {
				size = 29;
				dayModel = new String[size];
				for(i = 0; i < size; i++) {
					dayModel[i] = Integer.toString(i + 1);
				}
			}else if(!leapyearCheck(year)){
				size = 28;
				dayModel = new String[size];
				for(i = 0; i < size; i++) {
					dayModel[i] = Integer.toString(i + 1);
				}
			}
		}
	}
	
	class yearBoxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	class monthBoxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	class dayBoxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
		}
	}
}
