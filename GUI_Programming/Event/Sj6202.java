package Event;
import java.awt.event.*;
import java.awt.*;

public class Sj6202 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sj6Handler2 sj = new Sj6Handler2("Event ���� 2");
		sj.test();
	}

}

class Sj6Handler2 extends Frame implements ActionListener{
	Button bt;
	
	public Sj6Handler2() {
	}
	
	public Sj6Handler2(String str) {
		super(str);
	}
	
	void test() {
		bt = new Button("����������2");
		bt.addActionListener(this);
		add(bt, "Center");
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		System.out.println("Action Event �߻�(��ư�� ����)");
		System.out.println("Button�� ���� ���� = " + ev.getActionCommand());
	}
}