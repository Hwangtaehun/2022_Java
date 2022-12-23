package Swing;
import javax.swing.*;
import java.awt.event.*;

public class Sj64JProgressBar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjswJProgressBar frame = new SjswJProgressBar("ProgressBar Test");
		frame.initForm();
	}

}

class SjswJProgressBar extends JFrame implements ActionListener{
	JProgressBar prog1;
	JLabel label1;
	JButton button1, button2, button3;
	JPanel pan1;
	int cnt;
	
	SjswJProgressBar(){}
	SjswJProgressBar(String str){
		super(str);
	}
	
	public void initForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pan1 = new JPanel();
		button1 = new JButton("ProgressBar 초기화");
		button2 = new JButton("ProgressBar 감소");
		button3 = new JButton("ProgressBar 증가");
		pan1.add(button1);
		pan1.add(button2);
		pan1.add(button3);
		prog1 = new JProgressBar();
		prog1.setMinimum(0);
		prog1.setMaximum(100);
		prog1.setValue(0);
		label1 = new JLabel("");
		getContentPane().add(pan1, "North");
		getContentPane().add(prog1, "Center");
		getContentPane().add(label1, "South");
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		cnt = 10;
		setSize(500, 200);
		setVisible(true);
		test();
	}
	
	public void test() {
		try {
			for(cnt = 0; cnt <= 100; cnt++) {
				prog1.setValue(cnt);
				Thread.sleep(20);
				label1.setText(" " +  cnt + "% 진행중 ");
			}
		}catch(InterruptedException e) {
			
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		JButton bt;
		bt = (JButton)event.getSource();
		if(bt.equals(button1))
			cnt = 0;
		else if(bt.equals(button2))
			cnt -= 10;
		else if(bt.equals(button3))
			cnt += 10;
		prog1.setValue(cnt);
		label1.setText(" " + cnt + "% 진행중 ");
	}
	
}