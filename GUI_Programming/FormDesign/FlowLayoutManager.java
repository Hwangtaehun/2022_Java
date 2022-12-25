package FormDesign;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FlowLayoutManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Form frame = new Form("Sejong Chatting");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.initForm();
		frame.setVisible(true);
	}

}

class Form extends JFrame{
	JTextArea txtArea;
	JTextField txtField1;
	JTextField txtField2;
	JTextField txtField3;
	JTextField txtField4;
	Label label1;
	Label label2;
	Label label3;
	Label label4;
	JButton bt1;
	JButton bt2;
	JButton bt3;
	
	public Form() {}
	public Form(String str) {
		super(str);
		setSize(550, 220);
		setLayout(new FlowLayout());
	}
	
	public void initForm() {
		txtArea = new JTextArea(5, 50);
		add(txtArea);
		
		label1 = new Label("Server Ip");
		txtField2 = new JTextField("localhost");
		add(label1);
		add(txtField2);
		
		label2 = new Label("Port No");
		txtField3 = new JTextField("1234");
		add(label2);
		add(txtField3);
		
		label3 = new Label("Name");
		txtField4 = new JTextField("¼Õ´Ô1");
		add(label3);
		add(txtField4);
		
		bt1 = new JButton("Connect");
		add(bt1);
		
		bt2 = new JButton("DisConnect");
		add(bt2);
		
		label4 = new Label("Message");
		add(label4);
		
		txtField1 = new JTextField(30);
		add(txtField1);
		
		bt3 = new JButton("Send");
		add(bt3);
	}
		
}
