package Swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sj64JCheckBox {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjswCheckBox frame = new SjswCheckBox("JCheckbox Test");
		frame.initForm();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 80);
		frame.setVisible(true);
	}

}

class SjswCheckBox extends JFrame{
	Container cpane;
	JCheckBox chk1;
	JCheckBox chk2;
	JCheckBox chk3;
	
	SjswCheckBox(){}
	SjswCheckBox(String str){
		super(str);
	}
	
	public void initForm() {
		chk1 = new JCheckBox("����", true);
		chk2 = new JCheckBox("����ó��", false);
		chk3 = new JCheckBox("�п�", false);
		setLayout(new FlowLayout());
		cpane = getContentPane();
		chk1.addItemListener(new ItemHandler());
		chk2.addItemListener(new ItemHandler());
		chk3.addItemListener(new ItemHandler());
		cpane.add(chk1);
		cpane.add(chk2);
		cpane.add(chk3);
		chk2.setText("��ǻ��");
		System.out.println("chk2 �� ������ " + chk2.getText());
		chk3.setSelected(true);
		System.out.println("chk3 �� ���´� " + chk3.isSelected());
	}
}

class ItemHandler implements ItemListener{
	ItemHandler(){}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getStateChange()==ItemEvent.SELECTED) {
			System.out.println(((JCheckBox)e.getSource()).getText()+" ���õ�1");
		}
		else {
			System.out.println(((JCheckBox)e.getSource()).getText()+" ������1");
		}
		if(((JCheckBox)e.getSource()).isSelected()) {
			System.out.println(((JCheckBox)e.getSource()).getText()+" ���õ�2");
		}
		else {
			System.out.println(((JCheckBox)e.getSource()).getText()+" ������2");
		}
	}
	
}