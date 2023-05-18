package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import test.DB_DAO;

import java.sql.*;

class Addresstool{
	private String sidobasic[] = {"서울특별시", "부산광역시", "대전광역시", "대구광역시", "인천광역시", "광주광역시", "울산광역시",
							 "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도",
							 "세종특별자치시", "강원도", "경기도", "서울시", "부산시", "대전시", "대구시", "인천시", "광주시", "울산시",
							 "충북", "충남", "전북", "전남", "경북", "경남", "제주도", "세종시"};
	private String sigungusp[] = {"고양시", "성남시", "수원시", "안산시", "안양시", "용인시", "창원시", "포항시", "전주시", "천안시", "청주시",
								  "고양",   "성남",  "수원",  "안산",   "안양",  "용인",  "창원",   "포항",  "전주",   "천안",  "청주"};
	private String main[];
	private DB_DAO db;
	private int i;
	private ResultSet rs;
	public String sido, sigungu, eupmyun, doro, buildno1, buildno2, dong, dong_at, ri, jibun1, jibun2;
	
	public Addresstool() {}
	public Addresstool(String str, DB_DAO db) {
		this.db = db;
		String word = str.trim();
		main = word.split(" ");
		//print();
		sido = "%";
		sigungu = "%";
		eupmyun = "%";
		doro = "%";
		dong = "%";
		dong_at = "dong_hj";
		ri = "%";
		buildno1 = "%";
		buildno2 = "%";
		jibun1 = "%";
		jibun2 = "%";
		sort();
	}
	
	public void print() {
		for(int i = 0; i < main.length; i++) {
			System.out.print(main[i]+ " ");
		}
	}
	
	public void print2() {
		System.out.print(sido + sigungu + eupmyun + dong + ri + doro);
	}
	
	private void sort() {
		for(i = 0; i < sidobasic.length; i++) {
			if(main[0].equals(sidobasic[i])) {
				if(i > 16) {
					int num = i % 17;
					sido = sidobasic[num];
				}
				else {
					sido = main[0];
				}
			}
		}
		
		i = 0;
		if(!sido.equals("%")) {
			i++;
		}
		
		sigungu(main[i]);
		eupmyun(main[i]);
		dong(main[i]);
		ri(main[i]);
		
		if(dong.equals("%") && ri.equals("%")) {
			doro(main[i]);
		}
		
		number(main[i]);
	}
	
	private void sigungu(String str) {
		String word = str;
		for(int j = 0; j < sigungusp.length; j++) {
			if(str.equals(sigungusp[j])) {
				if(j > 10) {
					int num = j % 11;
					word = sigungusp[num];
				}
				i++;
				word += " " + main[i];
			}
		}
		
		String sql = "SELECT DISTINCT `sigungu` FROM `address` WHERE `sigungu` LIKE '" + word + "%'";
		rs = db.getResultSet(sql);
		
		try {
			while(rs.next()) {
				sigungu = rs.getString("sigungu");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!sigungu.equals("%")) {
			i++;
		}
	}
	
	private void eupmyun(String str) {
		String sql = "SELECT DISTINCT `eupmyun` FROM `address` WHERE `eupmyun` LIKE '" + str + "%'";
		rs = db.getResultSet(sql);
		
		try {
			while(rs.next()) {
				eupmyun = rs.getString("eupmyun");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!eupmyun.equals("%")) {
			i++;
		}
	}
	
	private void dong(String str) {
		String sql = "SELECT DISTINCT `dong_hj` FROM `address` WHERE `dong_hj` LIKE '" + str + "%'";
		rs = db.getResultSet(sql);
		try {
			while(rs.next()) {
				dong = rs.getString("dong_hj");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dong.length() > 4 || dong.equals("%")) {
			sql = "SELECT DISTINCT `dong` FROM `address` WHERE `dong` LIKE '" + str + "%'";
			rs = db.getResultSet(sql);
			try {
				while(rs.next()) {
					dong = rs.getString("dong");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!dong.equals("%")) {
			i++;
		}
	}
	
	private void ri(String str) {
		String sql = "SELECT DISTINCT `ri` FROM `address` WHERE `ri` LIKE '" + str + "%'";
		rs = db.getResultSet(sql);
		
		try {
			while(rs.next()) {
				ri = rs.getString("ri");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!ri.equals("%")) {
			i++;
		}
	}
	
	
	private void doro(String str) {
		String sql = "SELECT DISTINCT `doro` FROM `address` WHERE `doro` LIKE '" + str + "%'";
		rs = db.getResultSet(sql);
		System.out.println(str);
		
		try {
			while(rs.next()) {
				doro = rs.getString("doro");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!doro.equals("%")) {
			i++;
		}
	}
	
	private void number(String str) {
		String word[];
		word = str.split("-");
		if(doro.equals("%")) {
			if(word.length == 2) {
				jibun1 = word[0];
				jibun2 = word[1];
			}
			else {
				jibun1 = word[0];
			}
		}
		else {
			if(word.length == 2) {
				buildno1 = word[0];
				buildno2 = word[1];
			}
			else {
				buildno1 = word[0];
			}
		}
	}
}


/*https://qh5944.tistory.com/44*/

public class LbDB_zipcode_Dialog extends JDialog implements WindowListener{
	private LbDB_DAO db;
	private JTextField tf_zipcode, tf_address, tf_detail;
	
	public LbDB_zipcode_Dialog() {}
	public LbDB_zipcode_Dialog(LbDB_DAO db, JTextField tf_zipcode, JTextField tf_address, JTextField tf_detail) {
		this.db = db;
		this.tf_zipcode = tf_zipcode;
		this.tf_address = tf_address;
		this.tf_detail = tf_detail;
		addWindowListener(this);
	}
	
	void initform() {
		
	}
	
	public void length(String str) {
		if(str.length() < 2);
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("우편검색 페이지 종료!!");
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
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
