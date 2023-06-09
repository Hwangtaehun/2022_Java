package split;

import java.sql.ResultSet;
import java.time.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class main {	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LbDB_DAO db = new LbDB_DAO();
		String sql = "SELECT * " + "FROM library, book, material LEFT JOIN lent ON material.mat_no = lent.mat_no " + 
				     "WHERE library.lib_no = material.lib_no AND book.book_no = material.book_no AND " +
				     "len_no = 2";
		ResultSet result = db.getResultSet(sql);
		String state = null;
		
		try {
			while(result.next()) {
				state = result.getString("lent.len_re_st");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(state);
		
		LocalDate Now = LocalDate.now();
		System.out.println(Now);
	}
}
