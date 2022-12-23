package Swing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Sj64JDialog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjDialog frame = new SjDialog("Dialog 연습");
		frame.setTitle("대화상자연습");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

class SjDialog extends JFrame implements ActionListener{
	JDialog  dlg;
	MyDialog mdlg;
	JButton bt1, bt2, btload, btsave, btok;
	JFileChooser fileChooser;
	
	public SjDialog() {}
	public SjDialog(String str) {
		super(str);
		setLayout(new FlowLayout());
		setSize(400, 100);
		
		dlg = new JDialog(this, "대화상자1", true);
		dlg.add(new JLabel("첫번째 대화상자"), "North");
		btok = new JButton("닫기1");
		btok.addActionListener(this);
		dlg.add(btok, "Center");
		dlg.pack();
		
		mdlg = new MyDialog(this, "대화상자2", true);
		fileChooser = new JFileChooser();
		
		bt1 = new JButton("대화상자1");
		bt2 = new JButton("대화상자2");
		btload = new JButton("파일읽기");
		btsave = new JButton("파일저장");
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		btload.addActionListener(this);
		btsave.addActionListener(this);
		add(bt1);
		add(bt2);
		add(btload);
		add(btsave);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str = e.getActionCommand();
		System.out.println(str + " Button 눌림");
		JButton bt = (JButton)e.getSource();
		
		//if(str.equals("대화상자1"))
		if(bt.equals(bt1)) 
		{
			dlg.setVisible(true);
		}
		//else if(str.equals("닫기1"))
		else if(bt.equals(btok)) 
		{
			dlg.setVisible(false);
		}
		//else if(str.equals("대화상자2"))
		else if(bt.equals(bt2)) 
		{
			mdlg.setVisible(true);
		}
		//else if(str.equals("파일읽기"))
		else if(bt.equals(btload)) 
		{
			fileChooser.showOpenDialog(this);
			File file = fileChooser.getSelectedFile();
			System.out.println("선택한 File은 " + file.getName() + " 입니다.");
		}
		//else if(str.equals("파일읽기"))
		else if(bt.equals(btsave)) 
		{
			fileChooser.showSaveDialog(this);
			System.out.println("저장할 File은 " + fileChooser.getSelectedFile().getName() + "입니다.");
		}
	}
	
}

class MyDialog extends JDialog implements ActionListener{
	public MyDialog() {}
	public MyDialog(JFrame frm, String str, boolean bool) {
		super(frm, str, bool);
		JButton bt = new JButton("닫기");
		bt.addActionListener(this);
		add(new JLabel("두번째 대화상자"), "North");
		add(bt, "Center");
		pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		System.out.println("MyDialog에서 버튼 눌림");
		setVisible(false);
		dispose();
	}
	
}