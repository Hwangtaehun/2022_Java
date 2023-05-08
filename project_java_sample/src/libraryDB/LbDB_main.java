package libraryDB;

public class LbDB_main {
	public class Client{
		private int pk;
		private int state;
		
		public Client(int pk, int state) {
			this.pk = pk;
			this.state = state;
		}
		
		public int primarykey() {
			return pk;
		}
		
		public int state() {
			return state;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LbDB_DAO db = new LbDB_DAO();
		db.printMetaData("address");
		db.printMetaData("book");
		db.printMetaData("delivery");
		db.printMetaData("kind");
		db.printMetaData("lent");
		db.printMetaData("library");
		db.printMetaData("material");
		db.printMetaData("member");
		db.printMetaData("overdue");
		db.printMetaData("place");
		db.printMetaData("reservation");
	}
}
