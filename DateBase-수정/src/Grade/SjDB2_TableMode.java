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
	
	public SjDB2_TableMode() {}
	public SjDB2_TableMode(SjDB2_DAO db, SjDB2_Frame frame)
	{
		super();
		this.db = db;
		this.frame = frame;
		initForm();
	}
	
	
	void initForm() {
		String columnModel[] = {"�й�", "�̸�", "����", "����", "����", "����", "���", "����"};
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
					dataModel[i][2] = 0;
					dataModel[i][3] = 0;
					dataModel[i][4] = 0;
					dataModel[i][5] = 0;
					dataModel[i][6] = 0;
					dataModel[i][7] = 0;
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
		 //int col = table.getSelectedColumn();
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



//class MyTable extends AbstractTableModel{
//	Object[][] data = {
//		{"1001", "ȫ�浿", 99, 99, 99, 297, 99.0, 2},
//		{"1002", "������", 86, 78, 78, 244, 61.3, 3},
//		{"1003", "������", 69, 45, 88, 202, 67.3, 5},
//		{"1004", "���Ŷ�", 49, 100, 88, 237, 79.0, 4},
//		{"1005", "�ż���", 100, 100, 100, 300, 100.0, 1}
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
//		String columnName[] = {"�й�", "�̸�", "����", "����", "����", "����", "���", "����"};
//		return columnName[columnIndex];
//	}
//	
//	public boolean isCellEditable(int rowIndex, int columnIndex) {
//		boolean bool = (columnIndex != 0) ? true : false;
//		return bool;
//	}
//}