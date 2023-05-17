package libraryDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

class Addresstool{
	private String sidobasic[] = {"서울특별시", "부산광역시", "대전광역시", "대구광역시", "인천광역시", "광주광역시", "울산광역시",
							 "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도",
							 "세종특별자치시", "강원도", "경기도", "서울시", "부산시", "대전시", "대구시", "인천시", "광주시", "울산시",
							 "충북", "충남", "전북", "전남", "경북", "경남", "제주도", "세종시"};
	private String sigungusp[] = {"고양시", "성남시", "수원시", "안산시", "안양시", "용인시", "창원시", "포항시", "전주시", "천안시", "청주시",
								  "고양",   "성남",  "수원",  "안산",   "안양",  "용인",  "창원",   "포항",  "전주",   "천안",  "청주"};
	private String main[];
	private LbDB_DAO db;
	private int i;
	public String sido, sigungu, eupmyun, doro, buildno1, buildno2, dong_hj, ri, jibun1, jibun2;
	
	public Addresstool() {}
	public Addresstool(String str, LbDB_DAO db) {
		this.db = db;
		String word = str.trim();
		main = word.split(" ");
		sort();
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
		if(sido.isEmpty()) {
			sigungu(main[i]);
		}
		else {
			i++;
			sigungu(main[i]);
		}
		
		
	}
	
	private void sigungu(String str) {
		ResultSet rs;
		String word = null;
		for(int j = 0; j < sigungusp.length; j++) {
			if(str.equals(sigungusp[j])) {
				if(j > 10) {
					int num = j % 11;
					word += sigungusp[num];
				}
				else {
					word += str;
				}
				i++;
				word += main[i];
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
		if(!sigungu.isEmpty()) {
			i++;
		}
	}
	
	private void eupmyun(String str) {
		ResultSet rs;
		String sql = "SELECT DISTINCT `eupmyun` FROM `address` WHERE `eupmyun` LIKE '" + str + "%'";
		rs = db.getResultSet(sql);
		
		try {
			while(rs.next()) {
				eupmyun = rs.getString("sigungu");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!eupmyun.isEmpty()) {
			i++;
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
