package RDBMS;
import java.sql.*;

public class Sj12AccessDbConnect3 {
	public static void main(String[] args) {
		Connection con = null;
		Statement smt = null;
		Sj12AccessDbConnect3 db = new Sj12AccessDbConnect3();
		try {
			con = db.dbConnect();
			smt = con.createStatement();
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			smt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection dbConnect() throws Exception{
		//Connection con = DriverManager.getConnection("jdbc:ucanaccess://SjTestDB.accdb;memory = false");
		Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\수강생\\황태훈\\Database\\SjTestDB.accdb;memory=false");
		System.out.println("Access DB에 연결 성공!!");
		return con;
	}
}
