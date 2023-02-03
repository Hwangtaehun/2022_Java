package MySQL;
import java.sql.*;

public class Sj12MySqlConnect {
	public static void main(String[] args) {
		String url = "jdbc:mysql://192.168.1.30:3306/test";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "sj002", "sj4321"); 
			
			if(con != null)
				System.out.println("연결 성공");
			else
				System.out.println("연결 실패");
			
			Statement smt = con.createStatement();
			System.out.println("Statement 객체 생성 성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
