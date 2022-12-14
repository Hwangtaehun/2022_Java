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
		absButton.setToolTipText("Button 풍선 도움말 ");
		northPanel.add(absButton);
		
		absButton = new JCheckBox("JChekcbox");
		northPanel.add(absButton);
		absButton.setToolTipText("CheckBox 풍선 도움말 ");
		
		absButton = new JRadioButton("JRadioButton");
		northPanel.add(absButton);
		absButton.setToolTipText("RadioButton 풍선 도움말 ");
		
		slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		centerPanel.add(slider);
		
		buttonGroup1 = new ButtonGroup();
		buttonGroup2 = new ButtonGroup();
		grp = new ButtonGroup();
		
		radioButton = new JRadioButton("세종", true);
		radioButton.addActionListener(this);
		radioButton.addItemListener(new ItemHandler2());
		radioButton.setActionCommand("Sejong");
		grp.add(radioButton);
		southPanel.add(radioButton);
		
		radioButton = new JRadioButton("컴퓨터", false);
		radioButton.setActionCommand("Computer");
		radioButton.addActionListener(this);
		radioButton.addItemListener(new ItemHandler2());
		grp.add(radioButton);
		southPanel.add(radioButton);
		
		radioButton = new JRadioButton("학원", false);
		radioButton.setActionCommand("학 원");
		radioButton.addActionListener(this);
		radioButton.addItemListener(new ItemHandler2());
		grp.add(radioButton);
		southPanel.add(radioButton);
		
		radioButton = new JRadioButton("자바", false);
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
			System.out.println(cmd + "선택됨");
		else if(cmd.equals("Computer"))
			System.out.println(cmd + "선택됨");
		else
			System.out.println(cmd + "선택됨");
	}
	
}

class ItemHandler2 implements ItemListener{
	ItemHandler2(){}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getStateChange()==ItemEvent.SELECTED) {
			System.out.println(((JRadioButton)e.getSource()).getText()+" 선택됨");
		}
		else {
			System.out.println(((JRadioButton)e.getSource()).getText()+" 해제됨");
		}
		if(((JRadioButton)e.getSource()).isSelected()) {
			System.out.println(((JRadioButton)e.getSource()).getText()+" 선택됨1");
		}
		else {
			System.out.println(((JRadioButton)e.getSource()).getText()+" 해제됨1");
		}
	}
	
}