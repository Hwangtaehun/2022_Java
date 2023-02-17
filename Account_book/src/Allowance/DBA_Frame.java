package Allowance;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class DBA_Frame extends JFrame{
	private DBA_Frame frame;
	private DBA_DAO stuDB;
	private DBA_TableMode tablemodel;
	private ResultSet result;
	private JTable table;
	private int dataCount, selectedCol, sw = 1;
	private JTextField tf_Price, tf_Date, tf_Inform;
	private JButton newBt, addBt, updateBt, deleteBt;
	public String manModel[], conModel[];
	private JMenuBar mb;
	private JComboBox <String> manBox, conBox;
	
	public DBA_Frame() {}
	public DBA_Frame(DBA_DAO db) {
		super("가계부");
		stuDB = db;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame = this;
		NewArray();
		
		String sql = "Select Banks.id, Manager.title, Banks.price, Banks.date, Connection.title, Banks.inform, Banks.balance "
			   + "From Banks left join Manager on Banks.manid = Manager.manid left join Connection on Banks.conid = Connection.conid";
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
		label = new JLabel("     기  초  자  료   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 1, 1, 1);
		manBox = new JComboBox<String>(new DefaultComboBoxModel<String>(manModel));
		manBox.addItemListener(new comboBoxListener());
		gbl.setConstraints(manBox, gbc);
		leftPanel.add(manBox);
		setGrid(gbc, 0, 2, 1, 1);
		label = new JLabel("     금  액         ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 2, 1, 1);
		tf_Price = new JTextField(10);
		gbl.setConstraints(tf_Price, gbc);
		leftPanel.add(tf_Price);
		setGrid(gbc, 0, 3, 1, 1);
		label = new JLabel("     날  짜         ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 3, 1, 1);
		tf_Date = new JTextField(5);
		gbl.setConstraints(tf_Date, gbc);
		leftPanel.add(tf_Date);
		setGrid(gbc, 0, 4, 1, 1);
		label = new JLabel("     거  래  처     ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 4, 1, 1);
		conBox = new JComboBox<String>(new DefaultComboBoxModel<String>(conModel));
		conBox.addItemListener(new comboBoxListener());
		gbl.setConstraints(conBox, gbc);
		leftPanel.add(conBox);
		setGrid(gbc, 0, 5, 1, 1);
		label = new JLabel("     내  용          ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 5, 1, 1);
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
		
		String columnName[] = {"번호", "관리구분", "금액", "날짜", "거래처", "내용", "잔액"};
		tablemodel = new DBA_TableMode(columnName.length, columnName);
		table = new JTable(tablemodel);
		
		table.setPreferredScrollableViewportSize(new Dimension(530, 14*16));
		table.getSelectionModel().addListSelectionListener(new tableListener());
		JScrollPane scrollPane = new JScrollPane(table);
		centerPanel.add(scrollPane);
		
		mb = new JMenuBar();
		JMenu addMenu = new JMenu("추가");
		JMenuItem[] menuItems = new JMenuItem[2];
		String[] items = {"기초자료 추가", "거래처 추가"};
		menuListener act = new menuListener();
		
		for(int i=0; i<menuItems.length; i++) {
            menuItems[i] = new JMenuItem(items[i]); // 메뉴 아이템 컴포넌트 생성
            menuItems[i].addActionListener(act); // 리스너 등록
            addMenu.add(menuItems[i]);     
        }
		
		JMenu sortMenu = new JMenu("검색");
		menuItems = new JMenuItem[3];
		items = new String[3];
		items[0] = "기초자료 검색";
		items[1] = "거래처 검색";
		items[2] = "날짜 검색";
		
		for(int i=0; i<menuItems.length; i++) {
            menuItems[i] = new JMenuItem(items[i]);
            menuItems[i].addActionListener(act);
            sortMenu.add(menuItems[i]);     
        }
		
		mb.add(addMenu);
		mb.add(sortMenu);
		setJMenuBar(mb);
		
		cpane.add("West", leftPanel);
		cpane.add("Center", centerPanel);
		pack();
		balance();
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
	
	private String SortSQL() {
		String sql = null;
		if(sw == 1)
		{
			sql = "Select Banks.id, Manager.manid, Manager.title, Banks.price, Banks.date, Connection.title, Banks.inform, Banks.balance "
			      + "From Banks left join Manager on Banks.manid = Manager.manid left join Connection on Banks.conid = Connection.conid "
				  + "order by Banks.id";
		}
		else
		{
			sql = "Select Banks.id, Manager.manid, Manager.title, Banks.price, Banks.date, Connection.title, Banks.inform, Banks.balance "
				   + "From Banks left join Manager on Banks.manid = Manager.manid left join Connection on Banks.conid = Connection.conid "
				   + "order by Banks.date";
		}
		
		return sql;
	}
	
	public void LoadList() {
		String sql = SortSQL();
		result = stuDB.getResultSet(sql);
		
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
	
	private void balance() {
		int tot = 0, man_id =0;
		String sql = SortSQL();
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
	
	private int Countkey(String sql) {
		int num = 0;
		ResultSet rs = stuDB.getResultSet(sql);
		try {
			while(rs.next()) 
			{
				num++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return num;
	}
	
	private int Foreignkey(String table, String title) {
		String sql  = "Select * FROM " + table + " WHERE title = '" + title + "'";
		int num = 0;
		
		System.out.println(sql);
		ResultSet rs = stuDB.getResultSet(sql);
		try {
			rs.next();
			if(table.equals("Manager"))
				num = rs.getInt("manid");
			else
				num = rs.getInt("conid");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(num);
		return num;
	}
	
	private String ConvertDate(String date) {
		String year = null, month = null, day = null, finish = null;
		String[] array_word;
		int count = date.length();
		array_word = date.split("");
		
		if(ExistNumber(date)) {
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
			else 
			{
				System.out.println("날짜형식이 잘못 되었습니다. 다시한번 확인해보세요.");
				return "error";
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
			else 
			{
				System.out.println("날짜형식이 잘못 되었습니다. 다시한번 확인해보세요.");
				return "error";
			}
		}
		
		if(!Checkdate(year, month, day))
		{
			System.out.println("날짜형식이 잘못 되었습니다.");
			return "error";
		}
			
		
		finish = year + "-" + month + "-" + day;
		return finish;
	}
	
	private boolean leapyearCheck(String str_year) {
		int year = Integer.parseInt(str_year);
		if((year / 4 == 0 && year / 100 != 0) || year / 400 == 0) {
			return true;	
		}
		else {
			return false;
		}
	}
	
	private boolean Checkdate(String str_year, String str_month, String str_day) {
		int month = Integer.parseInt(str_month);
		int day = Integer.parseInt(str_day);
		if(month >= 1 && month <= 12) {
			if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				if(day < 1 || day > 31) 
				{
					System.out.println("일부분이 잘못되었습니다. 1~31까지에 있는 숫자를 입력해주세요.");
					return false;
				}
				else
					return true;
			}else if(month == 4 || month == 6 || month == 9 || month == 11) {
				if(day < 1 || day > 30)
				{
					System.out.println("일부분이 잘못되었습니다. 1~30까지에 있는 숫자를 입력해주세요.");
					return false;
				}
				else
					return true;
			}else if(month == 2) {
				if(leapyearCheck(str_year)) {
					if(day < 1 || day > 29)
					{
						System.out.println("일부분이 잘못되었습니다. 윤달이므로 1~29까지에 있는 숫자를 입력해주세요.");
						return false;
					}
					else
						return true;
				}else if(!leapyearCheck(str_year)){
					if(day < 1 || day > 28)
					{
						System.out.println("일부분이 잘못되었습니다. 평달이므로 1~28까지에 있는 숫자를 입력해주세요.");
						return false;
					}
					else
						return true;
				}
			}
		}else
		{
			System.out.println("달부분이 잘못되었습니다. 1~12까지에 있는 숫자를 입력해주세요.");
			return false;
		}
		return true;
	}
	
	private boolean ExistNumber(String date) {
		try {
		      Integer.parseInt(date);
		      return true;
		    } catch (NumberFormatException ex) {
		      return false;
		    }
	}
	
	private void InputData(String dataModel[], String sql) {
		int num = 0;
		ResultSet rs = stuDB.getResultSet(sql);
		
		try {
			while(rs.next())
			{
				dataModel[num] = rs.getString("title");
				num++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		for(int i = 0; i < dataModel.length; i++)
//		{
//			System.out.println(dataModel[i]);
//		}
//		System.out.print("\n");
	}
	
	public void NewArray() 
	{
		int num;
		String sql = "Select * From Manager";
		num = Countkey(sql);
		manModel = new String[num];
		InputData(manModel, sql);
		
		sql = "Select * From Connection";
		num = Countkey(sql);
		conModel = new String[num];
		InputData(conModel, sql);
	}
	
	public void comboxSetValueAt(String tableName)
	{
		if(tableName.equals("Connection")) {
			conBox.removeAllItems();
			for (int i = 0; i < conModel.length; i++) {
				conBox.addItem(conModel[i]);
			}
		}
		else {
			manBox.removeAllItems();
			for (int i = 0; i < manModel.length; i++) {
				manBox.addItem(manModel[i]);
			}
		}
	}
	
	public void initialization()
	{
		String sql = SortSQL();
		result = stuDB.getResultSet(sql);
		try {
			result.first();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		MoveData();
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
	
	class comboBoxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	class menuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String command = e.getActionCommand();
			String sql;
			int num;
			String dataModel[];
			
			switch(command) {
			case "기초자료 추가":
				sql = "Select * From Manager Where manid like '%00'";
				num = Countkey(sql);
				dataModel = new String[num + 2];
				InputData(dataModel, sql);
				dataModel[num] = "지출 추가";
				dataModel[num + 1] = "수입 추가";
				DBA_Dialog manadd_dlg = new DBA_Dialog(frame, command, false, "Manager", dataModel);
				manadd_dlg.setVisible(true);
				break;
			case "거래처 추가":
				sql = "Select * From Connection Where conid like '%00'";
				num = Countkey(sql);
				dataModel = new String[num + 1];
				dataModel[num] = "거래처 구분 추가";
				InputData(dataModel, sql);
				DBA_Dialog conadd_dlg = new DBA_Dialog(frame, command, false, "Connection", dataModel);
				conadd_dlg.setVisible(true);
				break;
			case "기초자료 검색":
				System.out.println("기초자료 검색 대화상자 실행");
				DBA_Dialog2 mansearch_Dlg = new DBA_Dialog2(frame, command, false);
				mansearch_Dlg.setVisible(true);
				break;
			case "거래처 검색":
				System.out.println("거래처 검색 대화상자 실행");
				DBA_Dialog2 consearch_Dlg = new DBA_Dialog2(frame, command, false);
				consearch_Dlg.setVisible(true);
				break;
			case "날짜 검색":
				System.out.println("날짜 검색 대화상자 실행");
				DBA_Dialog2 datesearch_Dlg = new DBA_Dialog2(frame, command, false);
				datesearch_Dlg.setVisible(true);
				break;
			}	
		}
	}
	
	class newButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			conBox.setSelectedIndex(0);
			tf_Price.setText(null);
			tf_Date.setText(null);
			manBox.setSelectedIndex(0);
			tf_Inform.setText(null);
			setEnabledButton(false);
		}
	}

	class addButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql, date;
			int id, manid, conid;
			
			if(tf_Price.getText().isEmpty()) {
				System.out.println("번호가 입력되지 않았습니다.");
				return;
			}
			
			id = Lastkey("Select * FROM Banks order by id", "Banks.id") + 1;
			manid = Foreignkey("Manager", manBox.getSelectedItem().toString());
			conid = Foreignkey("Connection", conBox.getSelectedItem().toString());
			
			if(manid == 999999 || conid == 999999) {
				System.out.println("'null'은 입력이 불가능합니다.");
				return;
			}
			
			date = ConvertDate(tf_Date.getText());
			sql = " INSERT INTO Banks VALUES(" + id + "," + manid + ","+ tf_Price.getText() + "," +"STR_TO_DATE('" + date + "','%Y-%m-%d'), " + conid + ", '" + tf_Inform.getText() + "', null)";
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
			String sql, code, date; 
			int manid, conid;
			if(selectedCol == -1) {
				System.out.println("변경할 셀이 선택되지 않았습니다.");
				return;
			}
			code = table.getValueAt(selectedCol, 0).toString();
			date = ConvertDate(tf_Date.getText());
			manid = Foreignkey("Manager", manBox.getSelectedItem().toString());
			conid = Foreignkey("Connection", conBox.getSelectedItem().toString());
			
			if(manid == 999999 || conid == 999999) {
				System.out.println("'null'은 입력이 불가능합니다.");
				sql = "Select Banks.id, Manager.title, Banks.price, Banks.date, Connection.title, Banks.inform, Banks.balance "
					+ "From Banks left join Manager on Banks.manid = Manager.manid left join Connection on Banks.conid = Connection.conid";
				result = stuDB.getResultSet(sql);
				return;
			}
			
			sql = " UPDATE Banks SET manid = " + manid + ", price = " + tf_Price.getText() + ", date = STR_TO_DATE('" + date + "','%Y-%m-%d'), inform = '" + tf_Inform.getText() + "', conid = " + conid + " WHERE id =" + code;
			System.out.println(sql);
			stuDB.Excute(sql);
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
			sql = "DELETE FROM Banks WHERE id = " + code;
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