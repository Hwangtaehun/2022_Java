package split;

import java.sql.ResultSet;
import java.sql.SQLException;

public class main {	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LbDB_DAO db = new LbDB_DAO();
		String sql = "SELECT * FROM `material`";
		ResultSet result = db.getResultSet(sql);
		String mat_overlap = null;
		String str_array[];
		
		try {
			while(result.next()) {
				mat_overlap = result.getString("mat_overlap");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("mat_overlap: " + mat_overlap);
		str_array = mat_overlap.split("");
		
		System.out.println("str_array의 크기: " + str_array.length);
		for(int i = 0; i < str_array.length; i++) {
			System.out.println("str_array[" + i + "]: " + str_array[i]);
		}
	}
}
