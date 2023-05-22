package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

class Library {
	public String lib_name, lib_detail, lib_date;
	public int lib_no, add_no;
}

public class LbDB_Frame extends JFrame implements WindowListener{
	private Client cl;
	private LbDB_DAO db;
	private LbDB_TableMode tablemodel;
	private ResultSet result;
	private JTable table;
	private int state, pk;
	private Container cpane;
	private JPanel leftPanel, centerPanel;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	private Library lib;
	
	public LbDB_Frame() {}
	public LbDB_Frame(LbDB_DAO db, Client cl) {
		super("메인 화면");
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
		leftPanel = new JPanel();
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
        JMenu materialMenu = new JMenu("자료");
        JMenu libraryMenu = new JMenu("도서관");
        JMenu memberMenu = new JMenu("회원");
        JMenu lentMenu = new JMenu("대출");
        JMenu bookMenu = new JMenu("예약");
        
        JMenuItem[] menuItems = new JMenuItem[8];
        String[] items = {"자료관리", "자료추가", "도서관관리", "도서관추가", "회원관리", "대출관리", "대출추가", "예약관리"};
        
        for(int i=0; i<menuItems.length; i++) {
        	 menuItems[i] = new JMenuItem(items[i]); // 메뉴 아이템 컴포넌트 생성
             menuItems[i].addActionListener(new MenuAction()); 
        }        
        
        materialMenu.add(menuItems[0]);
        materialMenu.add(menuItems[1]);
        
        libraryMenu.add(menuItems[2]);
        libraryMenu.add(menuItems[3]);
        
        memberMenu.add(menuItems[4]);
        
        lentMenu.add(menuItems[5]);
        lentMenu.add(menuItems[6]);
        
        bookMenu.add(menuItems[7]);
        
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
	
	private void materialform(String str) {
		String sql, content = "";
		String[] libraryname;
		JLabel label;
		
		sql = "SELECT `lib_name` FROM `library`";
		result = db.getResultSet(sql);
		try {
			while(result.next()) {
				content += result.getString("lib_name");
				content += " ";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		libraryname = content.split(" ");
		lib = new Library();
		
		repaint();
		setGrid(gbc,0,1,1,1);
		label = new JLabel("    " + str + "   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,0,2,1,1);
		label = new JLabel("    도서관    ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,2,1,1);
		JComboBox <String> lib_Box = new JComboBox<String>(new DefaultComboBoxModel<String>(libraryname));
		lib_Box.addItemListener(new ComboboxListener());
		gbl.setConstraints(lib_Box, gbc);
		leftPanel.add(lib_Box);
		setGrid(gbc,0,3,1,1);
		label = new JLabel("    책이름    ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,3,1,1);
		label = new JLabel("    책이름    ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
	}
	
	class MenuAction implements ActionListener{
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
			}
		}
	}
	
	public class ComboboxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED) {
				lib.lib_name = e.getItem().toString();
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("종료됨 !!");
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}