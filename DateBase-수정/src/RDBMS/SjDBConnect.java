package RDBMS;
import java.sql.*;

public class SjDBConnect {
	public static void main(String[] args) {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}catch(java.lang.ClassNotFoundException e) {
			System.err.print("Jbc-odbc Driver Load Fail : ");
			System.err.println(e.getMessage());
		}
		try {
			Connection con = DriverManager.getConnection("jdbc:odbc:odbcsungjuk");
			System.out.println("DB에 연결 성공!!");
			Statement smt = con.createStatement();
			System.out.println("Statement 객체 생성 성공 !!");
			smt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQLException " + e.getMessage());
		}
	}
}
