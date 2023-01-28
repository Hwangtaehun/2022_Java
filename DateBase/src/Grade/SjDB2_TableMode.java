package Grade;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class SjDB2_TableMode extends JPanel{
	public SjDB2_TableMode()
	{
		super();
		initForm();
	}
	void initForm() {
		String columnModel[] = {"학번", "이름", "국어", "수학", "영어", "총점", "평균", "석차"};
		
		Object[][] dataModel = {
			{"1001", "홍길동", 99, 99, 99, 297, 99.0, 2},
			{"1002", "신유신", 86, 78, 78, 244, 61.3, 3},
			{"1003", "강감찬", 69, 45, 88, 202, 67.3, 5},
			{"1004", "강신라", 49, 100, 88, 237, 79.0, 4},
			{"1005", "신세종", 100, 100, 100, 300, 100.0, 1}
		};
		
		JTable table = new JTable(dataModel, columnModel);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	void initForm2() {
		MyTable myTable = new MyTable();
		JTable table = new JTable(myTable);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
}

class MyTable extends AbstractTableModel{
	Object[][] data = {
		{"1001", "홍길동", 99, 99, 99, 297, 99.0, 2},
		{"1002", "신유신", 86, 78, 78, 244, 61.3, 3},
		{"1003", "강감찬", 69, 45, 88, 202, 67.3, 5},
		{"1004", "강신라", 49, 100, 88, 237, 79.0, 4},
		{"1005", "신세종", 100, 100, 100, 300, 100.0, 1}
	};
	
	@Override
	public int getRowCount() {
		return data[0].length;
	}

	@Override
	public int getColumnCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	public void setValueAt(Object obj, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = obj;
	}
	
	public String getColumnName(int columnIndex) {
		String columnName[] = {"학번", "이름", "국어", "수학", "영어", "총점", "평균", "석차"};
		return columnName[columnIndex];
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		boolean bool = (columnIndex != 0) ? true : false;
		return bool;
	}
}