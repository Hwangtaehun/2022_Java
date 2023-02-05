package RDBMS;
import java.sql.*;

public class Sj12AccessDbConnect {
	static Connection con;
	static Statement smt;
	
	public static void main(String[] args) {
		try {
			con = DriverManager.getConnection("jdbc:ucanaccess://..\\DateBase\\Allowance.accdb;memory=false");
			System.out.println("Access DB�� ���� ����!!");
			
			smt = con.createStatement();
			System.out.println("Statement ��ü ���� ���� !!");
			
			smt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
