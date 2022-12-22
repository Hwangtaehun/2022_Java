package Swing;
import java.awt.*;
import javax.swing.*;

public class Sj64JTable1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjJTable1 frame = new SjJTable1("JTable Test 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}

class SjJTable1 extends JFrame{
	public SjJTable1() {}
	public SjJTable1(String str) {
		super (str);
		
		String columnModel[] = {"학번", "이름", "국어", "영어", "수학"};
		
		Object[][] dataModel = {
			{"1001", "세종", 100, 100, 100},
			{"1002", "정보", 88, 55, 99},
			{"1003", "처리", 99, 44, 66},
			{"1004", "컴퓨터", 77, 77, 99},
			{"1005", "학원", 77, 77, 99}
		};
		
		JTable table = new JTable(dataModel, columnModel);
		
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane);
		add(scrollPane);
		
		//add(new JScrollPane(table));
	}
}