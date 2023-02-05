package Allowance;
import java.sql.*;

public class DBA_DAO {
	private Connection con;
	private Statement smt;
	private ResultSet rs;
	
	public DBA_DAO() {
		try {
			con = DriverManager.getConnection("jdbc:ucanaccess://..\\DateBase\\Database1.accdb;memory=false");
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
