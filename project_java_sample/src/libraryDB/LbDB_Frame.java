package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

class Material_foreignkey{
	private int lib_no, book_no, kind_no, mem_no, lent_no, add_no;
	
	public void Material_foreignkey() {
		lib_no = 0;
		book_no = 0;
		kind_no = 0;
		mem_no = 0;
		lent_no = 0;
		add_no = 0;
	}
	
	public void insert_lib_no(int lib_no) {
		this.lib_no = lib_no;
	}
	
	public void insert_book_no(int book_no) {
		this.book_no = book_no;
	}
	
	public void insert_kind_no(int kind_no) {
		this.kind_no = kind_no;
	}
	
	public void insert_mem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	
	public void insert_add_no(int add_no) {
		this.add_no = add_no;
	}
	
	public int call_lib_no() {
		return lib_no;
	}
	
	public int call_book_no() {
		return book_no;
	}
	
	public int call_kind_no() {
		return kind_no;
	}
	
	public int call_mem_no() {
		return mem_no;
	}
	
	public int call_lent_no() {
		return lent_no;
	}
	
	public int call_add_no() {
		return add_no;
	}
}

public class LbDB_Frame extends JFrame implements WindowListener{
	private Client cl;
	private LbDB_DAO db;
	private LbDB_TableMode tablemodel;
	private ResultSet result;
	private JTable table;
	private int state, pk, dataCount, selectedCol;
	private String sql, menu_title;
	private JTextField tf_bookname, tf_author, tf_publish;
	private JButton addBt, updateBt, deleteBt, researchBt, clearBt;
	private Container cpane;
	private JPanel leftPanel, centerPanel;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	private Material_foreignkey mf;
	private JComboBox <String> lib_Box;
	
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
	
	private void manager_Initform(){
		JMenuBar menuBar = new JMenuBar(); 
        JMenu materialMenu = new JMenu("책");
        JMenu libraryMenu = new JMenu("도서관");
        JMenu memberMenu = new JMenu("자료 및 회원");
        JMenu lentMenu = new JMenu("대출");
        JMenu bookMenu = new JMenu("종류");
        
        JMenuItem[] menuItems = new JMenuItem[10];
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
	
	private void bookresearch() {
		String sql, name_content = "";
		String[] libraryname;
		JLabel label;
		
		leftPanel = new JPanel();
		centerPanel = new JPanel();
		
		sql = "SELECT `lib_name` FROM `library`";
		result = db.getResultSet(sql);
		try {
			while(result.next()) {
				name_content += result.getString("lib_name");
				name_content += " ";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		libraryname = name_content.split(" ");
		mf = new Material_foreignkey();
		
		repaint();
		setGrid(gbc,0,1,1,1);
		label = new JLabel("    자료 검색   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,0,2,1,1);
		label = new JLabel("    도서관       ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,2,1,1);
		lib_Box = new JComboBox<String>(new DefaultComboBoxModel<String>(libraryname));
		lib_Box.addItemListener(new ComboboxListener());
		gbl.setConstraints(lib_Box, gbc);
		leftPanel.add(lib_Box);
		setGrid(gbc,0,3,1,1);
		label = new JLabel("    책이름    ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,3,1,1);
		tf_bookname = new JTextField(20);
		gbl.setConstraints(tf_bookname, gbc);
		leftPanel.add(tf_bookname);
		setGrid(gbc,0,4,1,1);
		label = new JLabel("    저자   ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,4,1,1);
		tf_author = new JTextField(20);
		gbl.setConstraints(tf_author, gbc);
		leftPanel.add(tf_author);
		setGrid(gbc,0,5,1,1);
		label = new JLabel("    출판사  ");
		gbl.setConstraints(label, gbc);
		leftPanel.add(label);
		setGrid(gbc,1,5,1,1);
		tf_publish = new JTextField(20);
		gbl.setConstraints(tf_publish, gbc);
		leftPanel.add(tf_publish);
		setGrid(gbc,1,6,1,1);
		researchBt = new JButton("검색");
		researchBt.addActionListener(new researchButtonListener());
		gbl.setConstraints(researchBt, gbc);
		leftPanel.add(researchBt);
		setGrid(gbc,2,6,1,1);
		clearBt = new JButton("지우기");
		clearBt.addActionListener(new clearButtonListener());
		gbl.setConstraints(clearBt, gbc);
		leftPanel.add(clearBt);
		
		String columnName[] = {"도서관", "책 이름", "저자", "출판사", "대출가능"};
		tablemodel = new LbDB_TableMode(columnName.length, columnName);
		table = new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(470, 14*16));
		table.getSelectionModel().addListSelectionListener(new tableListener());
		
		cpane.add("West", leftPanel);
		cpane.add("Center", centerPanel);
		pack();
		
		LoadList();
		
		try {
			result.first();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MoveData();
	}
	
	private void removeTableRow(int row) {
		if(menu_title.equals("자료검색")) {
			table.setValueAt(null, row, 0);
			table.setValueAt(null, row, 1);
			table.setValueAt(null, row, 2);
			table.setValueAt(null, row, 3);
			table.setValueAt(null, row, 4);
		}
		else {
			
		}
	}
	
	private void MoveData() {
		try {
			/*
			if(menu_title.equals("자료검색")) {
				String libraryname = result.getString("lib_name");
				String bookname = 
			}
			tf_zipcode.setText(result.getString("zipcode"));
			tf_address.setText(address);
			fk.insert_add_no(result.getInt("add_no"));
			*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void LoadList() {
		if(menu_title.equals("자료검색")) {
			String lent_re_state = "대출불가";
			sql = "SELECT library.lib_name, book.book_name, book.book_author, book_publish, lent.len_re_st " +
				  "FROM library, book, material LEFT JOIN lent ON material.mat_no = lent.mat_no " + 
				  "WHERE library.lib_no = material.lib_no AND book.book_no = material.book_no";
			result = db.getResultSet(sql);
			
			for(int i = 0; i < dataCount; i++) {
				removeTableRow(i);
			}
			try {
				for(dataCount = 0; result.next(); dataCount++) {
					table.setValueAt(result.getString("library.lib_name"), dataCount, 0);
					table.setValueAt(result.getString("book.book_name"), dataCount, 1);
					table.setValueAt(result.getString("book.book_author"), dataCount, 2);
					table.setValueAt(result.getString("book_publish"), dataCount, 3);
					if(result.getString("lent.len_re_st").isEmpty() || result.getString("lent.len_re_st").equals("1")) {
						lent_re_state = "대출가능";
					}
					else {
						lent_re_state = "대출불가";
					}
					table.setValueAt(lent_re_state, dataCount, 4);
				}
				repaint();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			
		}
	}
	
	public class MenuAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String command = e.getActionCommand();
			menu_title = command;
			
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
	
	public class ComboboxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			String lib_name, sql;
			ResultSet rs;
			
			// TODO Auto-generated method stub
			if(e.getStateChange() == ItemEvent.SELECTED) {
				lib_name = e.getItem().toString();
				sql = "SELECT * FROM `library WHERE '" + lib_name + "'";
				rs = db.getResultSet(sql);
				
				try {
					while(rs.next()) {
						mf.insert_lib_no(rs.getInt("lib_no"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	public class researchButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class addButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}	
	}
	
	public class updateButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}	
	}
	
	public class deleteButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}	
	}
	
	public class clearButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}	
	}
	
	class tableListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting())
				return;
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			if(lsm.isSelectionEmpty())
				System.out.println("No columns are selected");
			else {
				selectedCol = lsm.getMinSelectionIndex();
				if(selectedCol >= dataCount)
					System.out.println("data is Empty");
				else {
					if(menu_title.equals("자료검색")) {
						lib_Box.setSelectedItem(table.getValueAt(selectedCol, 0).toString());
						tf_bookname.setText(table.getValueAt(selectedCol, 1).toString());
						tf_author.setText(table.getValueAt(selectedCol, 2).toString());
						tf_publish.setText(table.getValueAt(selectedCol, 3).toString());
					}
					else {
						
					}
					try {
						result.absolute(selectedCol + 1);
						MoveData();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					repaint();
				}
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