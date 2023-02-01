package Grade;
import java.sql.*;

public class SjDB2_DAO {
	private Connection con;
	private Statement smt;
	private ResultSet rs;
	
	public SjDB2_DAO() {
		try {
			con = DriverManager.getConnection("jdbc:ucanaccess://SjTestDb.accdb:memory = false");
			smt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = smt.executeQuery("Select * FROM Score order by strCode");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void printMetaData(String tableName) {
		ResultSet rs = getResultSet("select * from " + tableName);
		ResultSetMetaData meta;
		try {
			meta = rs.getMetaData();
			int colCnt = meta.getColumnCount();
			System.out.println(meta.getTableName(1) + "Table의 field 수 : " + colCnt);
			System.out.println("Field명 \t  DATA형");
			for(int i = 1; i <= colCnt; i++) {
				System.out.printf("%-10s %s\n", meta.getColumnName(i), meta.getColumnTypeName(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getResultSet(String sql) {
		try {
			return smt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void Excute(String sql) {
		try {
			smt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void totAvg() throws SQLException{
		ResultSet rs;
		int tot;
		double avg;
		String sql = "Select * FROM Score  ";
		rs = smt.executeQuery(sql);
		while(rs.next()) {
			tot = rs.getInt("nKor") + rs.getInt("nMat") + rs.getInt("nEng");
			avg = (double)tot/3.0;
			sql = "UPDATE Score Set nTotal = " + tot + ", dAverage =" + avg + " where strCode = '" + rs.getString("strCode") + " ' ";
			smt.executeUpdate(sql);
		}
	}
	
	public void rank() throws SQLException{
		ResultSet rs;
		int tot = 0, rank1 = 0, rank2 = 0;
		String sql = "Select * FROM Score order by nTotal desc ";
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
	}
	
//	public void first() {
//		try {
//			rs.first();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void last() {
//		try {
//			rs.last();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void previous() {
//		try {
//			rs.relative(-1);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void next() {
//		try {
//			rs.relative(1);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
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
