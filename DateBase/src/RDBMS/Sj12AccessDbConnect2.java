package RDBMS;
import java.sql.*;

public class Sj12AccessDbConnect2 {
	Connection con;
	Statement smt;
	public static void main(String[] args) {
		Sj12AccessDbConnect2 db = new Sj12AccessDbConnect2();
		db.dbConnect();
	}
	public void dbConnect() {
		try {
			//con = DriverManager.getConnection("jdbc:ucanaccess://SjTestDB.accdb:memory=false");
			con = DriverManager.getConnection("jdbc:ucanaccess://C:\\수강생\\황태훈\\Database\\SjTestDB.accdb;memory=false");
			System.out.println("Access DB에 연결 성공!!");
			smt = con.createStatement();
			System.out.println("Statement 객체 생성 성공!!");
			smt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
