package Swing;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Sj64JTable2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjJTable2 frame = new SjJTable2("JTable Test 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}

class SjJTable2 extends JFrame{
	public SjJTable2() {}
	public SjJTable2(String str) {
		super(str);
		SungjukModel sungjuk = new SungjukModel();
		JTable table = new JTable(sungjuk);
		table.setPreferredScrollableViewportSize(new Dimension(500, table.getRowCount()*16));
		add(new JScrollPane(table));
		table.setValueAt("information", 1, 1);
		for(int i = 0; i < table.getRowCount(); i++) {
			for(int j = 0; j <table.getColumnCount(); j++) {
				System.out.println(table.getValueAt(i, j) + "\t");
			}
			System.out.println();
		}
	}
}

class SungjukModel extends AbstractTableModel{
	Object[][] data = {{"1001", "세종", 100, 100, 100},
			{"1002", "정보", 88, 55, 99},
			{"1003", "처리", 99, 44, 66},
			{"1004", "컴퓨터", 77, 77, 99},
			{"1005", "학원", 77, 77, 99}};
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return data[0].length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}
	
	public void setValueAt(Object obj, int rowIndex, int columnIndex)
	{
		data[rowIndex][columnIndex] = obj;
	}
	
	public String getColumnName(int columnIndex) {
		String columnName[] = {"학번", "이름", "국어", "영어", "수학"};
		return columnName[columnIndex];
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		boolean bool = (columnIndex != 0) ? true : false;
		return bool;
	}
}