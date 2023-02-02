package Grade;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class SjDB2_TableMode extends JPanel implements MouseListener{
	JTable table;
	SjDB2_Frame frame;
	SjDB2_DAO db;
	Object[][] dataModel;
	JScrollPane scrollPane;
	String columnModel[];
	
	public SjDB2_TableMode() {}
	public SjDB2_TableMode(SjDB2_DAO db, SjDB2_Frame frame)
	{
		super();
		this.db = db;
		this.frame = frame;
		initForm();
	}
	
	void initForm() {
		columnModel = new String[8];
		columnModel[0] = "학번";
		columnModel[1] = "이름"; 
		columnModel[2] = "국어";
		columnModel[3] = "수학";
		columnModel[4] = "영어";
		columnModel[5] = "총점";
		columnModel[6] = "평균";
		columnModel[7] = "석차";
		//columnModel[] = {"학번", "이름", "국어", "수학", "영어", "총점", "평균", "석차"};
		setTable();
		table = new JTable(dataModel, columnModel);
		table.addMouseListener(this);
		scrollPane = new JScrollPane(table);
		scrollPane.addMouseListener(this);
		add(scrollPane);
	}
	
	void setTable() {
		dataModel = new Object[50][8];
		ResultSet rs;
		String sql = "Select * FROM Score order by strCode";
		rs = db.getResultSet(sql);
		for(int i = 0; i < 50; i++) {
			try {
				if(rs.next() == false) {
					dataModel[i][0] = null;
					dataModel[i][1] = null;
					dataModel[i][2] = null;
					dataModel[i][3] = null;
					dataModel[i][4] = null;
					dataModel[i][5] = null;
					dataModel[i][6] = null;
					dataModel[i][7] = null;
				}
				else {
					dataModel[i][0] = (rs.getString("strCode")).trim();
					dataModel[i][1] = (rs.getString("strName")).trim();
					dataModel[i][2] = rs.getInt("nKor");
					dataModel[i][3] = rs.getInt("nMat");
					dataModel[i][4] = rs.getInt("nEng");
					dataModel[i][5] = rs.getInt("nTotal");
					dataModel[i][6] = rs.getDouble("dAverage");
					dataModel[i][7] = rs.getInt("nRank");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		 int row = table.getSelectedRow();
		 int col = table.getSelectedColumn();
		 int n;
		 for (int i = 0; i < table.getColumnCount(); i++) { 
			 Object result = table.getModel().getValueAt(row, i);
			 switch(i) 
			 {
			 case 0:
				 frame.number.setText((String)result);
				 break;
			 case 1:
				 frame.name.setText((String)result);
				 break;
			 case 2:
				 n = (int)result;
				 frame.kor.setText(Integer.toString(n));
				 break;
			 case 3:
				 n = (int)result;
				 frame.mat.setText(Integer.toString(n));
				 break;
			 case 4:
				 n = (int)result;
				 frame.eng.setText(Integer.toString(n));
				 break;
			 }
		 }
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}


//void initForm2() {
//MyTable myTable = new MyTable();
//JTable table = new JTable(myTable);
//JScrollPane scrollPane = new JScrollPane(table);
//add(scrollPane);
//}

//class MyTable extends AbstractTableModel{
//	Object[][] data = {
//		{"1001", "홍길동", 99, 99, 99, 297, 99.0, 2},
//		{"1002", "신유신", 86, 78, 78, 244, 61.3, 3},
//		{"1003", "강감찬", 69, 45, 88, 202, 67.3, 5},
//		{"1004", "강신라", 49, 100, 88, 237, 79.0, 4},
//		{"1005", "신세종", 100, 100, 100, 300, 100.0, 1}
//	};
//	
//	@Override
//	public int getRowCount() {
//		return data[0].length;
//	}
//
//	@Override
//	public int getColumnCount() {
//		return data.length;
//	}
//
//	@Override
//	public Object getValueAt(int rowIndex, int columnIndex) {
//		return data[rowIndex][columnIndex];
//	}
//	
//	public void setValueAt(Object obj, int rowIndex, int columnIndex) {
//		data[rowIndex][columnIndex] = obj;
//	}
//	
//	public String getColumnName(int columnIndex) {
//		String columnName[] = {"학번", "이름", "국어", "수학", "영어", "총점", "평균", "석차"};
//		return columnName[columnIndex];
//	}
//	
//	public boolean isCellEditable(int rowIndex, int columnIndex) {
//		boolean bool = (columnIndex != 0) ? true : false;
//		return bool;
//	}
//}