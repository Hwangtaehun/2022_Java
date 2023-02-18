package Allowance;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class DBA_Dialog extends JDialog{
	private DBA_TableMode tablemodel;
	private DBA_Frame frame;
	private DBA_DAO stuDB;
	private JComboBox <String> comboBox;
	private JButton newBt, addBt, updateBt, deleteBt;
	private JTextField tf_Name;
	private JTable table;
	private String tableName, tableId, titleName;
	private String dataModel[], dataList[][], dataSuperList[];
	private int dataCount, selectedCol, cnt;
	private ResultSet result;
	
	public DBA_Dialog() {}
	public DBA_Dialog(DBA_Frame frame, String titleName, boolean modal, String tableName, String dataModel[]) {
		super(frame, titleName, modal);
		this.tableName = tableName;
		this.dataModel = dataModel;
		this.frame = frame;
		stuDB = new DBA_DAO();
		
		if(tableName.equals("Manager"))
		{
			tableId = "manid";
			this.titleName = "기 초 자 료";
		}
		else
		{
			tableId = "conid";
			this.titleName = "거 래 처";
		}
		
		NewArray();
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
		comboBox = new JComboBox<String>(new DefaultComboBoxModel<String>(dataModel));
		comboBox.addItemListener(new comboBoxListener());
		gbl.setConstraints(comboBox, gbc);
		leftPanel.add(comboBox);
		setGrid(gbc, 0, 4, 1, 1);
		label = new JLabel("         제  목      ");
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
				if(result.getInt(tableId) != 999999){
					inputTable(dataCount, result.getInt(tableId), result.getString("title"));
					dataList[0][dataCount] = Integer.toString(result.getInt(tableId));
					dataList[1][dataCount] = result.getString("title");
					//System.out.println(dataList[0][dataCount]);
				}
			}
			repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void MoveData() {
		try {
			String title = result.getString("title");
			String supertitle = FindSuper(result.getInt(tableId), 0);
			comboBox.setSelectedItem(supertitle);
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
	
	public String FindSuper(int id, int select)
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
	
	private void NewArray() {
		cnt = 0;
		int cnt_temp;
		String sql = "Select * From " + tableName;
		ResultSet rs = stuDB.getResultSet(sql);
		try {
			while(rs.next()) 
			{
				cnt++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dataList = new String[2][cnt];
		
		sql = "Select title From Connection Where conid like '%0000'";
		cnt_temp = Countkey(sql);
		dataSuperList = new String[cnt_temp];
		InputData(dataSuperList, sql, 0);
	}
	
	private int InsertId(String define) {
		int id = 0;
		String sql, superid;
		
		if(define.equals("지출 추가"))
		{
			sql = "Select manid From Manager Where manid like '1__00'";
			id = Lastkey(sql, tableId) + 100;
		}
		else if(define.equals("수입 추가"))
		{
			sql = "Select manid From Manager Where manid like '2__00'";
			id = Lastkey(sql, tableId) + 100;
		}
		else if(define.equals("거래처 구분 추가"))
		{
			sql = "Select conid From Connection Where conid like '%0000'";
			id = Lastkey(sql, tableId) + 10000;
		}
		else if(equalTitle(define))
		{
			sql = "Select * FROM " + tableName + " WHERE title = '" + define + "' order by " + tableId;
			System.out.println(sql);
			id = Lastkey(sql, tableId);
			superid = FindSuper(id, 2);
			sql = "Select * FROM " + tableName + " WHERE " + tableId + " like '" + superid + "' order by " + tableId;
			System.out.println(sql);
			id = Lastkey(sql, tableId) + 100;
		}
		else 
		{
			sql = "Select * FROM " + tableName + " WHERE title = '" + define + "' order by " + tableId;
			System.out.println(sql);
			id = Lastkey(sql, tableId);
			superid = FindSuper(id, 1);
			sql = "Select * FROM " + tableName + " WHERE " + tableId + " like '" + superid + "' order by " + tableId;
			System.out.println(sql);
			id = Lastkey(sql, tableId) + 1;
		}
		return id;
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
	
	private void InputData(String dataModel[], String sql, int selectnum) {
		int num = 0;
		String attribut = "error";
		ResultSet rs = stuDB.getResultSet(sql);
		
		if(selectnum == 0)
		{
			attribut = "title";
		}
		else if(selectnum == 1)
		{
			attribut = tableId;
		}
		else
		{
			System.out.println("error");
			return;
		}
		
		try {
			while(rs.next())
			{
				dataModel[num] = rs.getString(attribut);
				num++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void ExtraInputData(String dataModel[], int cnt)
	{
		if(tableName.equals("Manager"))
		{
			dataModel[cnt - 2] = "지출 추가";
			dataModel[cnt - 1] = "수입 추가";
		}
		else
		{
			dataModel[cnt - 1] = "거래처 구분 추가";
		}
	}
	
	private void comboxSetValueAt()
	{
		int cnt = 0;
		String sql = "Select * From " + tableName + " Where " + tableId + " like '%00'";
		cnt = Countkey(sql);
		
		if(tableName.equals("Manager"))
			cnt += 2;
		else
			cnt += 1;
		
		dataModel = new String[cnt];
		InputData(dataModel, sql, 0);
		ExtraInputData(dataModel, cnt);
		
		comboBox.removeAllItems();
		for (int i = 0; i < dataModel.length; i++) {
			comboBox.addItem(dataModel[i]);
		}
	}
	
	private boolean equalTitle(String title) {
		for(int i = 0; i < dataSuperList.length; i++) {
			if(dataSuperList[i].equals(title))
			{
				System.out.println("내용 확인" + dataSuperList[i]);
				return true;
			}
		}
		return false;
	}
	
	private void initialization()
	{
		String sql = "Select * FROM " + tableName;
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
					String supertitle = FindSuper((int)table.getValueAt(selectedCol, 0), 0);
					comboBox.setSelectedItem(supertitle);
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
			comboBox.setSelectedIndex(0);
			tf_Name.setText(null);
			setEnabledButton(false);
		}
	}
	
	class addButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql, define;
			int id;
			
			if(tf_Name.getText().isEmpty()) {
				System.out.println("이름이 입력되지 않았습니다.");
				return;
			}
			define = comboBox.getSelectedItem().toString();
			id = InsertId(define);
			sql = " INSERT INTO " + tableName + " VALUES(" + id + ", '" + tf_Name.getText() + "')";
			System.out.println(sql);
			stuDB.Excute(sql);
			NewArray();
			LoadList();
			comboxSetValueAt();
			setEnabledButton(true);
			frame.NewArray();
			frame.comboxSetValueAt(tableName);
			frame.initialization();
			initialization();
		}
	}

	class updateButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql, code, define, supertitle; 
			int id;
			if(selectedCol == -1) {
				System.out.println("변경할 셀이 선택되지 않았습니다.");
				return;
			}
			define = comboBox.getSelectedItem().toString();
			code = table.getValueAt(selectedCol, 0).toString();
			supertitle = FindSuper(Integer.parseInt(code), 0);
			
			if(supertitle.equals(define)) {
				id = Integer.parseInt(code);
			}
			else {
				id = InsertId(define);
			}
			sql = " UPDATE Banks SET " + tableId + " = 999999 WHERE " + tableId + " = " + code;
			System.out.println(sql);
			stuDB.Excute(sql);
			sql = " UPDATE " + tableName + " SET " + tableId + " = " + id + ", title = '" + tf_Name.getText() + "' WHERE " + tableId + " = " + code;
			System.out.println(sql);
			stuDB.Excute(sql);
			NewArray();
			LoadList();
			comboxSetValueAt();
			frame.NewArray();
			frame.comboxSetValueAt(tableName);
			frame.LoadList();
			initialization();
		}
	}

	class deleteButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql, code, first_define, second_define, superid;
			String dataArray[];
			int size = 0, i = 0;
			
			if(selectedCol == -1) {
				System.out.println("삭제할 셀이 선택되지 않았습니다.");
				return;
			}
			
			code = table.getValueAt(selectedCol, 0).toString();
			first_define = FindSuper(Integer.parseInt(code), 4);
			second_define = FindSuper(Integer.parseInt(code), 3);
			
			if(code.equals(first_define)) {
				superid = FindSuper(Integer.parseInt(code), 5);
				sql = "Select " + tableId + " FROM " + tableName + " WHERE like " + superid;
				size = Countkey(sql);
				dataArray = new String[size];
				InputData(dataArray, sql, 1);
				
				for(i = 0; i < size; i++)
				{
					superid = dataArray[i];
					sql = "UPDATE Banks SET " + tableId + " = 999999 WHERE " + tableId + " = " + superid;
					System.out.println(sql);
					stuDB.Excute(sql);
					sql = "DELETE FROM " + tableName + " WHERE " + tableId + " = " + superid;
					System.out.println(sql);
					stuDB.Excute(sql);
				}
			}
			else if(code.equals(second_define)) {
				superid = FindSuper(Integer.parseInt(code), 1);
				sql = "Select " + tableId + " FROM " + tableName + " WHERE like " + superid;
				size = Countkey(sql);
				dataArray = new String[size];
				InputData(dataArray, sql, 1);
				
				for(i = 0; i < size; i++)
				{
					superid = dataArray[i];
					sql = "UPDATE Banks SET " + tableId + " = 999999 WHERE " + tableId + " = " + superid;
					System.out.println(sql);
					stuDB.Excute(sql);
					sql = "DELETE FROM " + tableName + " WHERE " + tableId + " = " + superid;
					System.out.println(sql);
					stuDB.Excute(sql);
				}
			}
			else {
				sql = "UPDATE Banks SET " + tableId + " = 999999 WHERE " + tableId + " = " + code;
				System.out.println(sql);
				stuDB.Excute(sql);
				sql = "DELETE FROM " + tableName + " WHERE " + tableId + " = " + code;
				System.out.println(sql);
				stuDB.Excute(sql);
			}
			NewArray();
			LoadList();
			comboxSetValueAt();
			frame.NewArray();
			frame.comboxSetValueAt(tableName);
			frame.LoadList();
			initialization();
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
			setVisible(false);
			dispose();
		}
	}
}
