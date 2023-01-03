package DNSandURL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class DNS_Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwDns frame = new SwDns("DNS Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}

class SwDns extends JFrame{
	JTextField txtField;
	JButton button;
	JTextArea txtArea;
	Label label1, label2;
	JPanel pan1, pan2, pan3;
	
	public SwDns() {}
	public SwDns(String str) {
		super(str);
		setLayout(new BorderLayout());
		initForm();
	}
	
	public void initForm() {
		pan1 = new JPanel();
		pan1.setLayout(new BorderLayout());
		label1 = new Label("DomainName");
		pan1.add(label1, BorderLayout.NORTH);
		
		txtField = new JTextField(10);
		pan1.add(txtField, BorderLayout.SOUTH);
		
		pan2 = new JPanel();
		pan2.setLayout(new BorderLayout());
		button = new JButton("눌러봐");
		button.addActionListener(new SjJTextActionHandler());
		pan2.add(button, BorderLayout.NORTH);
		
		label2 = new Label("Domain Name : IpAddress");
		pan2.add(label2, BorderLayout.SOUTH);
		
		pan3 = new JPanel();
		txtArea = new JTextArea(10, 20);
		txtArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txtArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pan3.add(scrollPane);
		
		add(pan1, BorderLayout.NORTH);
		add(pan2, BorderLayout.CENTER);
		add(pan3, BorderLayout.SOUTH);
	}
	
	public class SjJTextActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String data = txtField.getText();
			IPprint(data);
		}
		
	}
	
	public void IPprint(String data) {
		InetAddress [] iv;
		String name = data;
		
		try {
			iv = InetAddress.getAllByName(name);
			for(int i = 0; i < iv.length; i++) {
				txtArea.append(iv[i].getHostName() + ":" + iv[i].getHostAddress() + "\n");
			}
		}
		catch(Exception e) {
			txtArea.append(name + " 해당 Host가 없네요 !" + "\n");
		}
	}
}