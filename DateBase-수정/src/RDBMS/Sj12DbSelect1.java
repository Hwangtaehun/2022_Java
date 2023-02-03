package RDBMS;
import java.sql.*;

public class Sj12DbSelect1 {
	public static void main(String[] args) {
		Connection con = null;
		Statement smt = null;
		Sj12DbSelect1 db = new Sj12DbSelect1();
		try {
			con = db.dbConnect();
			smt = con.createStatement();
			db.selectData(smt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	public Connection dbConnect() throws Exception {
		//Connection con = DriverManager.getConnection("jdbc:ucanaccess://SjTestDB.accdb;memory=false");
		Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\수강생\\황태훈\\Database\\SjTestDB.accdb;memory=false");
		return con;
	}
	
	public void selectData(Statement smt) {
		ResultSet rs;
		String sql;
		String hakBun;
		String hakName;
		int kor, eng, mat;
		
		
		try {
			//sql = "Select * FROM Score";
			//sql = "Select * FROM Score where strName = '신세종'";
			//sql = "Select * FROM Score where strName like '*신*'";
			//sql = "Select * FROM Score where nkor >= 80";
			//sql = "Select * FROM Score where nkor >= 80 and nMat = 100";
			//sql = "Select * FROM Score order by strName asc";
			sql = "Select * FROM Score order by strName desc";
			rs = smt.executeQuery(sql);
			//rs = executeQuery(sql);
			//rs = excuteUpdate(sql);
			while(rs.next()) {
				hakBun = rs.getString("strCode");
				hakName = rs.getString("strName");
				kor = rs.getInt("nKor");
				mat = rs.getInt("nMat");
				eng = rs.getInt("nEng");
				System.out.println(hakBun + " " + hakName + " " + kor + " " + mat + " " + eng + " ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQLException " +  e.getMessage());
		}
		
		
	}
}
