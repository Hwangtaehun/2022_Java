package RDBMS;
import java.sql.*;

public class Sj12DbCursor {
	public static void main(String[] args) {
		Connection con = null;
		Sj12DbCursor db = new Sj12DbCursor();
		try {
			con = db.dbConnect();
			db.cursorTest(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection dbConnect() throws Exception {
		Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\수강생\\황태훈\\Database\\SjTestDB.accdb;memory=false");
		return con;
	}
	
	Statement smt;
	ResultSet rs;
	
	public void printData() throws SQLException{
		String hakBun, hakName;
		int kor, eng, mat;
		
		hakBun = rs.getString("strCode");
		hakName = rs.getString("strName");
		kor = rs.getInt("nKor");
		mat = rs.getInt("nMat");
		eng = rs.getInt("nEng");
		
		System.out.println(hakBun + " " + hakName + " " + kor + " " + mat + " " + eng +"");
	}
	
	public void cursorTest(Connection con) {
		try {
			//smt = con.createStatement();
			smt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "Select * FROM Score order by strCode";
			rs = smt.executeQuery(sql);
			
			while(rs.next()) {
				printData();
			}
			while(rs.previous()) {
				printData();
			}
			rs.last();
			//rs.first();
			//rs.relative(2);
			rs.relative(-2);
			//rs.absolute(2);
			//rs.absolute(-2);
			printData();
			smt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQLException " + e.getMessage());
		}
		
	}
}
