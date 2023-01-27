package RDBMS;
import java.sql.*;

public class Sj12DbDml {
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Sj12DmlTest db = new Sj12DmlTest();
		db.printAllData();
		
		//db.insertData();
		//db.updateData();
		//db.deleteData();
		//db.selectData();
		//db.totAvg();
		//db.rank();
		
		db.Close();
	}
}

class Sj12DmlTest{
	Connection con;
	Statement smt;
	String sql;
	
	public Sj12DmlTest() {
		try {
			Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\수강생\\황태훈\\Database\\SjTestDB.accdb;memory=false");
			smt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertData() throws SQLException{
		
	}
	
	public void updateData() throws SQLException{
		
	}
	
	public void deleteData() throws SQLException{
		
	}
	
	public void selectData() throws SQLException{
		
	}
	
	public void totAvg() throws SQLException{
		
	}
	
	public void rank() throws SQLException{
		
	}
	
	public void printAllData() throws SQLException{
		
	}
	
	public void Close() {
		
	}
}