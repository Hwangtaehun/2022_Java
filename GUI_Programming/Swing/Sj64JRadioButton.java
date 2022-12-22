package Swing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sj64JRadioButton {
	public static void main(String s[]) {
		SjswRadioButton frame = new SjswRadioButton("JRadioButton Test");
		frame.initForm();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}

class SjswRadioButton extends JFrame implements ActionListener{
	JPanel northPanel, centerPanel, southPanel;
	AbstractButton absButton;
	JSlider slider;
	JRadioButton radioButton;
	ButtonGroup buttonGroup1, buttonGroup2, grp;
	JCheckBox chk1;
	JCheckBox chk2;
	JCheckBox chk3;
	
	SjswRadioButton(){}
	SjswRadioButton(String str){
		super(str);
	}
	
	public void initForm() {
		northPanel = new JPanel();
		centerPanel = new JPanel();
		southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(2,1));
		absButton = new JButton("JButton");
		absButton.setToolTipText("Button ǳ�� ���� ");
		northPanel.add(absButton);
		
		absButton = new JCheckBox("JChekcbox");
		northPanel.add(absButton);
		absButton.setToolTipText("CheckBox ǳ�� ���� ");
		
		absButton = new JRadioButton("JRadioButton");
		northPanel.add(absButton);
		absButton.setToolTipText("RadioButton ǳ�� ���� ");
		
		slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		centerPanel.add(slider);
		
		buttonGroup1 = new ButtonGroup();
		buttonGroup2 = new ButtonGroup();
		grp = new ButtonGroup();
		
		radioButton = new JRadioButton("����", true);
		radioButton.addActionListener(this);
		radioButton.addItemListener(new ItemHandler2());
		radioButton.setActionCommand("Sejong");
		grp.add(radioButton);
		southPanel.add(radioButton);
		
		radioButton = new JRadioButton("��ǻ��", false);
		radioButton.setActionCommand("Computer");
		radioButton.addActionListener(this);
		radioButton.addItemListener(new ItemHandler2());
		grp.add(radioButton);
		southPanel.add(radioButton);
		
		radioButton = new JRadioButton("�п�", false);
		radioButton.setActionCommand("�� ��");
		radioButton.addActionListener(this);
		radioButton.addItemListener(new ItemHandler2());
		grp.add(radioButton);
		southPanel.add(radioButton);
		
		radioButton = new JRadioButton("�ڹ�", false);
		radioButton.setActionCommand("Java");
		buttonGroup1.add(radioButton);
		southPanel.add(radioButton);
		
		radioButton = new JRadioButton("VisualC++", false);
		radioButton.setActionCommand("cpp");
		buttonGroup1.add(radioButton);
		southPanel.add(radioButton);
		
		getContentPane().add(northPanel, "North");
		getContentPane().add(centerPanel, "Center");
		getContentPane().add(southPanel, "South");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd;
		cmd = e.getActionCommand();
		if(cmd.equals("Sejong"))
			System.out.println(cmd + "���õ�");
		else if(cmd.equals("Computer"))
			System.out.println(cmd + "���õ�");
		else
			System.out.println(cmd + "���õ�");
	}
	
}

class ItemHandler2 implements ItemListener{
	ItemHandler2(){}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getStateChange()==ItemEvent.SELECTED) {
			System.out.println(((JRadioButton)e.getSource()).getText()+" ���õ�");
		}
		else {
			System.out.println(((JRadioButton)e.getSource()).getText()+" ������");
		}
		if(((JRadioButton)e.getSource()).isSelected()) {
			System.out.println(((JRadioButton)e.getSource()).getText()+" ���õ�1");
		}
		else {
			System.out.println(((JRadioButton)e.getSource()).getText()+" ������1");
		}
	}
	
}