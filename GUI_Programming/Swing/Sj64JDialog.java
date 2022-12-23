package Swing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Sj64JDialog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjDialog frame = new SjDialog("Dialog ����");
		frame.setTitle("��ȭ���ڿ���");
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
		
		dlg = new JDialog(this, "��ȭ����1", true);
		dlg.add(new JLabel("ù��° ��ȭ����"), "North");
		btok = new JButton("�ݱ�1");
		btok.addActionListener(this);
		dlg.add(btok, "Center");
		dlg.pack();
		
		mdlg = new MyDialog(this, "��ȭ����2", true);
		fileChooser = new JFileChooser();
		
		bt1 = new JButton("��ȭ����1");
		bt2 = new JButton("��ȭ����2");
		btload = new JButton("�����б�");
		btsave = new JButton("��������");
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
		System.out.println(str + " Button ����");
		JButton bt = (JButton)e.getSource();
		
		//if(str.equals("��ȭ����1"))
		if(bt.equals(bt1)) 
		{
			dlg.setVisible(true);
		}
		//else if(str.equals("�ݱ�1"))
		else if(bt.equals(btok)) 
		{
			dlg.setVisible(false);
		}
		//else if(str.equals("��ȭ����2"))
		else if(bt.equals(bt2)) 
		{
			mdlg.setVisible(true);
		}
		//else if(str.equals("�����б�"))
		else if(bt.equals(btload)) 
		{
			fileChooser.showOpenDialog(this);
			File file = fileChooser.getSelectedFile();
			System.out.println("������ File�� " + file.getName() + " �Դϴ�.");
		}
		//else if(str.equals("�����б�"))
		else if(bt.equals(btsave)) 
		{
			fileChooser.showSaveDialog(this);
			System.out.println("������ File�� " + fileChooser.getSelectedFile().getName() + "�Դϴ�.");
		}
	}
	
}

class MyDialog extends JDialog implements ActionListener{
	public MyDialog() {}
	public MyDialog(JFrame frm, String str, boolean bool) {
		super(frm, str, bool);
		JButton bt = new JButton("�ݱ�");
		bt.addActionListener(this);
		add(new JLabel("�ι�° ��ȭ����"), "North");
		add(bt, "Center");
		pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		System.out.println("MyDialog���� ��ư ����");
		setVisible(false);
		dispose();
	}
	
}