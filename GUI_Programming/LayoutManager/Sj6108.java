package LayoutManager;
import java.awt.*;

public class Sj6108 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sj06Class8 sj = new Sj06Class8("LayOut Manager 없는 Component ");
		sj.setVisible(true);
	}
}

class Sj06Class8 extends Frame{
	public Sj06Class8() {}
	public Sj06Class8(final String str) {
		super(str);
		Panel pan1 = new Panel();
		Panel pan2 = new Panel();
		
		setSize(350, 350);
		setBackground(Color.yellow);
		setLayout(null);
		
		pan1.setSize(100, 200);
		pan1.setBackground(Color.blue);
		
		pan2.setBounds(120, 150, 100, 150);
		pan2.setBackground(Color.green);
		
		add(pan1);
		add(pan2);
		
		Button bt1 = new Button("첫번째 버튼");
		Button bt2 = new Button("두번째 버튼");
		
		bt1.setBounds(120, 50, 100, 50);
		bt2.setBounds(240, 50, 100, 100);
		
		add(bt1);
		add(bt2);
	}
}