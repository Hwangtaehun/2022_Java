package libraryDB;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;

class Combobox_Inheritance{
	private String parent_name, num, arraystring[];
	private boolean nothing = false;
	public Combobox_Manager child_manager;
	public JComboBox <String> child_combox;
	
	public Combobox_Inheritance() {}
	public Combobox_Inheritance(Combobox_Manager cm, JComboBox <String> cd, String str) {
		this.child_manager = cm;
		this.child_combox = cd;
		parent_name = str;
		num = "";
	}
	
	public void insert_num(String str) {
		num = str;
	}

	public void insert_nothing(boolean bool) {
		nothing = bool;
	}
	
	public String call_parent_name() {
		return parent_name;
	}
	
	public String call_num() {
		return num;
	}
	
	public boolean call_nothing() {
		return nothing;
	}
	
	public void makearray(String str) {
		String sentence = "", sql;
		LbDB_DAO db; 
		ResultSet rs;
		
		db = new LbDB_DAO();
		sql = "SELECT `kind_name` FROM `kind` " + str;
		rs = db.getResultSet(sql);
		try {
			while(rs.next()) {
				sentence += rs.getString("kind_name");
				sentence += "-";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		arraystring = sentence.split("-");
	}
	
	public String[] call_array() {
		return arraystring;
	}
}

class Combobox_Manager {
	private LbDB_DAO db;
	private int fk;
	private String table, key, key_name, sql;
	private String[] arraystring;
	private ResultSet rs;
	private Combobox_Inheritance ci;
	private boolean ci_exist = false;
	private boolean nothing = false;
	public JComboBox <String> combox;
	
	public Combobox_Manager() {}
	public Combobox_Manager(JComboBox <String> cb, String table, String key) {
		combox = cb;
		db = new LbDB_DAO();
		this.table = table;
		this.key = key;
		
		makearray();
		combox = new JComboBox<String>(new DefaultComboBoxModel<String>(arraystring));
		combox.addItemListener(new ComboboxListener());
	}
	public Combobox_Manager(JComboBox <String> cb, String table, String key, String where, boolean bool) {
		combox = cb;
		db = new LbDB_DAO();
		this.table = table;
		this.key = key;
		nothing = bool;
		
		makearray(where);
		combox = new JComboBox<String>(new DefaultComboBoxModel<String>(arraystring));
		combox.addItemListener(new ComboboxListener());
	}
	public Combobox_Manager(Combobox_Inheritance ci, JComboBox <String> cb, String table, String key, String where) {
		combox = cb;
		db = new LbDB_DAO();
		this.table = table;
		this.key = key;
		this.ci = ci;
		ci_exist = true;
		
		makearray(where);
		combox = new JComboBox<String>(new DefaultComboBoxModel<String>(arraystring));
		combox.addItemListener(new ComboboxListener());
	}
	
	private String changenamekey() {
		String str = "";
		char[] temp;
		int cnt = 0;
		
		temp = key.toCharArray();
		for(int i = 0; i < temp.length; i++) {
			if(temp[i] == '_') {
				cnt = i;
			}
		}
		
		for(int i = 0; i < cnt + 1; i++) {
			str += String.valueOf(temp[i]);
		}
		str += "name";
		
		return str;
	}
	
	private void makearray() {
		String sentence = "";
		
		key_name = changenamekey();
		if(nothing) {
			sentence = "없음-";
		}
		sql = "SELECT `" + key_name + "` FROM `" + table + "`";
		rs = db.getResultSet(sql);
		try {
			while(rs.next()) {
				sentence += rs.getString(key_name);
				sentence += "-";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		arraystring = sentence.split("-");
	}
	
	private void makearray(String str) {
		String sentence = "";
		
		key_name = changenamekey();
		sql = "SELECT `" + key_name + "` FROM `" + table + "` " + str;
		rs = db.getResultSet(sql);
		try {
			while(rs.next()) {
				sentence += rs.getString(key_name);
				sentence += "-";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		arraystring = sentence.split("-");
	}
	
	public int foreignkey() {
		return fk;
	}
	
	public class ComboboxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			String choice_str, now_sql = null;
			String pn = "", num = "";
			String array[];
			
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED) {
				choice_str = e.getItem().toString();
				sql = "SELECT * FROM `" + table + "` WHERE " + key_name + " LIKE '" + choice_str + "'";
				System.out.println(sql);
				rs = db.getResultSet(sql);
				
				try {
					while(rs.next()) {
						fk = rs.getInt(key);
						if(key.equals("kind_no")) {
							num = rs.getString("kind_num");
							System.out.println(num);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(ci_exist) {
					pn = ci.call_parent_name();
					ci.insert_num(num);
					if(pn.equals("대분류")) {
					    now_sql = "WHERE `kind_num` LIKE '" + String.valueOf(num.charAt(0)) + "_0'";
					    System.out.println(now_sql);
					}
					else if(pn.equals("중분류")) {
						now_sql = "WHERE `kind_num` LIKE '" + String.valueOf(num.charAt(0)) 
						  			 + String.valueOf(num.charAt(1)) + "_'";
						System.out.println(now_sql);
					}
					ci.child_combox.removeAllItems();
					ci.makearray(now_sql);
					array = ci.call_array();
					for(int i = 0; i < array.length ; i++) {
						ci.child_combox.addItem(array[i]);
					}
				}
			}
		}
	}
}

public class LbDB_main_Frame extends LbDB_Frame {
	protected Client cl;
	protected LbDB_DAO db;
	protected int state, pk;
	protected Container cpane;
	protected JPanel leftPanel, centerPanel;
	protected GridBagLayout gbl;
	protected GridBagConstraints gbc;
	
	protected LbDB_TableMode tablemodel;
	protected ResultSet result;
	protected JTable table;
	protected int dataCount, selectedCol;
	protected String sql, menu_title;
	protected JButton addBt, updateBt, deleteBt, researchBt, clearBt;
	
	public LbDB_main_Frame() {}
	public LbDB_main_Frame(LbDB_DAO db, Client cl) {
		this.db = db;
		this.cl = cl;
		state = cl.state();
		pk = cl.primarykey();
		
		menuform();
		Initform();
		baseform();
		setTitle("메인화면");
		addWindowListener(this);
	}
	
	protected void Initform() {
		cpane = getContentPane();
		centerPanel = new JPanel();
		leftPanel = new JPanel();
		gbl = new GridBagLayout();
		leftPanel.setLayout(gbl);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
	}
	
	protected void menuform() {
		if(state == 1) {
			manager_Initform();
		}
		else {
			member_Initform();
		}
	}
	
	private void baseform() {
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
	
	protected void manager_Initform(){
		JMenuBar menuBar = new JMenuBar(); 
        JMenu materialMenu = new JMenu("책");
        JMenu libraryMenu = new JMenu("도서관");
        JMenu memberMenu = new JMenu("자료 및 회원");
        JMenu lentMenu = new JMenu("대출");
        JMenu bookMenu = new JMenu("종류");
        
        JMenuItem[] menuItems = new JMenuItem[13];
        String[] items = {"책관리", "책추가", "도서관관리", "도서관추가", "회원관리", "자료관리", "자료추가", "대출관리", "대출추가", "예약관리", "상호대차관리", "종류관리", "종류추가"};
        
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
        lentMenu.add(menuItems[9]);
        lentMenu.add(menuItems[10]);
        
        bookMenu.add(menuItems[11]);
        bookMenu.add(menuItems[12]);
        
        menuBar.add(materialMenu);
        menuBar.add(libraryMenu);
        menuBar.add(memberMenu);
        menuBar.add(lentMenu);
        menuBar.add(bookMenu);
        
        setJMenuBar(menuBar);
	}
	
	protected void member_Initform() {
		JMenuBar menuBar = new JMenuBar(); 
        JMenu materialMenu = new JMenu("자료검색");
        JMenuItem m = new JMenuItem("자료검색");
        m.addActionListener(new MenuAction());
        materialMenu.add(m);
        
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
	
	protected void setGrid(GridBagConstraints gbc, int dx, int dy, int width, int height) {
		// TODO Auto-generated method stub
		gbc.gridx = dx;
		gbc.gridy = dy;
		gbc.gridwidth = width;
		gbc.gridheight = height;
	}
	
	protected void closeFrame() {
		setVisible(false);
		dispose();
	}
	
	public class MenuAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String command = e.getActionCommand();
			
			switch(command) {
			case "자료관리": 
				System.out.println("자료관리");
				LbDB_material_Frame frame1 = new LbDB_material_Frame(db, cl, command);
				frame1.setVisible(true);
				break;
			case "자료추가": 
				System.out.println("자료추가");
				LbDB_material_Frame frame2 = new LbDB_material_Frame(db, cl, command);
				frame2.setVisible(true);
				break;
			case "도서관관리": 
				System.out.println("도서관관리");
				LbDB_library_Frame frame4 = new LbDB_library_Frame(db, cl, command);
				frame4.setVisible(true);
				break;
			case "도서관추가": 
				System.out.println("도서관추가");
				LbDB_library_Frame frame5 = new LbDB_library_Frame(db, cl, command);
				frame5.setVisible(true);
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
				LbDB_material_Frame frame3 = new LbDB_material_Frame(db, cl, command);
				frame3.setVisible(true);
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
			case "상호대차관리":
				System.out.println("상호대차");
				break;
			case "종류관리":
				System.out.println("종류관리");
				LbDB_kind_Frame frame6 = new LbDB_kind_Frame(db, cl, command);
				frame6.setVisible(true);
				break;
			case "종류추가":
				System.out.println("종류추가");
				LbDB_kind_Frame frame7 = new LbDB_kind_Frame(db, cl, command);
				frame7.setVisible(true);
				break;
			case "책관리":
				System.out.println("책관리");
				break;
			case "책추가":
				System.out.println("책추가");
				break;
			}
			closeFrame();
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