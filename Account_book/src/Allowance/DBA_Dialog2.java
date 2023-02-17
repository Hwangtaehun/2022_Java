package Allowance;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.time.*;

public class DBA_Dialog2 extends JDialog{
	private DBA_TableMode tablemodel;
	private DBA_DAO stuDB;
	private DBA_Frame frame;
	private JComboBox <String> manBox, conBox, yearBox, monthBox, dayBox;
	private JTable table;
	private JButton searchBt, updateBt, deleteBt;
	private JTextField tf_Price, tf_Date, tf_Inform;
	private JCheckBox manchk, conchk, datechk;
	private String manName, conName, mainSQL, tableName, tableID;
	private int dataCount, selectedCol, cnt, year, month, day;
	private String yearModel[], monthModel[], dayModel[];
	private boolean yearbool, monthbool, daybool;
	private ResultSet result;
	
	public DBA_Dialog2() {}
	public DBA_Dialog2(DBA_Frame frame, String titleName, boolean modal)
	{
		super(frame, titleName, modal);
		this.frame = frame;
		stuDB = new DBA_DAO();
		
		LocalDate now = LocalDate.now();
		year = now.getYear();
		month = now.getMonthValue();
		day = now.getDayOfMonth();
		yearbool = true;
		monthbool = true;
		daybool = true;
		
		yearModel = new String[201];
		for(int i = 0; i < 201; i++)
		{
			yearModel[i] = Integer.toString(i + 1900);
		}
		
		initialDate();
		initform_man();
		comboboxSetEnable();
	}
	
	void initform_man()
	{
		Container cpane = getContentPane();
		JPanel leftPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel manPanel = new JPanel();
		JPanel conPanel = new JPanel();
		JPanel checkPanel = new JPanel();
		JPanel datePanel = new JPanel();
		JPanel yearPanel = new JPanel();
		JPanel monthPanel = new JPanel();
		JPanel dayPanel = new JPanel();
		JLabel label;
		JButton bt;
		selectedCol = -1;
		GridBagLayout gbl = new GridBagLayout();
		leftPanel.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		setGrid(gbc, 0, 0, 1, 1);
		label = new JLabel("     기  초  자  료   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 0, 1, 1);
		manBox = new JComboBox<String>(new DefaultComboBoxModel<String>(frame.manModel));
		manBox.addItemListener(new manBoxListener());
		gbl.setConstraints(manBox, gbc);
		leftPanel.add(manBox);
		setGrid(gbc, 0, 2, 1, 1);
		label = new JLabel("     거  래  처     ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 2, 1, 1);
		conBox = new JComboBox<String>(new DefaultComboBoxModel<String>(frame.conModel));
		conBox.addItemListener(new conBoxListener());
		gbl.setConstraints(conBox, gbc);
		leftPanel.add(conBox);
		setGrid(gbc, 1, 3, 1, 1);
		manchk = new JCheckBox("기초자료", yearbool);
		manchk.addItemListener(new checkBoxListener());
		checkPanel.add("West", manchk);
		conchk = new JCheckBox("거래처", monthbool);
		conchk.addItemListener(new checkBoxListener());
		checkPanel.add("Center", conchk);
		datechk = new JCheckBox("날짜", daybool);
		datechk.addItemListener(new checkBoxListener());
		checkPanel.add("East", datechk);
		gbl.setConstraints(checkPanel, gbc);
		leftPanel.add(checkPanel);
		setGrid(gbc, 0, 4, 1, 1);
		label = new JLabel("         날  짜");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 4, 1, 1);
		label = new JLabel("년");
		yearPanel.add("West", label);
		yearBox = new JComboBox<String>(new DefaultComboBoxModel<String>(yearModel));
		yearBox.addItemListener(new yearBoxListener());
		yearBox.setSelectedItem(Integer.toString(year));
		yearPanel.add("East", yearBox);
		label = new JLabel("월");
		monthPanel.add("West", label);
		monthBox = new JComboBox<String>(new DefaultComboBoxModel<String>(monthModel));
		monthBox.addItemListener(new yearBoxListener());
		monthBox.setSelectedItem(Integer.toString(month));
		monthPanel.add("Esat", monthBox);
		label = new JLabel("일");
		dayPanel.add("West", label);
		dayBox = new JComboBox<String>(new DefaultComboBoxModel<String>(dayModel));
		dayBox.addItemListener(new yearBoxListener());
		dayBox.setSelectedItem(Integer.toString(day));
		dayPanel.add("East", dayBox);
		datePanel.add("West", yearPanel);
		datePanel.add("Center", monthPanel);
		datePanel.add("East", dayPanel);
		gbl.setConstraints(datePanel, gbc);
		leftPanel.add(datePanel);
		setGrid(gbc, 0, 7, 1, 1);
		label = new JLabel(" ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 0, 8, 1, 1);
		label = new JLabel(" ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);

		setGrid(gbc, 3, 0, 1, 1);
		updateBt = new JButton("수정");
		//updateBt.addActionListener(new updateButtonListener());
		gbl.setConstraints(updateBt, gbc);
		leftPanel.add(updateBt);
		setGrid(gbc, 3, 1, 1, 1);
		deleteBt = new JButton("삭제");
		//deleteBt.addActionListener(new deleteButtonListener());
		gbl.setConstraints(deleteBt, gbc);
		leftPanel.add(deleteBt);
		setGrid(gbc, 3, 3, 1, 1);
		searchBt = new JButton("검색");
		searchBt.addActionListener(new searchButtonListener());
		gbl.setConstraints(searchBt, gbc);
		leftPanel.add(searchBt);
		setGrid(gbc, 3, 8, 1, 1);
		bt = new JButton("종료");
		bt.addActionListener(new exitButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		
		String columnName[] = {"번호", "관리구분", "금액", "날짜", "거래처", "내용", "잔액"};;
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
	
	void initform_con()
	{
		Container cpane = getContentPane();
		JPanel leftPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel manPanel = new JPanel();
		JPanel conPanel = new JPanel();
		JPanel checkPanel = new JPanel();
		JPanel datePanel = new JPanel();
		JPanel yearPanel = new JPanel();
		JPanel monthPanel = new JPanel();
		JPanel dayPanel = new JPanel();
		JLabel label;
		JButton bt;
		selectedCol = -1;
		GridBagLayout gbl = new GridBagLayout();
		leftPanel.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		setGrid(gbc, 0, 0, 1, 1);
		label = new JLabel("     기  초  자  료   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 0, 1, 1);
		manBox = new JComboBox<String>(new DefaultComboBoxModel<String>(frame.manModel));
		manBox.addItemListener(new manBoxListener());
		gbl.setConstraints(manBox, gbc);
		leftPanel.add(manBox);
		setGrid(gbc, 0, 2, 1, 1);
		label = new JLabel("     거  래  처     ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 2, 1, 1);
		conBox = new JComboBox<String>(new DefaultComboBoxModel<String>(frame.conModel));
		conBox.addItemListener(new conBoxListener());
		gbl.setConstraints(conBox, gbc);
		leftPanel.add(conBox);
		setGrid(gbc, 1, 3, 1, 1);
		manchk = new JCheckBox("기초자료", yearbool);
		manchk.addItemListener(new checkBoxListener());
		checkPanel.add("West", manchk);
		conchk = new JCheckBox("거래처", monthbool);
		conchk.addItemListener(new checkBoxListener());
		checkPanel.add("Center", conchk);
		datechk = new JCheckBox("날짜", daybool);
		datechk.addItemListener(new checkBoxListener());
		checkPanel.add("East", datechk);
		gbl.setConstraints(checkPanel, gbc);
		leftPanel.add(checkPanel);
		setGrid(gbc, 0, 4, 1, 1);
		label = new JLabel("         날  짜");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 4, 1, 1);
		label = new JLabel("년");
		yearPanel.add("West", label);
		yearBox = new JComboBox<String>(new DefaultComboBoxModel<String>(yearModel));
		yearBox.addItemListener(new yearBoxListener());
		yearBox.setSelectedItem(Integer.toString(year));
		yearPanel.add("East", yearBox);
		label = new JLabel("월");
		monthPanel.add("West", label);
		monthBox = new JComboBox<String>(new DefaultComboBoxModel<String>(monthModel));
		monthBox.addItemListener(new yearBoxListener());
		monthBox.setSelectedItem(Integer.toString(month));
		monthPanel.add("Esat", monthBox);
		label = new JLabel("일");
		dayPanel.add("West", label);
		dayBox = new JComboBox<String>(new DefaultComboBoxModel<String>(dayModel));
		dayBox.addItemListener(new yearBoxListener());
		dayBox.setSelectedItem(Integer.toString(day));
		dayPanel.add("East", dayBox);
		datePanel.add("West", yearPanel);
		datePanel.add("Center", monthPanel);
		datePanel.add("East", dayPanel);
		gbl.setConstraints(datePanel, gbc);
		leftPanel.add(datePanel);
		setGrid(gbc, 0, 7, 1, 1);
		label = new JLabel(" ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 0, 8, 1, 1);
		label = new JLabel(" ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);

		setGrid(gbc, 3, 0, 1, 1);
		updateBt = new JButton("수정");
		//updateBt.addActionListener(new updateButtonListener());
		gbl.setConstraints(updateBt, gbc);
		leftPanel.add(updateBt);
		setGrid(gbc, 3, 1, 1, 1);
		deleteBt = new JButton("삭제");
		//deleteBt.addActionListener(new deleteButtonListener());
		gbl.setConstraints(deleteBt, gbc);
		leftPanel.add(deleteBt);
		setGrid(gbc, 3, 3, 1, 1);
		searchBt = new JButton("검색");
		searchBt.addActionListener(new searchButtonListener());
		gbl.setConstraints(searchBt, gbc);
		leftPanel.add(searchBt);
		setGrid(gbc, 3, 8, 1, 1);
		bt = new JButton("종료");
		bt.addActionListener(new exitButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		
		String columnName[] = {"번호", "관리구분", "금액", "날짜", "거래처", "내용", "잔액"};;
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
	
	void initform_date()
	{
		Container cpane = getContentPane();
		JPanel leftPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel manPanel = new JPanel();
		JPanel conPanel = new JPanel();
		JPanel checkPanel = new JPanel();
		JPanel datePanel = new JPanel();
		JPanel yearPanel = new JPanel();
		JPanel monthPanel = new JPanel();
		JPanel dayPanel = new JPanel();
		JLabel label;
		JButton bt;
		selectedCol = -1;
		GridBagLayout gbl = new GridBagLayout();
		leftPanel.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		setGrid(gbc, 0, 0, 1, 1);
		label = new JLabel("     기  초  자  료   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 0, 1, 1);
		manBox = new JComboBox<String>(new DefaultComboBoxModel<String>(frame.manModel));
		manBox.addItemListener(new manBoxListener());
		gbl.setConstraints(manBox, gbc);
		leftPanel.add(manBox);
		setGrid(gbc, 0, 2, 1, 1);
		label = new JLabel("     거  래  처     ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 2, 1, 1);
		conBox = new JComboBox<String>(new DefaultComboBoxModel<String>(frame.conModel));
		conBox.addItemListener(new conBoxListener());
		gbl.setConstraints(conBox, gbc);
		leftPanel.add(conBox);
		setGrid(gbc, 1, 3, 1, 1);
		manchk = new JCheckBox("년", yearbool);
		manchk.addItemListener(new checkBoxListener());
		checkPanel.add("West", manchk);
		conchk = new JCheckBox("일", monthbool);
		conchk.addItemListener(new checkBoxListener());
		checkPanel.add("Center", conchk);
		datechk = new JCheckBox("월", daybool);
		datechk.addItemListener(new checkBoxListener());
		checkPanel.add("East", datechk);
		gbl.setConstraints(checkPanel, gbc);
		leftPanel.add(checkPanel);
		setGrid(gbc, 0, 4, 1, 1);
		label = new JLabel("         날  짜");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 4, 1, 1);
		label = new JLabel("년");
		yearPanel.add("West", label);
		yearBox = new JComboBox<String>(new DefaultComboBoxModel<String>(yearModel));
		yearBox.addItemListener(new yearBoxListener());
		yearBox.setSelectedItem(Integer.toString(year));
		yearPanel.add("East", yearBox);
		label = new JLabel("월");
		monthPanel.add("West", label);
		monthBox = new JComboBox<String>(new DefaultComboBoxModel<String>(monthModel));
		monthBox.addItemListener(new yearBoxListener());
		monthBox.setSelectedItem(Integer.toString(month));
		monthPanel.add("Esat", monthBox);
		label = new JLabel("일");
		dayPanel.add("West", label);
		dayBox = new JComboBox<String>(new DefaultComboBoxModel<String>(dayModel));
		dayBox.addItemListener(new yearBoxListener());
		dayBox.setSelectedItem(Integer.toString(day));
		dayPanel.add("East", dayBox);
		datePanel.add("West", yearPanel);
		datePanel.add("Center", monthPanel);
		datePanel.add("East", dayPanel);
		gbl.setConstraints(datePanel, gbc);
		leftPanel.add(datePanel);
		setGrid(gbc, 0, 7, 1, 1);
		label = new JLabel(" ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 0, 8, 1, 1);
		label = new JLabel(" ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);

		setGrid(gbc, 3, 0, 1, 1);
		updateBt = new JButton("수정");
		//updateBt.addActionListener(new updateButtonListener());
		gbl.setConstraints(updateBt, gbc);
		leftPanel.add(updateBt);
		setGrid(gbc, 3, 1, 1, 1);
		deleteBt = new JButton("삭제");
		//deleteBt.addActionListener(new deleteButtonListener());
		gbl.setConstraints(deleteBt, gbc);
		leftPanel.add(deleteBt);
		setGrid(gbc, 3, 3, 1, 1);
		searchBt = new JButton("검색");
		searchBt.addActionListener(new searchButtonListener());
		gbl.setConstraints(searchBt, gbc);
		leftPanel.add(searchBt);
		setGrid(gbc, 3, 8, 1, 1);
		bt = new JButton("종료");
		bt.addActionListener(new exitButtonListener());
		gbl.setConstraints(bt, gbc);
		leftPanel.add(bt);
		
		String columnName[] = {"번호", "관리구분", "금액", "날짜", "거래처", "내용", "잔액"};;
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
	
	private void inputTable(int cnt, int id, String man_title, int price, Date date, String con_title, String inform, int balance) {
		table.setValueAt(id, cnt, 0);
		table.setValueAt(man_title, cnt, 1);
		table.setValueAt(price, cnt, 2);
		table.setValueAt(date, cnt, 3);
		table.setValueAt(con_title, cnt, 4);
		table.setValueAt(inform, cnt, 5);
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
		mainSQL = "Select Banks.id, Manager.manid, Manager.title, Banks.price, Banks.date, Connection.title, Banks.inform, Banks.balance "
				   + "From Banks left join Manager on Banks.manid = Manager.manid left join Connection on Banks.conid = Connection.conid "
				   + "order by Banks.date";
		result = stuDB.getResultSet(mainSQL);
		
		for(int i = 0; i < dataCount; i++) {
			removeTableRow(i);
		}
		try {
			for(dataCount = 0; result.next(); dataCount++) {
				inputTable(dataCount, result.getInt("Banks.id"), result.getString("Manager.title"), result.getInt("Banks.price"), result.getDate("Banks.date"),
						   result.getString("Connection.title"), result.getString("Banks.inform"), result.getInt("Banks.balance"));
			}
			repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void Balance() {
		int tot = 0, man_id =0;
		String sql;
		ResultSet rs = stuDB.getResultSet(mainSQL);
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
	
	private void MoveData() {
		try {
			String manager = result.getString("Manager.title");
			String price = String.valueOf(result.getInt("Banks.price"));
			String date = String.valueOf(result.getDate("Banks.date"));
			String connection = result.getString("Connection.title");
			String inform = result.getString("Banks.inform");
			manBox.setSelectedItem(manager);
			tf_Price.setText(price);
			tf_Date.setText(date);
			conBox.setSelectedItem(connection);
			tf_Inform.setText(inform);
		} catch (SQLException e) {
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
	
	private void comboboxSetEnable() {
		manBox.setEnabled(yearbool);
		conBox.setEnabled(monthbool);
		yearBox.setEnabled(daybool);
		monthBox.setEnabled(daybool);
		dayBox.setEnabled(daybool);
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
					manBox.setSelectedItem(table.getValueAt(selectedCol, 1).toString());
					tf_Price.setText(table.getValueAt(selectedCol, 2).toString());
					tf_Date.setText(table.getValueAt(selectedCol, 3).toString());
					conBox.setSelectedItem(table.getValueAt(selectedCol, 4).toString());
					tf_Inform.setText(table.getValueAt(selectedCol, 5).toString());
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
	
	class manBoxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED) {
				manName = e.getItem().toString();
			}
		}
	}
	
	class conBoxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED) {
				conName = e.getItem().toString();
			}
		}
	}
	
	class yearBoxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED) {
				year = Integer.parseInt(e.getItem().toString());
				initialDate();
			}
		}
	}
	
	class monthBoxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED) {
				month = Integer.parseInt(e.getItem().toString());
				initialDate();
			}
		}
	}
	
	class dayBoxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED) {
				day = Integer.parseInt(e.getItem().toString());
				initialDate();
			}
		}
	}
	
	class checkBoxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED) {
				System.out.println(((JCheckBox)e.getSource()).getText() + " 선택됨");
				String command = ((JCheckBox)e.getSource()).getText();
				switch(command)
				{
				case "기초자료":
					yearbool = true;
					break;
				case "거래처":
					monthbool = true;
					break;
				case "날짜":
					daybool = true;
					break;
				}
			}
			else {
				System.out.println(((JCheckBox)e.getSource()).getText() + " 해제됨");
				String command = ((JCheckBox)e.getSource()).getText();
				switch(command)
				{
				case "기초자료":
					yearbool = false;
					break;
				case "거래처":
					monthbool = false;
					break;
				case "날짜":
					daybool = false;
					break;
				}
			}
			comboboxSetEnable();
		}
	}
	
	class searchButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LoadList();
			Balance();
		}
	}
	
	class exitButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			dispose();
		}
	}
}