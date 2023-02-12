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
	private int dataCount, selectedCol, cnt;
	private ResultSet result;
	private JComboBox <String> comboBox;
	private String dataModel[], dataList[][];
	private JButton newBt, addBt, updateBt, deleteBt;
	private JTextField tf_Name;
	private String titleName;
	private DBA_Frame frame;
	private int dataid[];
	
	public DBA_Dialog() {}
	public DBA_Dialog(DBA_DAO stuDB, DBA_Frame frame, String tableName, String dataModel[]) {
		this.stuDB = stuDB;
		this.tableName = tableName;
		this.dataModel = dataModel;
		this.frame = frame;
		
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
				dataList[0][dataCount] = Integer.toString(result.getInt(tableId));
				dataList[1][dataCount] = result.getString("title");
				//System.out.println(dataList[0][dataCount]);
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
	
	private String FindSuper(int id, int select)
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
	
	private void IdSort() {
		int i, j, k;
		int cnt = 0;
		int size_frist = 0;
		int size_second = 0;
		int size_thrid = 0;
		int first_temp[];
		int second_temp[];
		int thrid_temp[] = null;
		String sql;
		ResultSet rs;
		String changeid, originid, secondstring = null, thridstring;
		
		if(tableName.equals("Manager"))
		{
			first_temp = new int[2];
			first_temp[0] = 1;
			first_temp[1] = 2;
			size_frist = 2;
		}
		else {
			sql = "Select " + tableId +" From " + tableName + " Where " + tableId +" like '_0000'";
			System.out.println(sql);
			size_frist = Countkey(sql);
			first_temp = new int[size_frist];
			rs = stuDB.getResultSet(sql);
			try {
				for(i = 0; rs.next(); i++)
				{
					int temp = rs.getInt(tableId)/10000;
					first_temp[i] = temp;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(i = 0; i < size_frist; i++) {
			sql = "Select " + tableId +" From " + tableName + " Where " + tableId +" like '" + first_temp[i] + "__00'";
			System.out.println(sql);
			size_second = Countkey(sql);
			second_temp = new int[size_second];
			rs = stuDB.getResultSet(sql);
			try {
				for(j = 0; rs.next(); j++)
				{
					int temp = rs.getInt(tableId)%10000/100;
					second_temp[j] = temp;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(j = 0; j < size_second; j++) {
				if(second_temp[j] < 10)
				{
					secondstring = "0" + Integer.toString(second_temp[j]);
				}
				else
				{
					secondstring = Integer.toString(second_temp[j]);
				}
				sql = "Select " + tableId +" From " + tableName + " Where " + tableId +" like '" 
					+ first_temp[i] + second_temp[j] +"__'";
				System.out.println(sql);
				size_thrid = Countkey(sql);
				thrid_temp = new int[size_second];
				rs = stuDB.getResultSet(sql);
				try {
					for(k = 0; rs.next(); k++)
					{
						int temp = rs.getInt(tableId)%100;
						thrid_temp[k] = temp;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(k = 0; k < size_thrid; k++)
			{
				if(cnt != thrid_temp[k])
				{
					String cntstring;
					if(cnt < 10)
					{
						cntstring = "0" + Integer.toString(cnt);
					}
					else 
					{
						cntstring = Integer.toString(cnt);
					}
					if(thrid_temp[k] < 10)
					{
						thridstring = "0" + Integer.toString(thrid_temp[k]);
					}
					else
					{
						thridstring = Integer.toString(thrid_temp[k]);
					}
					changeid = Integer.toString(first_temp[i]) + secondstring + cntstring;
					originid = Integer.toString(first_temp[i]) + secondstring + thridstring;
					sql = "UPDATE " + tableName + " SET " + tableId + " = " + changeid + " WHERE " + tableId + " = " + originid;
					rs = stuDB.getResultSet(sql);
				}
				cnt++;
			}
		}
		
		
	}
	
	private void NewArray() {
		cnt = 0;
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
		else if(define.equals("online")||define.equals("offline")||define.equals("country")||define.equals("family"))
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
	}
	
	private void comboxSetValueAt()
	{
		int cnt = 0;
		String sql = "Select * FROM " + tableName;
		cnt = Countkey(sql);
		dataModel = new String[cnt];
		InputData(dataModel, sql);
		
		comboBox.removeAllItems();
		for (int i = 0; i < dataModel.length; i++) {
			comboBox.addItem(dataModel[i]);
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
			sql = " UPDATE " + tableName + " SET " + tableId + " = " + id + ", title = '" + tf_Name.getText() + "' WHERE " + tableId + " = " + code;
			System.out.println(sql);
			stuDB.Excute(sql);
			NewArray();
			LoadList();
			comboxSetValueAt();
			frame.NewArray();
			frame.comboxSetValueAt(tableName);
		}
	}

	class deleteButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql, code, define;
			if(selectedCol == -1) {
				System.out.println("삭제할 셀이 선택되지 않았습니다.");
				return;
			}
			code = table.getValueAt(selectedCol, 0).toString();
			define = FindSuper(Integer.parseInt(code), 4);
			if(code.equals(define)) {
				System.out.println("삭제할 수 없습니다.");
				return;
			}
			
			define = FindSuper(Integer.parseInt(code), 3);
			if(code.equals(define)) {
				System.out.println("삭제할 수 없습니다.");
				return;
			}
			sql = "DELETE FROM " + tableName + " WHERE " + tableId + " = " + code;
			System.out.println("삭제 쿼리문 확인" + sql);
			stuDB.Excute(sql);
			NewArray();
			LoadList();
			comboxSetValueAt();
			frame.NewArray();
			frame.comboxSetValueAt(tableName);
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
			frame.initialization();
			setVisible(false);
			dispose();
		}
	}
}
