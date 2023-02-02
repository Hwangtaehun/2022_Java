package Grade;

public class SjDB2_main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SjDB2_Frame frame;
		SjDB2_DAO db = new SjDB2_DAO();
		db.printMetaData("Score");
		db.printMetaData("Student");
		frame = new SjDB2_Frame(db);
		frame.setVisible(true);
	}
}
