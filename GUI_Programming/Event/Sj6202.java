package Event;
import java.awt.event.*;
import java.awt.*;

public class Sj6202 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sj6Handler2 sj = new Sj6Handler2("Event 연습 2");
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
		bt = new Button("눌러보셔유2");
		bt.addActionListener(this);
		add(bt, "Center");
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		System.out.println("Action Event 발생(버튼이 눌림)");
		System.out.println("Button에 쓰인 글은 = " + ev.getActionCommand());
	}
}