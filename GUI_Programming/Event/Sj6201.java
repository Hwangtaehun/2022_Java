package Event;
import java.awt.event.*;
import java.awt.*;

class MyHandler implements ActionListener{
	MyHandler(){
		
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("같은 File에 작성된  Action Event Handler 실행됨");
	}
}

public class Sj6201 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Frame frm = new Frame("Event 연습1");
		Button bt = new Button("눌러보셔유..");
		
		//MyHandler mh = new MyHandler();
		//bt.addActionListener(mh);
		
		bt.addActionListener(new MyHandler());
		
		bt.addActionListener(new Sj6Handler1());
		frm.add(bt, "Center");
		frm.pack();
		frm.setVisible(true);
	}

}
