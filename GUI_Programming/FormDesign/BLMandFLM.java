package FormDesign;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BLMandFLM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChattingForm frame = new ChattingForm("Sejong Chatting 3");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.initForm();
		frame.setVisible(true);
	}

}

class ChattingForm extends JFrame{
	JTextArea txtArea;
	JTextField txtField1, txtField2, txtField3, txtField4;
	Label label1, label2, label3, label4;
	JButton bt1, bt2, bt3;
	JPanel pan1, pan2, pan3, pan31, pan32;
	
	public ChattingForm() {}
	public ChattingForm(String str) {
		super(str);
		setSize(550, 220);
		setLayout(new BorderLayout());
	}
	
	public void initForm() {
		pan1 = new JPanel();
		pan1.setPreferredSize(new Dimension(550, 10));
		//pan1.setLayout(new FlowLayout());
		add(pan1, BorderLayout.NORTH);
		
		pan2 =new JPanel();
		pan2.setLayout(new BorderLayout());
		
		txtArea = new JTextArea(5, 60);
		pan2.add(txtArea);
		
		add(pan2, BorderLayout.CENTER);
		
		pan3 =new JPanel();
		pan3.setLayout(new BorderLayout());
		
		pan31 =new JPanel();
		//pan31.setLayout(new FlowLayout());
		
		pan32 =new JPanel();
		//pan32.setLayout(new FlowLayout());
		
		
		label1 = new Label("Server Ip"); 
		txtField2 = new JTextField("localhost");
		pan31.add(label1); 
		pan31.add(txtField2);
		  
		label2 = new Label("Port No"); 
		txtField3 = new JTextField("1234");
		pan31.add(label2); 
		pan31.add(txtField3);
		  
		label3 = new Label("Name"); 
		txtField4 = new JTextField("¼Õ´Ô1"); 
		pan31.add(label3);
		pan31.add(txtField4);
		  
		bt1 = new JButton("Connect"); 
		pan31.add(bt1);
		  
		bt2 = new JButton("DisConnect"); 
		pan31.add(bt2);
		  
		label4 = new Label("Message"); 
		pan32.add(label4);
		  
		txtField1 = new JTextField(30); 
		pan32.add(txtField1);
		  
		bt3 = new JButton("Send"); 
		pan32.add(bt3);
		
		pan3.add(pan31, BorderLayout.NORTH);
		pan3.add(pan32, BorderLayout.SOUTH);
		
		add(pan3, BorderLayout.SOUTH);
	}
		
}