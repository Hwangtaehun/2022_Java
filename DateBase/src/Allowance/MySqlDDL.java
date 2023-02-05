package Allowance;
import java.sql.*;

public class MySqlDDL {
	public static void main(String[] args) {
		DdlTest db = new DdlTest();
		db.ddlTest();
	}
}

class DdlTest{
	Connection con;
	Statement smt;
	
	public DdlTest() {
		String url = "jdbc:mysql://192.168.1.30:3306/testdb";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, "sj002", "sj4321"); 
			smt = con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ddlTest() {
		ResultSet rs;
		String sql;
		
		try {
			rs = smt.executeQuery("show databases");
//			if(smt.execute("show databases")) {
//				rs = smt.getResultSet();
//			}
			
			printData(rs, 1);
			
			rs = smt.executeQuery("use test");
			rs = smt.executeQuery("show tables");
			printData(rs, 1);
			
			
			smt.execute("create table Incomes (incomeid INTEGER PRIMARY KEY, deposit INTEGER DEFAULT 500000 CHECK(deposit > 0), incomedata DATE, in_inform VARCHAR(50)");
			smt.execute("create table Expenses ( expenseid INTEGER PRIMARY KEY, spend INTEGER DEFAULT 89100 CHECK(spend < 0), expensedata DATE, ex_inform VARCHAR(50)");
			smt.execute("create table Banks ( bankid INTEGER PRIMARY KEY, incomeid INTEGER, expenseid INTEGER, FOREIGN KEY (incomeid) REFERENCES Incomes(incomeid), FOREIGN KEY (expenseid) REFERENCES Expenses(expenseid)");
			rs = smt.executeQuery("show tables");
			printData(rs, 1);
			
			sql = "INSERT INTO Incomes VALUES(1, 50000, STR_TO_DATE('2023-01-01','%Y-%m-%d'), 'bonus money')";
			smt.executeUpdate(sql);
			
			sql = "INSERT INTO Expenses VALUES(1, 1000, STR_TO_DATE('2023-01-02','%Y-%m-%d'), 'rent comicbook')";
			smt.executeUpdate(sql);
			
			sql = "INSERT INTO Banks VALUES(1, 1, null)";
			smt.executeUpdate(sql);
			
			sql = "INSERT INTO Banks VALUES(2, null, 1)";
			smt.executeUpdate(sql);
			
			printAllData();
			
//			smt.execute("drop table Incomes");
//			smt.execute("drop table Expenses");
//			smt.execute("drop table Banks");
//			rs = smt.executeQuery("show tables");
//			printData(rs, 1);
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printData(ResultSet rs, int flag) throws SQLException {
		while(rs.next()) {
			if(flag == 1)
				System.out.println(rs.getNString(1));
			else
				System.out.println(rs.getNString(1)+" "+rs.getNString(2));
		}
		System.out.println();
	}
	
	public void printAllData() throws SQLException{
		ResultSet rs;
		int deposit, spend;
		String sql, in_inform, ex_inform;
		Date in_date, ex_date;
		sql = "Select Incomes.deposit, Expenses.spend, Incomes.incomedate, Expenses.expensedate, Incomes.in_inform, Expenses.ex_inform "
				+ "From Banks left join Expenses on Banks.expenseid = Expenses.expenseid left join Incomes on Banks.incomeid = Incomes.incomeid";
		rs = smt.executeQuery(sql);
		while(rs.next()) {
			deposit = rs.getInt("Incomes.deposit");
			spend = rs.getInt("Expenses.spend");
			in_date = rs.getDate("Incomes.incomedate");
			ex_date = rs.getDate("Expenses.expensedate");
			in_inform = rs.getString("Incomes.in_inform").trim();
			ex_inform = rs.getString("Expenses.ex_inform").trim();
			System.out.println("수입" + deposit + "\t" + "지출" + spend);
			System.out.println("수입날짜" + in_date + "\t" + "지출날짜" + ex_date);
			System.out.println("수입내역" + in_inform + "\t" + "지출내역" + ex_inform);
		}
	}
}