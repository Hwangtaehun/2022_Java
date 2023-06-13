package libraryDB;

class Client{
	private int pk;
	private int state;
	
	public Client() {
		this.pk = 0;
		this.state = 0;
	}
	
	public void insertnum(int pk, int state) {
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

class foreignkey{
	private int add_no, kind_no, book_no, mem_no;
	public String kind_num, book_name;
	
	public foreignkey() {
		add_no = 0;
		kind_no = 0;
		book_no = 0;
		mem_no = 0;
	};
	
	public void insert_kind_no(int num) {
		kind_no = num;
	}
	
	public void insert_add_no(int num) {
		add_no = num;
	}
	
	public void insert_book_no(int num) {
		book_no = num;
	}
	
	public void insert_mem_no(int num) {
		mem_no = num;
	}
	
	public int call_add_no() {
		return add_no;
	}
	
	public int call_kind_no() {
		return kind_no;
	}
	
	public int call_book_no() {
		return book_no;
	}
	
	public int call_mem_no() {
		return mem_no;	
	}
}

public class LbDB_main {
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
		Client cl = new Client();
		LbDB_Login_Frame log = new LbDB_Login_Frame(db, cl);
		log.setVisible(true);
	}
}
