package Grade;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class SjDB2_Frame extends JFrame{
	SjDB2_DAO db;
	JButton firstBt, lastBt, previousBt, nextBt, newFileBt, insertBt, updateBt, deleteBt, sortBt, closeBt;
	JTextField number, name, kor, mat, eng;
	JPanel pan1, pan11, pan12, pan13;
	SjDB2_TableMode table;
	
	
	public SjDB2_Frame() {}
	public SjDB2_Frame(SjDB2_DAO db) {
		super();
		this.db = db;
		initForm();
	}
	
	void initForm() {
		pan1 = new JPanel();
		pan11 = new JPanel();
		pan12 = new JPanel();
		pan13 = new JPanel();
		table = new SjDB2_TableMode(db, this);
		
		number = new JTextField(10);
		name = new JTextField(10);
		kor = new JTextField(10);
		mat = new JTextField(10);
		eng = new JTextField(10);
		
		firstBt = new JButton("처음");
		firstBt.addActionListener(new firstHandler());
		previousBt = new JButton("이전");
		previousBt.addActionListener(new previousHandler());
		nextBt = new JButton("다음");
		nextBt.addActionListener(new nextHandler());
		lastBt = new JButton("마지막");
		lastBt.addActionListener(new lastHandler());
		newFileBt = new JButton("새자료");
		newFileBt.addActionListener(new newFileHandler());
		insertBt = new JButton("등록");
		insertBt.addActionListener(new insertHandler());
		updateBt = new JButton("수정");
		updateBt.addActionListener(new updateHandler());
		deleteBt = new JButton("삭제");
		deleteBt.addActionListener(new deleteHandler());
		sortBt = new JButton("정렬");
		sortBt.addActionListener(new sortHandler());
		closeBt = new JButton("종료");
		closeBt.addActionListener(new closeHandler());
		
		pan1.setLayout(new BorderLayout());
		pan11.setLayout(new GridLayout(8, 2, 0, 2));
		pan11.add(new JLabel());
		pan11.add(new JLabel());
		pan11.add(new JLabel("           번 호"));
		pan11.add(number);
		pan11.add(new JLabel("           이 름"));
		pan11.add(name);
		pan11.add(new JLabel("           국 어"));
		pan11.add(kor);
		pan11.add(new JLabel("           수 학"));
		pan11.add(mat);
		pan11.add(new JLabel("           영 어"));
		pan11.add(eng);
		pan12.add(firstBt);
		pan12.add(previousBt);
		pan12.add(nextBt);
		pan12.add(lastBt);
		pan13.setLayout(new GridLayout(8, 1, 0, 0));
		pan13.add(newFileBt);
		pan13.add(insertBt);
		pan13.add(updateBt);
		pan13.add(deleteBt);
		pan13.add(new JLabel());
		pan13.add(sortBt);
		pan13.add(new JLabel());
		pan13.add(closeBt);
		pan1.add("Center", pan11);
		pan1.add("South", pan12);
		pan1.add("East", pan13);
		
		Container cpane;
		cpane = getContentPane();
		cpane.add("West", pan1);
		cpane.add("East", table);
		pack();
	}
	
	void setTextField(ResultSet rs) {
		String number = null, name = null;
		int kor = 0, mat = 0, eng = 0;
		try {
			number = (rs.getString("strCode")).trim();
			name = (rs.getString("strName")).trim();
			kor = rs.getInt("nKor");
			mat = rs.getInt("nMat");
			eng = rs.getInt("nEng");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.number.setText(number);
		this.name.setText(name);
		this.kor.setText(Integer.toString(kor));
		this.mat.setText(Integer.toString(mat));
		this.eng.setText(Integer.toString(eng));
	}
	
	class firstHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ResultSet rs;
			String sql = "Select * FROM Score order by strCode";
			rs = db.getResultSet(sql);
			try {
				rs.first();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setTextField(rs);
		}
	}

	class lastHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ResultSet rs;
			String sql = "Select * FROM Score order by strCode";
			rs = db.getResultSet(sql);
			try {
				rs.last();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setTextField(rs);
		}
	}

	class previousHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ResultSet rs;
			String sql = "Select * FROM Score order by strCode";
			rs = db.getResultSet(sql);
			try {
				rs.relative(-1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setTextField(rs);
		}
	}

	class nextHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ResultSet rs;
			String sql = "Select * FROM Score order by strCode";
			rs = db.getResultSet(sql);
			try {
				rs.relative(1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setTextField(rs);
		}
	}

	class newFileHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}

	class insertHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String i, j;
			i = number.getText();
			j = name.getText();
			String sql = "INSERT INTO Score VALUES("+ "'" + i + "'," + "'" + j + "'," + kor.getText() + "," + 
													mat.getText() + "," + eng.getText() + "," + 0 + ","+ 
													0 + "," + 0 +")";
			db.Excute(sql);
			try {
				db.totAvg();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			table.setTable();
		}
	}

	class updateHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String i = number.getText();
			String sql = "UPDATE Score SET strName = '" + name.getText() + "',nkor = " +
						kor.getName() + ",nMat = " + mat.getText() + ",nEng = " + eng.getText() + 
						" WHERE strCode = " + "'" + i + "'";
			db.Excute(sql);
			try {
				db.totAvg();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			table.setTable();
		}
	}

	class deleteHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String i = number.getText();
			String sql = "DELETE FROM Score WHERE strCode LIKE '" + i + "'";
			db.Excute(sql);
			table.setTable();
		}
	}

	class sortHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				db.rank();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			table.setTable();
		}
	}

	class closeHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(1);
		}
	}
}

