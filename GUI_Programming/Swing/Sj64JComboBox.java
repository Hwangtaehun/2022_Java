package Swing;
import javax.swing.*;
import java.awt.event.*;

public class Sj64JComboBox {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjswCombo frame = new SjswCombo("JComboBox Test");
		frame.initForm();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}

class SjswCombo extends JFrame implements ItemListener{
	JComboBox <String> comboBox;
	String dataModel[] = {"세종", "정보처리", "컴퓨터"};
	
	public SjswCombo(){}
	public SjswCombo(String str) {
		super(str);
	}
	
	public void initForm() {
		comboBox = new JComboBox<String>(new DefaultComboBoxModel<String>(dataModel));
		
		comboBox.addItem("학원");
		comboBox.insertItemAt("열림기획", 1);
		
		comboBox.addItemListener(this);
		getContentPane().add(comboBox);
		
		System.out.println("현재 항목 수 = " + comboBox.getItemCount());
		
		comboBox.setSelectedItem("정보처리");
		System.out.println("현재 " + comboBox.getSelectedIndex() + "번째가 선택됨");
		
		comboBox.setSelectedIndex(3);
		System.out.println("현재 " + comboBox.getSelectedItem().toString() + "가 선택됨");
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getStateChange()== ItemEvent.SELECTED) {
			System.out.println(e.getItem()+" 선택됨");
		}
	}
	
}