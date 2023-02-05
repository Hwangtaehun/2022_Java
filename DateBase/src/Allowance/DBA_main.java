package Allowance;

public class DBA_main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBA_Frame frame;
		DBA_DAO db = new DBA_DAO();
		db.printMetaData("Banks");
		db.printMetaData("Incomes");
		db.printMetaData("Expenses");
		frame = new DBA_Frame(db);
		frame.setVisible(true);
	}
}
