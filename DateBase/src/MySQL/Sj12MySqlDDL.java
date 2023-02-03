package MySQL;
import java.sql.*;

public class Sj12MySqlDDL {
	public static void main(String[] args) {
		Sj12DdlTest db = new Sj12DdlTest();
		db.ddlTest();
	}
}

class Sj12DdlTest{
	Connection con;
	Statement smt;
	
	public Sj12DdlTest() {
		String url = "jdbc:mysql://192.168.1.30:3306/testdb";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, "sj002", "sj4321"); 
			smt = con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ddlTest() {
		ResultSet rs;
		
		try {
			rs = smt.executeQuery("show databases");
//			if(smt.execute("show databases")) {
//				rs = smt.getResultSet();
//			}
			
			printData(rs, 1);
			
			rs = smt.executeQuery("use test");
			rs = smt.executeQuery("show tables");
			printData(rs, 1);
			
			
//			smt.execute("create table allowance (strCode char(4), strName char(10), nKor int, nEng int, nMat int)");
//			rs = smt.executeQuery("show tables");
//			printData(rs, 1);
//			
//			rs = smt.executeQuery("describe Score");
//			printData(rs, 2);
			
//			smt.execute("create table test2 (aa char(4), bb char(10), cc int)");
//			rs = smt.executeQuery("show tables");
//			printData(rs, 1);
			
//			smt.execute("alter table test2 add dd float");
//			rs = smt.executeQuery("describe test2");
//			printData(rs, 2);
			
//			smt.execute("drop table test2");
//			rs = smt.executeQuery("show tables");
//			printData(rs, 1);
			
//			smt.execute("drop table Score");
//			rs = smt.executeQuery("show tables");
//			printData(rs, 1);
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printData(ResultSet rs, int flag) throws SQLException {
		while(rs.next()) {
			if(flag == 1)
				System.out.println(rs.getNString(1));
			else
				System.out.println(rs.getNString(1)+" "+rs.getNString(2));
		}
		System.out.println();
	}
}