package Swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sj64JText {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjswText frame = new SjswText("TextField Test");
		frame.initForm();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}

class SjswText extends JFrame{
	JPanel pan1;
	JButton button1;
	JButton button2;
	Container cpane;
	JTextField txtField1;
	JTextField txtField2;
	JTextField txtField3;
	JTextArea txtArea;
	
	public SjswText() {}
	public SjswText(String str) {
		super(str);
	}
	
	public void initForm() {
		cpane = getContentPane();
		pan1 = new JPanel();
		pan1.setLayout(new BoxLayout(pan1, BoxLayout.Y_AXIS));
		
		txtField1 = new JTextField(30);
		txtField2 = new JTextField("Text Field Test", 20);
		txtField2.setEditable(true);
		txtField3 = new JTextField(40);
		txtField3.setEditable(false);
		txtField3.setText(txtField2.getText());
		
		pan1.add(txtField1);
		pan1.add(txtField2);
		pan1.add(txtField3);
		
		txtField1.addActionListener(new Sj64JTextActionHandler());
		txtField1.addKeyListener(new Sj64JTextKeyHandler());
		txtField1.addFocusListener(new Sj64JTextFocusHandler());
		txtField2.addFocusListener(new Sj64JTextFocusHandler());
		
		txtArea = new JTextArea(10, 20);
		//txtArea.setLineWrap(true);
		JScrollPane scrollPane1 = new JScrollPane(txtArea);
		scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		button1 = new JButton("눌러봐");
		button1.addActionListener(new Sj64JTextActionHandler2());
		cpane.add(button1, "Center");
		cpane.add(pan1, "North");
		cpane.add(scrollPane1, "South");
	}
	
	public class Sj64JTextActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Enter Key 입력된 내용은 " + e.getActionCommand());
			txtArea.append("Enter Key 입력된 내용은 " + e.getActionCommand() + "\n");
			txtField3.setText(txtField1.getText());
		}
		
	}
	
	public class Sj64JTextActionHandler2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			txtField2.requestFocus(true);
			txtField2.selectAll();
		}
		
	}
	
	public class Sj64JTextFocusHandler implements FocusListener{
		JTextField text;
		String str;
		
		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			text = (JTextField)e.getSource();
			if(text.equals(txtField1))
				str = "txtField1";
			else if(text.equals(txtField2))
				str = "txtField2";
			else if(text.equals(txtField3))
				str = "txtField3";
			System.out.println(str + " Focus 얻음");
			txtArea.append(str + " Focus 얻음\n");
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			text = (JTextField)e.getSource();
			if(text.equals(txtField1))
				str = "txtField1";
			else if(text.equals(txtField2))
				str = "txtField2";
			else if(text.equals(txtField3))
				str = "txtField3";
			System.out.println(str + " Focus 잃음");
			txtArea.append(str + " Focus 잃음");
		}
		
	}
	
	public class Sj64JTextKeyHandler extends KeyAdapter{
		public void keyTyped(KeyEvent e) {
			if(e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
				System.out.println("Kye 눌림 " + e.getKeyChar());
				txtArea.append("Kye 눌림 " + e.getKeyChar() + "\n");
			}
		}
	}
	
}