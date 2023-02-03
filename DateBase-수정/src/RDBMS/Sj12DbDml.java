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
		db.rank();
		
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
			//Connection con = DriverManager.getConnection("jdbc:ucanaccess://SjTestDB.accdb;memory=false");
			smt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertData() throws SQLException{
		//sql = "INSERT INTO Score (strCode, strName) VALUES('1006', '세종')";
		sql = "INSERT INTO Score VALUES('1008','세종', 99, 88, 77, 0, 0, 0)";
		smt.executeUpdate(sql);
		printAllData();
	}
	
	public void updateData() throws SQLException{
		//sql = "UPDATE Score Set nKor = 100";
		sql = "UPDATE Score SET nkor = 100 WHERE strName = '세종'";
		smt.executeUpdate(sql);
		printAllData();
	}
	
	public void deleteData() throws SQLException{
		//sql = "DELETE FROM Score WHERE strName = '세종'";
		//sql = "DELETE FROM Score WHERE strName LIKE '세%'";
		sql = "DELETE FROM Score WHERE strName LIKE '세*'";
		smt.executeUpdate(sql);
		printAllData();
	}
	
	public void selectData() throws SQLException{
		ResultSet rs;
		sql = "Select COUNT(*) as count, sum(nkor) as total FROM Score ";
		rs = smt.executeQuery(sql);
		rs.next();
		ResultSetMetaData meta;
		meta = rs.getMetaData();
		System.out.println(meta.getColumnClassName(1) + "\t" + meta.getColumnClassName(2));
		int cnt = rs.getInt(meta.getColumnName(1));
		System.out.println("Record 수  : " + cnt);
		System.out.println("국어 점수 함계 : " + rs.getDouble(meta.getColumnName(2)));
	}
	
	public void totAvg() throws SQLException{
		ResultSet rs;
		int tot;
		double avg;
		sql = "Select * FROM Score  ";
		rs = smt.executeQuery(sql);
		while(rs.next()) {
			tot = rs.getInt("nKor") + rs.getInt("nMat") + rs.getInt("nEng");
			avg = (double)tot/3.0;
			sql = "UPDATE Score Set nTotal = " + tot + ", dAverage =" + avg + " where strCode = '" + rs.getString("strCode") + " ' ";
			smt.executeUpdate(sql);
		}
		printAllData();
	}
	
	public void rank() throws SQLException{
		ResultSet rs;
		int tot = 0, rank1 = 0, rank2 = 0;
		sql = "Select * FROM Score order by nTotal desc ";
		rs = smt.executeQuery(sql);
		while(rs.next()) {
			rank1++;
			if(tot != rs.getInt("nTotal")) {
				tot = rs.getInt("nTotal");
				rank2 = rank1;
			}
			sql = "UPDATE Score Set nRank = " + rank2 + " where strCode ='" + rs.getString("strCode") + "'";
			smt.executeUpdate(sql);
		}
		printAllData();
	}
	
	public void printAllData() throws SQLException{
		ResultSet rs;
		String hakBun, hakName;
		int kor, eng, mat;
		sql = "Select * FROM Score ";
		rs = smt.executeQuery(sql);
		while(rs.next()) {
			hakBun = (rs.getString("strCode")).trim();
			hakName = rs.getString("strName").trim();
			kor = rs.getInt("nKor");
			mat = rs.getInt("nMat");
			eng = rs.getInt("nEng");
			System.out.print(hakBun + "\t" + hakName + "\t" + kor + "\t" + mat + "\t" + eng + " ");
			System.out.println(rs.getInt("nTotal") + "\t" + rs.getDouble("dAverage") + "\t" + rs.getInt("nRank"));
		}
	}
	
	public void Close() {
		try {
			smt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}