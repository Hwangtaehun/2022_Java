package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import libraryDB.LbDB_main.Client;

public class LbDB_Login_Dialog extends JDialog{
	private LbDB_DAO logDB;
	private Client logCL;
	private JTextField tf_Id, tf_Pw;
	
	LbDB_Login_Dialog() {}
	LbDB_Login_Dialog(LbDB_DAO db, Client cl){
		logDB = db;
		logCL = cl;
	}
	
	void initform() {
		Container cpane = getContentPane();
		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel southPanel = new JPanel();
		JButton bt_Login, bt_singup;
		JLabel label;
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		centerPanel.setLayout(gbl);
		setGrid(gbc, 0, 0, 1, 1);
		label = new JLabel("  아이디 ");
		gbl.setConstraints(label, gbc);
		centerPanel.add(label);
		setGrid(gbc, 1, 0, 1, 1);
		tf_Id = new JTextField(5);
		gbl.setConstraints(tf_Id, gbc);
		centerPanel.add(tf_Id);
		setGrid(gbc, 0, 1, 1, 1);
		label = new JLabel(" 비밀번호 ");
		gbl.setConstraints(label, gbc);
		centerPanel.add(label);
		setGrid(gbc, 1, 1, 1, 1);
		tf_Pw = new JTextField(5);
		gbl.setConstraints(tf_Pw, gbc);
		centerPanel.add(tf_Pw);
		
		
	}
	
	private void setGrid(GridBagConstraints gbc, int dx, int dy, int width, int height) {
		// TODO Auto-generated method stub
		gbc.gridx = dx;
		gbc.gridy = dy;
		gbc.gridwidth = width;
		gbc.gridheight = height;
	}

	public class logbutton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class registerbutton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
