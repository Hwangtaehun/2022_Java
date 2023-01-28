package Grade;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SjDB2_Frame extends JFrame implements ActionListener{
	SjDB2_DAO db;
	JButton firstBt, lastBt, previousBt, nextBt, newFileBt, insertBt, updateBt, deleteBt, sortBt, closeBt;
	JTextField number, name, kor, mat, eng;
	JPanel pan1, pan2, pan3;
	SjDB2_TableMode table;
	
	
	public SjDB2_Frame() {}
	public SjDB2_Frame(SjDB2_DAO db) {
		super();
		this.db = db;
		initForm();
	}
	
	void initForm() {
		pan1 = new JPanel();
		pan2 = new JPanel();
		pan3 = new JPanel();
		table = new SjDB2_TableMode();
		
		number = new JTextField();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
