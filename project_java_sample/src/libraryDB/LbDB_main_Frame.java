package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.time.LocalDate;

class Combobox_Inheritance{
	private String parent_name;
	private boolean nothing = false;
	public Combobox_Manager child_manager;
	public JComboBox <String> child_combox;
	
	public Combobox_Inheritance() {}
	public Combobox_Inheritance(Combobox_Manager cm, JComboBox <String> cb, String str) {
		this.child_manager = cm;
		this.child_combox = cb;
		parent_name = str;
	}
	
	public void insert_nothing(boolean bool) {
		nothing = bool;
	}
	
	public String call_parent_name() {
		return parent_name;
	}
	
	public boolean call_nothing() {
		return nothing;
	}
}

class Combobox_Manager {
	private LbDB_DAO db;
	private int fk = 1;
	private String table, key, key_name, sql, parent_num;
	private String[] arraystring;
	private ResultSet rs;
	private Combobox_Inheritance ci;
	private boolean ci_exist = false;
	private boolean pa_exist = false;
	private boolean dialog = false;
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
	public Combobox_Manager(JComboBox <String> cb, String table, String key, boolean bool) {
		combox = cb;
		fk = 0;
		db = new LbDB_DAO();
		this.table = table;
		this.key = key;
		String str = "없음";
		
		makearray(str, bool);
		combox = new JComboBox<String>(new DefaultComboBoxModel<String>(arraystring));
		combox.addItemListener(new ComboboxListener());
	}
	public Combobox_Manager(JComboBox <String> cb, String table, String key, String where, boolean bool) {
		combox = cb;
		db = new LbDB_DAO();
		this.table = table;
		this.key = key;
		
		makearray(where, bool);
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
		
		makearray(where, false);
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
	
	private void makearray(String str, boolean bool) {
		String sentence = "";
		
		if(str.isEmpty()) {
			return;
		}
		else if(str.equals("없음")) {
			str = "";
		}
		
		if(bool) {
			sentence = "없음-";
		}
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
	
	public void exist_parent(String str) {
		pa_exist = true;
		parent_num = str;
	}
	
	public void isDialog() {
		dialog = true;
	}
	
	public void repaintCombobox(String num) {
		String now_sql = "", pn;
		
		pn = ci.call_parent_name();
		if(pn.equals("대분류")) {
		    now_sql = "WHERE `kind_num` LIKE '" + String.valueOf(num.charAt(0)) + "_0'";
		    //System.out.println(now_sql);
		}
		else if(pn.equals("중분류")) {
			now_sql = "WHERE `kind_num` LIKE '" + String.valueOf(num.charAt(0)) + String.valueOf(num.charAt(1)); 
			if(dialog) {
				now_sql += "_%'";
			}
			else {
				now_sql += "_'";
			}
			//System.out.println(now_sql);
		}
		ci.child_combox.removeAllItems();
		makearray(now_sql, ci.call_nothing());
		for(int i = 0; i < arraystring.length ; i++) {
			ci.child_combox.addItem(arraystring[i]);
		}
	}
	
	public class ComboboxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			String choice_str, terms = "", num = "";
			boolean fk_zero = false;
			
			if(fk == 0) {
				fk_zero = true;
			}
			
			if(e.getStateChange() == ItemEvent.SELECTED) {
				choice_str = e.getItem().toString();
				
				if(fk_zero) {
					fk = 0;
				}
				
				if(pa_exist) {
					if(ci_exist) {
						terms = String.valueOf(parent_num.charAt(0)) + "_0";
					}
					else {
						terms = String.valueOf(parent_num.charAt(0)) + String.valueOf(parent_num.charAt(1)) + "_";
					}
					sql = "SELECT * FROM `" + table + "` WHERE " + key_name + " LIKE '" + choice_str + "'" +
						  " AND kind_num LIKE '" + terms + "'";
				}
				else {
					sql = "SELECT * FROM `" + table + "` WHERE " + key_name + " LIKE '" + choice_str + "'";
				}
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
					ci.child_manager.exist_parent(num);
					repaintCombobox(num);
				}
			}
		}
	}
}

class SwingItem{
	private JTextField tf_bookname, tf_kind, tf_many, tf_mem_id;
	private JComboBox <String> lib_Box;
	
	public SwingItem() {}
	public SwingItem(JComboBox <String> lb, JTextField bn, JTextField ki, JTextField ma) {
		lib_Box = lb;
		tf_bookname = bn;
		tf_kind = ki;
		tf_many = ma;
	}
	
	public SwingItem(JComboBox <String> lb, JTextField bn, JTextField id) {
		lib_Box = lb;
		tf_bookname = bn;
		tf_mem_id = id;
	}
	
	public void set_lib_Box(String str) {
		lib_Box.setSelectedItem(str);
	}
	
	public void set_bookname(String str) {
		tf_bookname.setText(str);
	}
	
	public void set_kind(String str) {
		tf_kind.setText(str);
	}
	
	public void set_many(String str) {
		tf_many.setText(str);
	}
	
	public void set_memid(String str) {
		tf_mem_id.setText(str);
	}
}

public class LbDB_main_Frame extends LbDB_Frame {
	protected Client cl;
	protected LbDB_DAO db;
	protected int state, pk;
	protected foreignkey fk;
	protected Container cpane;
	protected JPanel leftPanel, centerPanel;
	protected GridBagLayout gbl;
	protected GridBagConstraints gbc;
	
	protected LbDB_TableMode tablemodel;
	protected ResultSet result;
	protected JTable table;
	protected int dataCount, selectedCol, ex, st;
	protected String menu_title, sql, sortsql = "";
	protected JButton addBt, updateBt, deleteBt, researchBt, clearBt;
	protected JTextField tf_research, tf_date, tf_memo;
	
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
		selectedCol = -1;
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
        JMenu bookMenu = new JMenu("책");
        JMenu kindMenu = new JMenu("종류");
        JMenu libraryMenu = new JMenu("도서관");
        JMenu memberMenu = new JMenu("자료 및 회원");
        JMenu lentMenu = new JMenu("대출 및 반납");
        JMenu deliveryMenu = new JMenu("예약 및 배송");
        
        JMenuItem[] menuItems = new JMenuItem[15];
        String[] items = {"책관리", "책추가", "종류관리", "종류추가", "도서관관리", "도서관추가", "회원관리", "자료관리", "자료추가", "대출관리", "대출추가", "반납추가", "예약관리", "상호대차관리", "대출장소관리"};
        
        for(int i=0; i<menuItems.length; i++) {
        	 menuItems[i] = new JMenuItem(items[i]); // 메뉴 아이템 컴포넌트 생성
             menuItems[i].addActionListener(new MenuAction()); 
        }        
        
        bookMenu.add(menuItems[0]);
        bookMenu.add(menuItems[1]);
        
        kindMenu.add(menuItems[2]);
        kindMenu.add(menuItems[3]);
        
        libraryMenu.add(menuItems[4]);
        libraryMenu.add(menuItems[5]);
        
        memberMenu.add(menuItems[6]);
        memberMenu.add(menuItems[7]);
        memberMenu.add(menuItems[8]);
        
        lentMenu.add(menuItems[9]);
        lentMenu.add(menuItems[10]); 
        lentMenu.add(menuItems[11]);
        
        deliveryMenu.add(menuItems[12]);        
        deliveryMenu.add(menuItems[13]);
        deliveryMenu.add(menuItems[14]);
        
        menuBar.add(bookMenu);
        menuBar.add(kindMenu);
        menuBar.add(libraryMenu);
        menuBar.add(memberMenu);
        menuBar.add(lentMenu);
        menuBar.add(deliveryMenu);
        
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
        
        JMenu memberMenu = new JMenu("마이페이지");
        JMenuItem[] memberItems = new JMenuItem[2];
        String[] items2 = {"회원정보수정", "회원탈퇴"};
        
        for(int i=0; i < memberItems.length; i++) {
        	memberItems[i] = new JMenuItem(items2[i]);
        	memberItems[i].addActionListener(new MenuAction());
        	memberMenu.add(memberItems[i]);
        }
        
        menuBar.add(materialMenu);
        menuBar.add(libraryMenu);
        menuBar.add(memberMenu);
        
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
	
	protected boolean isInteger(String strValue) {
	    try {
	      Integer.parseInt(strValue);
	      return true;
	    } catch (NumberFormatException ex) {
	      return false;
	    }
	}
	
	protected boolean isFloat(String strValue) {
	    try {
	      Float.parseFloat(strValue);
	      return true;
	    } catch (NumberFormatException ex) {
	      return false;
	    }
	}
	
	protected boolean dateformat_check(String date_string) {
		boolean bool = false;
		String date_array[];
		
		date_array = date_string.split("-");
		
		if(isInteger(date_array[0])) {
			if(isInteger(date_array[1])) {
				if(isInteger(date_array[2])) {
					bool = true;
				}
			}
		}
		
		return bool;
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
			case "회원정보수정": 
				System.out.println("회원정보수정");
				LbDB_mem_info_Frame frame10 = new LbDB_mem_info_Frame(db, cl, command);
				frame10.setVisible(true);
				break;
			case "회원탈퇴":
				System.out.println("회원탈퇴");
				int answer = JOptionPane.showConfirmDialog(null, "탈퇴하시겠습니까?", "회원탈퇴", JOptionPane.YES_NO_OPTION );
				if(answer == JOptionPane.YES_OPTION){
					//사용자가 yes를 눌렀을 떄
					pk = cl.primarykey();
					String sql = "DELETE FROM `member` WHERE `mem_no` = " + pk;
					db.Excute(sql);
					System.out.println("회원정보 삭제");
					closeFrame();
				} else{
					//사용자가 Yes 외 값 입력시
					System.out.println("작업취소");
				}
				break;
			case "회원관리":
				System.out.println("회원관리");
				LbDB_mem_info_Frame frame11 =  new LbDB_mem_info_Frame(db, cl, command);
				frame11.setVisible(true);
				break;
			case "대출관리": 
				System.out.println("대출관리");
				LbDB_lent_Frame frame14 = new LbDB_lent_Frame(db, cl, command);
				frame14.setVisible(true);
				break;
			case "대출추가": 
				System.out.println("대출추가");
				LbDB_lent_Frame frame15 = new LbDB_lent_Frame(db, cl, command);
				frame15.setVisible(true);
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
				LbDB_lent_Frame frame12 = new LbDB_lent_Frame(db, cl, command);
				frame12.setVisible(true);
				break;
			case "모든대출내역":
				System.out.println("모든대출내역");
				LbDB_lent_Frame frame13 = new LbDB_lent_Frame(db, cl, command);
				frame13.setVisible(true);
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
				LbDB_book_Frame frame8 = new LbDB_book_Frame(db, cl, command);
				frame8.setVisible(true);
				break;
			case "책추가":
				System.out.println("책추가");
				LbDB_book_Frame frame9 = new LbDB_book_Frame(db, cl, command);
				frame9.setVisible(true);
				break;
			case "반납추가":
				System.out.println("반납추가");
				LbDB_lent_Frame frame16 = new LbDB_lent_Frame(db, cl, command);
				frame16.setVisible(true);
				break;
			}
			
			if(!command.equals("회원탈퇴"))
			closeFrame();
		}
	}
	
	public class todayButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LocalDate len_date;
			len_date = LocalDate.now(); 
			tf_date.setText(len_date.toString());
		}
	}
	
	public class radiobuttonListener implements ItemListener, ActionListener{
		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd, str_array[];
			
			cmd = e.getActionCommand();
			str_array = cmd.split("-");
			
			for(int i = 0; i < str_array.length; i++) {
				System.out.print(str_array[i]);
			}
			System.out.println();
			
			if(str_array[0].equals("ex")) {
				ex = Integer.parseInt(str_array[1]);
			}
			else {
				st = Integer.parseInt(str_array[1]);
			}
			
			if(cmd.equals("st-0")) {
				tf_date.setText("");
				if(menu_title.equals("대출관리") || menu_title.equals("반납추가")) {
					tf_memo.setText("");
				}
			}
			else if(cmd.equals("st-1")) {
				if(menu_title.equals("대출관리") || menu_title.equals("반납추가")) {
					tf_memo.setText("");
				}
			}
		}
	}
}