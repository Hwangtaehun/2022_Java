package MySQL;
import java.sql.*;

import RDBMS.Sj12DbMetaData;

public class Sj12MySqlMetaData {
	public static void main(String[] args) {
		Connection con = null;
		Statement smt = null;
		Sj12MySqlMetaData db = new Sj12MySqlMetaData();
		try {
			con = db.dbConnect();
			smt = con.createStatement();
			db.metaData(smt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			smt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection dbConnect() throws Exception{
		String url = "jdbc:mysql://192.168.1.30:3306/testdb";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "sj002", "sj4321");
		return con;
	}
	
	public void metaData(Statement smt) {
		String sql;
		ResultSet rs;
		ResultSetMetaData meta;
		int colCnt;
		try {
			//sql = "SELECT * FROM Score";
			//sql = "Select COUNT(*), sum(nKor) FROM Score ";
			sql = "Select COUNT(*) as count FROM Score ";
			rs = smt.executeQuery(sql);
			meta = rs.getMetaData();
			colCnt = meta.getColumnCount();
			System.out.println("필드수 : " + colCnt);
			System.out.println("필드명\tDATA형");
			for(int i = 1; i <= colCnt; i++) {
				System.out.println(meta.getColumnClassName(i)+"\t"+meta.getColumnTypeName(i));
			}
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
		}	
	}
}
