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
	private JTextField tf_Code, tf_Name, tf_Kor, tf_Mat, tf_Eng;
	private JButton newBt, addBt, updateBt, deleteBt;
	
	
	
	public DBA_Frame() {}
	public DBA_Frame(DBA_DAO db) {
		super();
		stuDB = db;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		result = stuDB.getResultSet("Select * FROM Score order by strCode");
		
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
		label = new JLabel("      번   호     ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 1, 1, 1);
		tf_Code = new JTextField(5);
		gbl.setConstraints(tf_Code, gbc);
		leftPanel.add(tf_Code);
		setGrid(gbc, 0, 2, 1, 1);
		label = new JLabel("      이  름     ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 2, 1, 1);
		tf_Name = new JTextField(10);
		gbl.setConstraints(tf_Name, gbc);
		leftPanel.add(tf_Name);
		setGrid(gbc, 0, 3, 1, 1);
		label = new JLabel("      국  어     ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 3, 1, 1);
		tf_Kor = new JTextField(5);
		gbl.setConstraints(tf_Kor, gbc);
		leftPanel.add(tf_Kor);
		setGrid(gbc, 0, 4, 1, 1);
		label = new JLabel("      수  학     ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 4, 1, 1);
		tf_Mat = new JTextField(5);
		gbl.setConstraints(tf_Mat, gbc);
		leftPanel.add(tf_Mat);
		setGrid(gbc, 0, 5, 1, 1);
		label = new JLabel("      영  어     ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc, 1, 5, 1, 1);
		tf_Eng = new JTextField(5);
		gbl.setConstraints(tf_Eng, gbc);
		leftPanel.add(tf_Eng);
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
		
		String columnName[] = {"학번", "이름", "국어", "수학", "영어", "총점", "평균", "석차"};
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
	
	private void inputTable(int cnt, String num, String name, int kor, int mat, int eng, int total, double average, int rank) {
		table.setValueAt(num, cnt, 0);
		table.setValueAt(name, cnt, 1);
		table.setValueAt(kor, cnt, 2);
		table.setValueAt(mat, cnt, 3);
		table.setValueAt(eng, cnt, 4);
		table.setValueAt(total, cnt, 5);
		table.setValueAt(average, cnt, 6);
		table.setValueAt(rank, cnt, 7);
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
		if(sw == 1)
			result = stuDB.getResultSet("SELECT * FROM Score order by strCode");
		else
			result = stuDB.getResultSet("SELECT * FROM Score order by nTotal desc");
		
		for(int i = 0; i < dataCount; i++) {
			removeTableRow(i);
		}
		try {
			for(dataCount = 0; result.next(); dataCount++) {
				inputTable(dataCount, result.getString("strCode"), result.getString("strName"), result.getInt("nKor"), result.getInt("nMat"), result.getInt("nEng"),
						 result.getInt("nTotal"), result.getDouble("dAverage"), result.getInt("nRank"));
			}
			repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void rank() {
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
			String hakBun = result.getString("strCode");
			String name = result.getString("strName");
			String kor = String.valueOf(result.getInt("nKor"));
			String eng = String.valueOf(result.getInt("nMat"));
			String mat = String.valueOf(result.getInt("nEng"));
			tf_Code.setText(hakBun);
			tf_Name.setText(name);
			tf_Kor.setText(kor);
			tf_Mat.setText(mat);
			tf_Eng.setText(eng);
		} catch (SQLException e) {
			e.printStackTrace();
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
					tf_Code.setText(table.getValueAt(selectedCol, 0).toString());
					tf_Name.setText(table.getValueAt(selectedCol, 1).toString());
					tf_Kor.setText(table.getValueAt(selectedCol, 2).toString());
					tf_Mat.setText(table.getValueAt(selectedCol, 3).toString());
					tf_Eng.setText(table.getValueAt(selectedCol, 4).toString());
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
			tf_Code.setText(null);
			tf_Name.setText(null);
			tf_Kor.setText(null);
			tf_Mat.setText(null);
			tf_Eng.setText(null);
			setEnabledButton(false);
		}
	}

	class addButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql;
			if(tf_Code.getText().isEmpty()) {
				System.out.println("번호기 입력되지 않았습니다.");
				return;
			}
			int total;
			total = Integer.parseInt(tf_Kor.getText()) + Integer.parseInt(tf_Mat.getText()) + Integer.parseInt(tf_Eng.getText());
			sql = " INSERT INTO Score (strCode, strName, nKor, nMat, nEng, nTotal, dAverage ) VALUES('" + tf_Code.getText() + "','" + tf_Name.getText() + "','" +
			      tf_Kor.getText() + "','" + tf_Eng.getText() + "','" + tf_Mat.getText() + "'," + Integer.toString(total) + "," + Double.toString(total/3.0) + ")";
			System.out.println(sql);
			stuDB.Excute(sql);
			rank();
			LoadList();
			setEnabledButton(true);
		}
	}

	class updateButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String sql, code;
			if(selectedCol == -1) {
				System.out.println("변경할 셀이 선택되지 않았습니다.");
				return;
			}
			code = table.getValueAt(selectedCol, 0).toString();
			int total = Integer.parseInt(tf_Kor.getText()) + Integer.parseInt(tf_Mat.getText()) + Integer.parseInt(tf_Eng.getText());
			sql = "UPDATE Score SET strName = '" + tf_Name.getText() + "', nKor = " + tf_Kor.getText() + ", nMat = " + tf_Mat.getText() + 
					", nEng = " + tf_Eng.getText() + ", nTotal = " + Integer.toString(total) + ", dAverage = " + Double.toString(total/3.0) + 
					" WHERE strCode = '" + code + "'";
			System.out.println(sql);
			stuDB.Excute(sql);
			rank();
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
			rank();
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
