package test;
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
		print();
		sido = "";
		sigungu = "";
		eupmyun = "";
		doro = "";
		dong = "";
		dong_at = "dong_hj";
		ri = "";
		buildno1 = "";
		buildno2 = "";
		jibun1 = "";
		jibun2 = "";
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
		
		if(!sido.isEmpty()) {
			i++;
		}
		
		sigungu(main[i]);
		eupmyun(main[i]);
		dong(main[i]);
		ri(main[i]);
		
		if(dong.isEmpty() && ri.isEmpty()) {
			doro(main[i]);
		}
		
		number(main[i]);
		
		/*
		sqlresearch(sigungu, main[i]);
		sqlresearch(eupmyun, main[i]);
		
		if(eupmyun.isEmpty()) {
			sqlresearch(dong, main[i]);
			if(!dong.isEmpty()) {
				
			}
		}
		else {
			sqlresearch(ri, main[i]);
			if(!ri.isEmpty()) {
				
			}
		}*/
	}
	
	/*
	private void sqlresearch(String name, String str) {
		ResultSet rs;
		String word = str;
		if(name.equals("sigungu")) {
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
		}
		
		if(name.equals("dong")) {
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
			if(dong.length() > 4 || dong.isEmpty()) {
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
		}
		else {
			String sql = "SELECT DISTINCT `" + name + "` FROM `address` WHERE `" + name + "` LIKE '" + word + "%'";
			rs = db.getResultSet(sql);
			
			try {
				while(rs.next()) {
					if(name.equals("sigungu")) {
						sigungu = rs.getString("sigungu");
					}
					else if(name.equals("eupmyun")) {
						eupmyun = rs.getString("eupmyun");
					}
					else if(name.equals("ri")) {
						ri = rs.getString("ri");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(!sigungu.isEmpty()) {
			i++;
		}
		else if(!eupmyun.isEmpty()) {
			i++;
		}
		else if(!dong.isEmpty()) {
			i++;
		}
		else if(!ri.isEmpty()) {
			i++;
		}
	}
	*/
	
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
		
		if(!sigungu.isEmpty()) {
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
		if(!eupmyun.isEmpty()) {
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
		
		if(dong.length() > 4 || dong.isEmpty()) {
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
			if(!dong.isEmpty()) {
				dong_at = "dong";
			}
		}
		if(!dong.isEmpty()) {
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
		if(!ri.isEmpty()) {
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
		if(!doro.isEmpty()) {
			i++;
		}
	}
	
	private void number(String str) {
		String word[];
		word = str.split("-");
		if(doro.isEmpty()) {
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

public class Print {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DB_DAO db = new DB_DAO();
		String address = "경북 군위 마시리 1215";
		Addresstool ad = new Addresstool(address, db);
		System.out.println("\n" + "시도: " + ad.sido + ", 시군구: " + ad.sigungu +", 읍면: " + ad.eupmyun + 
						   ", 동: " + ad.dong + ", 리: " + ad.ri + ", 도로명: " + ad.doro + 
						   ", 길본번: " + ad.buildno1 + ", 길부번: " + ad.buildno2 + ", 지번본번: " + ad.jibun1 );
	}
}
