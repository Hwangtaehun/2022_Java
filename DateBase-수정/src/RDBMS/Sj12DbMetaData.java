package RDBMS;
import java.sql.*;

public class Sj12DbMetaData {
	public static void main(String[] args) {
		Connection con = null;
		Statement smt = null;
		Sj12DbMetaData db = new Sj12DbMetaData();
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
		Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\������\\Ȳ����\\Database\\SjTestDB.accdb;memory=false");
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
			System.out.println("�ʵ�� : " + colCnt);
			System.out.println("�ʵ��\tDATA��");
			for(int i = 1; i <= colCnt; i++) {
				System.out.println(meta.getColumnClassName(i)+"\t"+meta.getColumnTypeName(i));
			}
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
		}	
	}
}
