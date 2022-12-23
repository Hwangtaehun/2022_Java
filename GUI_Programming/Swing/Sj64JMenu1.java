package Swing;
import javax.swing.*;

public class Sj64JMenu1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjMenu frame = new SjMenu("메뉴 연습 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

class SjMenu extends JFrame{
	public SjMenu() {}
	public SjMenu(String str) {
		super(str);
		setSize(300, 200);
		
		JMenuBar mb = new JMenuBar();
		
		JMenu fileMenu = new JMenu("파일");
		fileMenu.add(new JMenuItem("새 파일"));
		fileMenu.add("열기");
		fileMenu.add("저장");
		fileMenu.add("다른 이름으로 저장");
		fileMenu.add("닫기");
		fileMenu.addSeparator();
		fileMenu.add("인쇄");
		fileMenu.addSeparator();
		fileMenu.add("종료");
		
		JMenu editMenu = new JMenu("편집");
		editMenu.add("잘라내기");
		editMenu.add("복사");
		editMenu.add("붙여넣기");
		editMenu.add("지우기");
		
		mb.add(fileMenu);
		mb.add(editMenu);
		setJMenuBar(mb);
	}
}