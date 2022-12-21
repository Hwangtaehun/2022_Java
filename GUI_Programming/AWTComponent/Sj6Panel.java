package AWTComponent;
import java.awt.*;
import java.awt.event.*;

public class Sj6Panel {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjPanel sj = new SjPanel("Panel ¿¬½À");
		sj.setVisible(true);
	}
}

class SjPanel extends Frame implements ItemListener{
	Panel pan1, pan2, pan3;
	Checkbox chk1, chk2;
	Button bt1, bt2, bt3, bt4;
	
	public SjPanel() {}
	public SjPanel(String str) {
		super(str);
		setLayout(new FlowLayout());
		addWindowListener(new WindowHandler63());
		setSize(300, 120);
		pan1 = new Panel();
		pan2 = new Panel();
		pan3 = new Panel();
		chk1 = new Checkbox("Panel1", true);
		chk2 = new Checkbox("Panel2", true);
		chk1.addItemListener(this);
		chk2.addItemListener(this);
		pan1.setBackground(Color.blue);
		pan2.setBackground(Color.green);
		pan3.setBackground(Color.yellow);
		bt1 = new Button("Button1");
		bt2 = new Button("Button2");
		bt3 = new Button("Button3");
		bt4 = new Button("Button4");
		pan1.add(bt1);
		pan1.add(bt2);
		pan2.add(bt3);
		pan2.add(bt4);
		pan3.add(chk1);
		pan3.add(chk2);
		add(pan1);
		add(pan2);
		add(pan3);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Panel pan = null;
		Checkbox chk = (Checkbox)e.getSource();
		
		if(chk.equals(chk1))
			pan = pan1;
		else if(chk.equals(chk2))
			pan = pan2;
		
		if(e.getStateChange()==ItemEvent.SELECTED)
			pan.setVisible(true);
		else
			pan.setVisible(false);
	}
}