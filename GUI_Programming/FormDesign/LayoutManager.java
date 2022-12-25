package FormDesign;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LayoutManager {
	public static void main(String s[]) {
		FormText frame = new FormText("Sejong Chatting 11");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.initForm();
		frame.setVisible(true);
	}
}

class FormText extends JFrame{
	JTextArea txtArea;
	JTextField txtField1;
	JTextField txtField2;
	JTextField txtField3;
	JTextField txtField4;
	Label label1;
	Label label2;
	Label label3;
	JButton bt1;
	JButton bt2;
	JButton bt3;
	
	public FormText() {}
	public FormText(String str) {
		super(str);
		setSize(400, 220);
		setLayout(null);
	}
	
	public void initForm() {
		//setLayout(new FlowLayout());
		
		txtArea = new JTextArea(200, 100);
		txtArea.setBounds(5, 5, 200, 100);
		JScrollPane scrollPane1 = new JScrollPane(txtArea);
		scrollPane1.setBounds(5, 5, 200, 100);
		scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollPane1);
		
		txtField1 = new JTextField();
		txtField1.setBounds(5, 120, 200, 25);
		add(txtField1);
		
		label1 = new Label("Server Ip");
		label1.setBounds(220, 5, 60, 25);
		txtField2 = new JTextField("localhost");
		txtField2.setBounds(280, 5, 100, 25);
		add(label1);
		add(txtField2);
		
		label2 = new Label("Port No");
		label2.setBounds(220, 40, 60, 25);
		txtField3 = new JTextField("1234");
		txtField3.setBounds(280, 40, 100, 25);
		add(label2);
		add(txtField3);
		
		label3 = new Label("Name");
		label3.setBounds(220, 75, 60, 25);
		txtField4 = new JTextField("¼Õ´Ô");
		txtField4.setBounds(280, 75, 100, 25);
		add(label3);
		add(txtField4);
		
		bt1 = new JButton("Connect");
		bt1.setBounds(220, 100, 150, 25);
		add(bt1);
		
		bt2 = new JButton("DisConnect");
		bt2.setBounds(220, 125, 150, 25);
		add(bt2);
		
		bt3 = new JButton("Send");
		bt3.setBounds(220, 150, 150, 25);
		add(bt3);
	}
		
}