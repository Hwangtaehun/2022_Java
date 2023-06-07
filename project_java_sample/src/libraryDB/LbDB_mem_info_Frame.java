package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class LbDB_mem_info_Frame extends LbDB_main_Frame{
	private JTextField tf_name, tf_Id, tf_zipcode, tf_address, tf_detail;
	private JPasswordField tf_Pw, tf_Pw2;
	private JButton bt_complate;
	private foreignkey fk;
	private String title;
	
	public LbDB_mem_info_Frame() {}
	public LbDB_mem_info_Frame(LbDB_DAO db, String title) {
		this.db = db;
		this.title = title;
		fk = new foreignkey();
		initform();
		dialog("회원 가입");
	}
	public LbDB_mem_info_Frame(LbDB_DAO db, Client cl,  String title) {
		this.db = db;
		this.title = title;
		fk = new foreignkey();
		pk = cl.primarykey();
		initform();
		textfield_setText();
		setTitle(title);
		addWindowListener(this);
	}
	
	private void initform() {
		Container cpane = getContentPane();
		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel southPanel = new JPanel();
		JButton bt_clear, bt_duplicate, bt_address, bt_pw;
		JLabel label;
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		label = new JLabel(title);
		northPanel.add("Center", label);
		
		centerPanel.setLayout(gbl);
		setGrid(gbc, 0, 0, 1, 1);
		label = new JLabel("이 름");
		gbl.setConstraints(label, gbc);
		centerPanel.add(label);
		setGrid(gbc, 1, 0, 1, 1);
		tf_name = new JTextField(5);
		gbl.setConstraints(tf_name, gbc);
		centerPanel.add(tf_name);
		setGrid(gbc, 0, 1, 1, 1);
		label = new JLabel("아이디");
		gbl.setConstraints(label, gbc);
		centerPanel.add(label);
		setGrid(gbc, 1, 1, 1, 1);
		tf_Id = new JTextField(5);
		gbl.setConstraints(tf_Id, gbc);
		centerPanel.add(tf_Id);
		if(title.equals("회원 가입")) {
			setGrid(gbc, 2, 1, 1, 1);
			bt_duplicate = new JButton("중복 확인");
			bt_duplicate.addActionListener(new DuplicateButtonListener());
			gbl.setConstraints(bt_duplicate, gbc);
			centerPanel.add(bt_duplicate);
		}
		setGrid(gbc, 0, 2, 1, 1);
		label = new JLabel("비밀번호");
		gbl.setConstraints(label, gbc);
		centerPanel.add(label);
		setGrid(gbc, 1, 2, 1, 1);
		tf_Pw = new JPasswordField(5);
		gbl.setConstraints(tf_Pw, gbc);
		centerPanel.add(tf_Pw);
		if(title.equals("회원정보수정")) {
			setGrid(gbc, 2, 2, 1, 1);
			bt_pw = new JButton("비밀번호수정");
			bt_pw.addActionListener(new PasswordChangeButtonListener());
			gbl.setConstraints(bt_pw, gbc);
			centerPanel.add(bt_pw);
		}
		setGrid(gbc, 0, 3, 1, 1);
		label = new JLabel("비밀번호 확인");
		gbl.setConstraints(label, gbc);
		centerPanel.add(label);
		setGrid(gbc, 1, 3, 1, 1);
		tf_Pw2 = new JPasswordField(5);
		gbl.setConstraints(tf_Pw2, gbc);
		centerPanel.add(tf_Pw2);
		setGrid(gbc, 0, 4, 1, 1);
		label = new JLabel("우편번호");
		gbl.setConstraints(label, gbc);
		centerPanel.add(label);
		setGrid(gbc, 1, 4, 1, 1);
		tf_zipcode = new JTextField(5);
		tf_zipcode.setEnabled(false);
		gbl.setConstraints(tf_zipcode, gbc);
		centerPanel.add(tf_zipcode);
		setGrid(gbc, 2, 4, 1, 1);
		bt_address = new JButton("우편검색");
		bt_address.addActionListener(new AddressButtonListener());
		gbl.setConstraints(bt_address, gbc);
		centerPanel.add(bt_address);
		setGrid(gbc, 0, 5, 1, 1);
		label = new JLabel("주소");
		gbl.setConstraints(label, gbc);
		centerPanel.add(label);
		setGrid(gbc, 1, 5, 1, 1);
		tf_address = new JTextField(50);
		tf_address.setEnabled(false);
		gbl.setConstraints(tf_address, gbc);
		centerPanel.add(tf_address);
		setGrid(gbc, 0, 6, 1, 1);
		label = new JLabel("상세 주소");
		gbl.setConstraints(label, gbc);
		centerPanel.add(label);
		setGrid(gbc, 1, 6, 1, 1);
		tf_detail = new JTextField(50);
		gbl.setConstraints(tf_detail, gbc);
		centerPanel.add(tf_detail);
		
		southPanel.setLayout(gbl);
		setGrid(gbc, 0,0,1,1);
		bt_complate = new JButton("완료");
		bt_complate.addActionListener(new ComplateButtonListener()); 
		bt_complate.setEnabled(false);
		southPanel.add(bt_complate);
		setGrid(gbc, 1,0,1,1);
		bt_clear = new JButton("지우기");
		bt_clear.addActionListener(new ClearButtonListener());
		southPanel.add(bt_clear);
		
		cpane.add("North", northPanel);
		cpane.add("Center", centerPanel);
		cpane.add("South", southPanel);
		pack();
	}
	
	private void textfield_setText() {
		String now_sql, address, under = "";
		int add_no = 0;
		
		tf_Id.setEnabled(false);
		tf_Pw.setEnabled(false);
		tf_Pw2.setEnabled(false);
		bt_complate.setEnabled(true);
		now_sql = "SELECT * FROM `member` WHERE `mem_no` = " + pk;
		result = db.getResultSet(now_sql);
		
		try {
			while(result.next()) {
				tf_name.setText(result.getString("mem_name"));
				tf_Id.setText(result.getString("mem_id"));
				tf_Pw.setText(result.getString("mem_pw"));
				tf_Pw2.setText(result.getString("mem_pw"));
				add_no = result.getInt("add_no");
				fk.insert_add_no(add_no);
				tf_detail.setText(result.getString("mem_detail"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		now_sql = "SELECT * FROM `address` WHERE `add_no` = " + add_no;
		result = db.getResultSet(now_sql);
		
		try {
			while(result.next()) {
				tf_zipcode.setText(result.getNString("zipcode"));
				
				if(result.getString("under_yn").equals("1")) {
					under = "지하";
				}
				
				address = result.getString("sido") + " " + result.getString("sigungu") + " " + 
						  result.getString("doro") + " " + under + " " +
						  result.getString("buildno1") + "-" + result.getString("buildno2") + "(" +
						  result.getString("eupmyun") + " " + result.getString("dong") + " " + 
				          result.getString("ri") + " " + result.getString("jibun1") + "-" + result.getString("jibun2") + ")";
				
				tf_address.setText(address);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * private void closeDialog() { this.setVisible(false); this.dispose(); }
	 */
	
	class AddressButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LbDB_zipcode_Dialog add_Dialog = new LbDB_zipcode_Dialog(tf_zipcode, tf_address, fk);
			add_Dialog.setVisible(true);
		}
		
	}
	
	class ComplateButtonListener implements ActionListener{
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int add_no;
			String sql;
			
			if(tf_name.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요.",  title + " 오류", JOptionPane.PLAIN_MESSAGE);
			}
			else if(tf_Pw.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.",  title + " 오류", JOptionPane.PLAIN_MESSAGE);
			}
			else if(tf_zipcode.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "주소를 입력해주세요.",  title + " 오류", JOptionPane.PLAIN_MESSAGE);
			}
			else if(!tf_Pw.getText().equals(tf_Pw2.getText())) {
				JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력해주세요.",  title + " 오류", JOptionPane.PLAIN_MESSAGE);
			}
			else {
				add_no = fk.call_add_no();
				if(title.equals("회원 가입")) {
					sql = "INSERT INTO `member` (`mem_name`, `mem_id`, `mem_pw`, `add_no`, `mem_detail`) VALUES ('" + tf_name.getText() + 
							     "', '" + tf_Id.getText() + "', '" + tf_Pw.getText() + "', " + add_no + ", '" + tf_detail.getText() + "')";
					System.out.println(sql);
					
					db.Excute(sql);
					closeFrame();
				}
				else {
					sql = "UPDATE `member` SET `mem_name` = '" + tf_name.getText() + "', `mem_pw` = '" + tf_Pw.getText() +
						  "', `add_no` = " + add_no + ", `mem_detail` = '" + tf_detail.getText() + "' WHERE `mem_no` = " + pk;
					db.Excute(sql);
					tf_Pw.setEnabled(false);
					tf_Pw2.setEnabled(false);
				}
			}
		}
		
	}
	
	class ClearButtonListener implements ActionListener{ //요부분 수정
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			tf_name.setText("");
			tf_Id.setText("");
			tf_zipcode.setText("");
			tf_address.setText("");
			tf_detail.setText("");
			if(title.equals("회원 가입")) {
				tf_Pw.setText("");
				tf_Pw2.setText("");
				bt_complate.setEnabled(false);
			}
		}
		
	}
	
	class DuplicateButtonListener implements ActionListener{
		ResultSet rs;
		String sql;
		boolean check = true;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(tf_Id.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.",  title + " 오류", JOptionPane.PLAIN_MESSAGE);
			}
			else {
				sql = "SELECT `mem_id` FROM `member`";
				rs = db.getResultSet(sql);
				
				try {
					while(rs.next()) {
						if(rs.getString("mem_id").equals(tf_Id.getText()))
							check = false;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(check) {
					bt_complate.setEnabled(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "아이디 중복 되었습니다. 다른 아이디를 입력해주세요.",  "아이디 중복", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}
	
	class PasswordChangeButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			tf_Pw.setText("");
			tf_Pw2.setText("");
			tf_Pw.setEnabled(true);
			tf_Pw2.setEnabled(true);
		}
	}
}