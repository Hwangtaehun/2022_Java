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
		//String url = "jdbc:mysql://localhost:3306/madang";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, "sj002", "sj4321"); 
			//con = DriverManager.getConnection(url, "madang", "madang"); 
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
			
//			smt.execute("use test");
//			smt.execute("CREATE TABLE Manager ( manid INTEGER PRIMARY KEY, title VARCHAR(30))");
//			smt.execute("CREATE TABLE Connection ( conid INTEGER PRIMARY KEY, title VARCHAR(30))");
//			smt.execute("CREATE TABLE Banks ( id INTEGER PRIMARY KEY, manid INTEGER, price INTEGER, date DATE, inform VARCHAR(50), conid INTEGER, balance  INTEGER, FOREIGN KEY (manid) REFERENCES Manager(manid), FOREIGN KEY (conid) REFERENCES Connection(conid))");
//			printData(rs, 1);
			
//			sql = "INSERT INTO Manager VALUES(10100, 'food')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10101, 'eat out')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10102, 'delivery')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10200, 'housing')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10201, 'home lant')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10202, 'electricity')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10203, 'gas')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10204, 'water')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10205, 'maintenance')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10206, 'loan rate')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10300, 'clothes')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10301, 'bags')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10302, 'watch')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10303, 'shoes')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10400, 'necessities')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10401, 'cleaning')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10402, 'washing')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10500, 'communication expense')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10501, 'cell phone')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10502, 'internet')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10503, 'TV')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10504, 'home phone')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10505, 'monthly installment')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10600, 'personal')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10601, 'transportation fee')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10602, 'hobby')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10603, 'travel')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10604, 'cultural life')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10605, 'exercise')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10700, 'tax')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10800, 'insurance')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10801, 'actual insurance')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10802, 'whole life insurance')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10803, 'cancer insurance')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10900, 'raise')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10901, 'guide')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10902, 'raise hospital')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10903, 'school')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(10904, 'private educational institute')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(11000, 'save')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(11100, 'family event')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(11101, 'parent allowance')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(20100, 'month pay')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(20200, 'insurance payback')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(20300, 'bonus')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(20301, 'allowance')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Manager VALUES(20302, 'company bonus')";
//			smt.executeUpdate(sql);
//			
//			sql = "INSERT INTO Connection VALUES(10000, 'online')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Connection VALUES(10100, 'openmarket')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Connection VALUES(10200, 'socialcommerce')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Connection VALUES(10300, 'secondhand')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Connection VALUES(10400, 'online synthesis mall')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Connection VALUES(10500, 'online wholesale')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Connection VALUES(20000, 'offline')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Connection VALUES(20100, 'wholesale')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Connection VALUES(20200, 'retailsales')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Connection VALUES(20300, 'department store')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Connection VALUES(20400, 'supermarket')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Connection VALUES(20500, 'traditional market')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Connection VALUES(30000, 'country')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Connection VALUES(30100, 'national')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Connection VALUES(30200, 'local')";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Connection VALUES(40000, 'other people')";
//			smt.executeUpdate(sql);
//			
//			sql = "INSERT INTO Banks VALUES(1, 20100, 50000, STR_TO_DATE('2023-01-01','%Y-%m-%d'), 'bonus money', 40000, 50000)";
//			smt.executeUpdate(sql);
//			sql = "INSERT INTO Banks VALUES(2, 10602, 1000, STR_TO_DATE('2023-01-02','%Y-%m-%d'), 'rent', 10000, null)";
//			smt.executeUpdate(sql);
			
			printAllData();
			
//			smt.execute("drop table Banks");
//			smt.execute("drop table Incomes");
//			smt.execute("drop table Expenses");
//			
//			rs = smt.executeQuery("show tables");
//			printData(rs, 1);
			
			//printAllData();
			
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
		int id, price, balance;
		String sql, man_title, con_title, inform;
		Date date;
		sql = "Select Banks.id, Manager.title, Banks.price, Banks.date, Connection.title, Banks.inform, Banks.balance "
				+ "From Banks left join Manager on Banks.manid = Manager.manid left join Connection on Banks.conid = Connection.conid";
		rs = smt.executeQuery(sql);
		while(rs.next()) {
			id = rs.getInt("Banks.id");
			price = rs.getInt("Banks.price");
			man_title = rs.getString("Manager.title");
			date = rs.getDate("Banks.date");
			con_title = rs.getString("Connection.title");
			inform = rs.getString("Banks.inform");
			balance = rs.getInt("Banks.balance");
			System.out.println("번호 " + id + "\t" + "관리구분 " + man_title);
			System.out.println("금액 " + price + "\t" + "날짜 " + date);
			System.out.println("거래처 " + con_title + "\t" + "내용 " + inform);
			System.out.println("잔고 " + balance);
		}
	}
}