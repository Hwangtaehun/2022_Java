package GraphicsContext;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Sj71Font {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjFontPanel frame = new SjFontPanel("Font 연습");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
		frame.setVisible(true);
	}
}

class SjFontPanel extends JFrame implements ListSelectionListener{
	private MyPanel pan;
	private JList <String>list;
	private String fontName3;
	
	public SjFontPanel() {}
	public SjFontPanel(String str) {
		super(str);
		
		fontName3 = "궁서체";
		
		pan = new MyPanel();
		pan.setBackground(Color.green);
		
		String systemFontName[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		list = new JList<String>();
		
		list.setListData(systemFontName);
		JScrollPane scrollPane1 = new JScrollPane(list);
		list.addListSelectionListener(this);
		
		getContentPane().add(scrollPane1, "East");
		getContentPane().add(pan, "Center");
	}
	
	class MyPanel extends JPanel{
		public void paint(Graphics g) {
			Font  font1 = new Font("SansSerif", Font.PLAIN, 10);
			Font  font2 = new Font("Serif", Font.ITALIC, 25);
			Font  font3 = new Font(fontName3, Font.BOLD, 25);
			
			Color c = new Color(255, 0, 0);
			g.setFont(font1);
			g.setColor(c);
			g.drawString("기본 글꼴 Default Font", 10, 40);
			
			g.setFont(font2);
			g.setColor(Color.blue);
			g.drawString("기울임 글꼴 Italic Font", 10, 60);
			
			g.setFont(font3);
			g.setColor(new Color(128,128,128));
			g.drawString("굵은 글꼴 Bold Font", 10, 100);
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		fontName3 = list.getSelectedValue();
		System.out.println("list2 선택  : " + fontName3);
		repaint();
		//pan.repaint();
	}
	
}