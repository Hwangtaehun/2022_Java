package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class LbDB_main_Frame extends LbDB_Frame {
	private Client cl;
	private LbDB_DAO db;
	private int state, pk;
	private String menu_title;
	private Container cpane;
	private JPanel centerPanel;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	
	public LbDB_main_Frame() {}
	public LbDB_main_Frame(LbDB_DAO db, Client cl) {
		this.db = db;
		this.cl = cl;
		state = cl.state();
		pk = cl.primarykey();
		
		if(state == 1) {
			manager_Initform();
		}
		else {
			member_Initform();
		}
		
		Initform();
		addWindowListener(this);
	}
	
	private void Initform() {
		cpane = getContentPane();
		centerPanel = new JPanel();
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		JLabel context;
		String text;
		
		setGrid(gbc,2,0,1,1);
		text = "위에 있는 메뉴를 클릭해주세요.";
		context = new JLabel(text);
		context.setFont(new Font("SansSerif", Font.PLAIN, 30));
		gbl.setConstraints(context, gbc);
		centerPanel.add(context);
		
		if(state == 2) {
			setGrid(gbc,2,1,1,1);
		    text = "정지된 계정입니다.";
		    context = new JLabel(text);
			context.setFont(new Font("SansSerif", Font.PLAIN, 30));
			gbl.setConstraints(context, gbc);
			centerPanel.add(context);
		}
		
		cpane.add("Center", centerPanel);
		pack();
	}
	
	private void manager_Initform(){
		JMenuBar menuBar = new JMenuBar(); 
        JMenu materialMenu = new JMenu("책");
        JMenu libraryMenu = new JMenu("도서관");
        JMenu memberMenu = new JMenu("자료 및 회원");
        JMenu lentMenu = new JMenu("대출");
        JMenu bookMenu = new JMenu("종류");
        
        JMenuItem[] menuItems = new JMenuItem[12];
        String[] items = {"책관리", "책추가", "도서관관리", "도서관추가", "회원관리", "자료관리", "자료추가", "대출관리", "대출추가", "예약관리", "종류관리", "종류추가"};
        
        for(int i=0; i<menuItems.length; i++) {
        	 menuItems[i] = new JMenuItem(items[i]); // 메뉴 아이템 컴포넌트 생성
             menuItems[i].addActionListener(new MenuAction()); 
        }        
        
        materialMenu.add(menuItems[0]);
        materialMenu.add(menuItems[1]);
        
        libraryMenu.add(menuItems[2]);
        libraryMenu.add(menuItems[3]);
        
        memberMenu.add(menuItems[4]);
        memberMenu.add(menuItems[5]);
        memberMenu.add(menuItems[6]);
        
        lentMenu.add(menuItems[7]);
        lentMenu.add(menuItems[8]);
        
        bookMenu.add(menuItems[9]);
        bookMenu.add(menuItems[10]);
        bookMenu.add(menuItems[11]);
        
        menuBar.add(materialMenu);
        menuBar.add(libraryMenu);
        menuBar.add(memberMenu);
        menuBar.add(lentMenu);
        menuBar.add(bookMenu);
        
        setJMenuBar(menuBar);
	}
	
	private void member_Initform() {
		JMenuBar menuBar = new JMenuBar(); 
        JMenu materialMenu = new JMenu("자료 검색");
        materialMenu.addActionListener(new MenuAction());
        
        JMenu libraryMenu = new JMenu("내서재");
        JMenuItem[] menuItems = new JMenuItem[4];
        String[] items = {"대출중도서", "모든대출내역", "예약내역", "상호대차"};
        
        for(int i=0; i<menuItems.length; i++) {
        	 menuItems[i] = new JMenuItem(items[i]); // 메뉴 아이템 컴포넌트 생성
             menuItems[i].addActionListener(new MenuAction());
             libraryMenu.add(menuItems[i]);
        }
        
        menuBar.add(materialMenu);
        menuBar.add(libraryMenu);
        
        setJMenuBar(menuBar);
	}
	
	private void setGrid(GridBagConstraints gbc, int dx, int dy, int width, int height) {
		// TODO Auto-generated method stub
		gbc.gridx = dx;
		gbc.gridy = dy;
		gbc.gridwidth = width;
		gbc.gridheight = height;
	}
	
	public class MenuAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String command = e.getActionCommand();
			
			switch(command) {
			case "자료관리": 
				System.out.println("자료관리");
				break;
			case "자료추가": 
				System.out.println("자료추가");
				break;
			case "도서관관리": 
				System.out.println("도서관관리");
				break;
			case "도서관추가": 
				System.out.println("도서관추가");
				break;
			case "회원관리": 
				System.out.println("회원관리");
				break;
			case "대출관리": 
				System.out.println("대출관리");
				break;
			case "대출추가": 
				System.out.println("대출관리");
				break;
			case "예약관리":
				System.out.println("예약관리");
				break;
			case "자료검색":
				System.out.println("자료검색");
				break;
			case "대출중도서":
				System.out.println("대출중도서");
				break;
			case "모든대출내역":
				System.out.println("모든대출내역");
				break;
			case "예약내역":
				System.out.println("예약내역");
				break;
			case "상호대차":
				System.out.println("상호대차");
				break;
			case "종류관리":
				System.out.println("종류관리");
				break;
			case "종류추가":
				System.out.println("종류추가");
				break;
			}
		}
	}
	
	/*
	addBt = new JButton("등록");
	addBt.addActionListener(new addButtonListener());
	updateBt = new JButton("수정");
	updateBt.addActionListener(new updateButtonListener());
	deleteBt = new JButton("삭제");
	deleteBt.addActionListener(new deleteButtonListener());
	researchBt = new JButton("검색");
	researchBt.addActionListener(new researchButtonListener());
	clearBt = new JButton("지우기");
	clearBt.addActionListener(new clearButtonListener());
	*/
}