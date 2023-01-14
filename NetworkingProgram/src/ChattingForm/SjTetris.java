package ChattingForm;
import java.awt.*;
import javax.swing.*;

public class SjTetris {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TetrisFrame tetrisForm = new TetrisFrame("Sejong Tetris1");
		tetrisForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tetrisForm.setSize(350, 600);
		tetrisForm.setVisible(true);
	}
}

class TetrisFrame extends JFrame{
	JPanel pan1, pan2;
	JButton gameStart, gameStop;
	
	public TetrisFrame() {}
	public TetrisFrame(String str) {
		super(str);
		gameStart = new JButton("Game Start");
		gameStop = new JButton("Game Stop");
		
		pan1 = new JPanel();
		pan1.setLayout(new BorderLayout());
		pan2 = new JPanel();
		pan2.add(gameStart);
		pan2.add(gameStop);
		
		add("North", pan1);
		add("South", pan2);
	}
}

class TetrisPlay implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}