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
	String dataModel[] = {"����", "����ó��", "��ǻ��"};
	
	public SjswCombo(){}
	public SjswCombo(String str) {
		super(str);
	}
	
	public void initForm() {
		comboBox = new JComboBox<String>(new DefaultComboBoxModel<String>(dataModel));
		
		comboBox.addItem("�п�");
		comboBox.insertItemAt("������ȹ", 1);
		
		comboBox.addItemListener(this);
		getContentPane().add(comboBox);
		
		System.out.println("���� �׸� �� = " + comboBox.getItemCount());
		
		comboBox.setSelectedItem("����ó��");
		System.out.println("���� " + comboBox.getSelectedIndex() + "��°�� ���õ�");
		
		comboBox.setSelectedIndex(3);
		System.out.println("���� " + comboBox.getSelectedItem().toString() + "�� ���õ�");
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getStateChange()== ItemEvent.SELECTED) {
			System.out.println(e.getItem()+" ���õ�");
		}
	}
	
}