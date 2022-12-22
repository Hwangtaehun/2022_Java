package Swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sj64JButton {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjswJButton button1 = new SjswJButton("Swing JButton 연습 ");
		button1.initForm();
		button1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button1.setSize(300, 80);
		button1.setVisible(true);
	}
}

class SjswJButton extends JFrame implements ActionListener{
	JButton button1;
	JButton button2;
	Container cpane;
	
	SjswJButton(){}
	SjswJButton(String str){
		super(str);
	}
	
	public void initForm() {
		button1 = new JButton("첫번째 버튼");
		button2 = new JButton("두번째 버튼");
		
		cpane = getContentPane();
		cpane.setLayout(new FlowLayout());
		
		button1.setMnemonic('1');
		button2.setMnemonic('2');
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		
		cpane.add(button1);
		cpane.add(button2);
		
		System.out.println("Button1 의 제목은 " + button1.getText());
		button1.setText("하나");
		System.out.println("Button1 의 제목은 " + button1.getText());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		System.out.println("지금 누른 Button의 제목은 " + event.getActionCommand());
	}
	
}