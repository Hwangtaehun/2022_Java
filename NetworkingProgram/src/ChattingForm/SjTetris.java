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
	Point [][] pattern; 
	Point [][] nextpattern;
	
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
		blackPattern();
	}
	
	private void blackPattern() {
		pattern = new Point[7][16];
		pattern[0][0] = new Point(0, 0);
		pattern[0][1] = new Point(0, -1);
		pattern[0][2] = new Point(-1, 0);
		pattern[0][3] = new Point(-1, -1);
		pattern[0][4] = new Point(0, 0);
		pattern[0][5] = new Point(0, -1);
		pattern[0][6] = new Point(-1, 0);
		pattern[0][7] = new Point(-1, -1);
		pattern[0][8] = new Point(0, 0);
		pattern[0][9] = new Point(0, -1);
		pattern[0][10] = new Point(-1, 0);
		pattern[0][11] = new Point(-1, -1);
		pattern[0][12] = new Point(0, 0);
		pattern[0][13] = new Point(0, -1);
		pattern[0][14] = new Point(-1, 0);
		pattern[0][15] = new Point(-1, -1);
		pattern[1][0] = new Point(0, 0);
		pattern[1][1] = new Point(1, 0);
		pattern[1][2] = new Point(-1, 0);
		pattern[1][3] = new Point(-2, 0);
		pattern[1][4] = new Point(0, 0);
		pattern[1][5] = new Point(0, 1);
		pattern[1][6] = new Point(0, 2);
		pattern[1][7] = new Point(0, -1);
		pattern[1][8] = new Point(0, 0);
		pattern[1][9] = new Point(1, 0);
		pattern[1][10] = new Point(-1, 0);
		pattern[1][11] = new Point(-2, 0);
		pattern[1][12] = new Point(0, 0);
		pattern[1][13] = new Point(0, 1);
		pattern[1][14] = new Point(0, 2);
		pattern[1][15] = new Point(0, -1);
		pattern[2][0] = new Point(0, 0);
		pattern[2][1] = new Point(-1, 0);
		pattern[2][2] = new Point(0, -1);
		pattern[2][3] = new Point(1, -1);
		pattern[2][4] = new Point(0, 0);
		pattern[2][5] = new Point(0, 1);
		pattern[2][6] = new Point(-1, 0);
		pattern[2][7] = new Point(-1, -1);
		pattern[2][8] = new Point(0, 0);
		pattern[2][9] = new Point(-1, 0);
		pattern[2][10] = new Point(0, -1);
		pattern[2][11] = new Point(1, -1);
		pattern[2][12] = new Point(0, 0);
		pattern[2][13] = new Point(0, 1);
		pattern[2][14] = new Point(-1, 0);
		pattern[2][15] = new Point(-1, -1);
		pattern[3][0] = new Point(0, 0);
		pattern[3][1] = new Point(-1, -1);
		pattern[3][2] = new Point(0, -1);
		pattern[3][3] = new Point(1, 0);
		pattern[3][4] = new Point(0, 0);
		pattern[3][5] = new Point(-1, 0);
		pattern[3][6] = new Point(-1, 1);
		pattern[3][7] = new Point(0, -1);
		pattern[3][8] = new Point(0, 0);
		pattern[3][9] = new Point(-1, -1);
		pattern[3][10] = new Point(0, -1);
		pattern[3][11] = new Point(1, 0);
		pattern[3][12] = new Point(0, 0);
		pattern[3][13] = new Point(-1, 0);
		pattern[3][14] = new Point(-1, 1);
		pattern[3][15] = new Point(0, -1);
		pattern[4][0] = new Point(-1, 0);
		pattern[4][1] = new Point(-1, 1);
		pattern[4][2] = new Point(0, 1);
		pattern[4][3] = new Point(1, 1);
		pattern[4][4] = new Point(0, 1);
		pattern[4][5] = new Point(1, 1);
		pattern[4][6] = new Point(1, 0);
		pattern[4][7] = new Point(1, -1);
		pattern[4][8] = new Point(-1, -1);
		pattern[4][9] = new Point(0, -1);
		pattern[4][10] = new Point(1, -1);
		pattern[4][11] = new Point(1, 0);
		pattern[4][12] = new Point(0, -1);
		pattern[4][13] = new Point(-1, -1);
		pattern[4][14] = new Point(-1, 0);
		pattern[4][15] = new Point(-1, 1);
		pattern[5][0] = new Point(-1, 1);
		pattern[5][1] = new Point(0, 1);
		pattern[5][2] = new Point(1, 1);
		pattern[5][3] = new Point(1 ,0);
		pattern[5][4] = new Point(0, -1);
		pattern[5][5] = new Point(1, -1);
		pattern[5][6] = new Point(1, 0);
		pattern[5][7] = new Point(1, 1);
		pattern[5][8] = new Point(-1, 0);
		pattern[5][9] = new Point(-1, -1);
		pattern[5][10] = new Point(0, -1);
		pattern[5][11] = new Point(1, -1);
		pattern[5][12] = new Point(-1, 1);
		pattern[5][13] = new Point(-1, 0);
		pattern[5][14] = new Point(-1, -1);
		pattern[5][15] = new Point(0, 1);
		pattern[6][0] = new Point(0, 0);
		pattern[6][1] = new Point(-1, 0);
		pattern[6][2] = new Point(1, 0);
		pattern[6][3] = new Point(0, 1);
		pattern[6][4] = new Point(0, 0);
		pattern[6][5] = new Point(0, -1);
		pattern[6][6] = new Point(0, 1);
		pattern[6][7] = new Point(1, 0);
		pattern[6][8] = new Point(0, 0);
		pattern[6][9] = new Point(-1, 0);
		pattern[6][10] = new Point(1, 0);
		pattern[6][11] = new Point(0, -1);
		pattern[6][12] = new Point(0, 0);
		pattern[6][13] = new Point(-1, 0);
		pattern[6][14] = new Point(0, -1);
		pattern[6][15] = new Point(0, 1);
		
		nextpattern = new Point[7][4];
		nextpattern[0][0] = new Point(-1, 0);
		nextpattern[0][1] = new Point(0, 0);
		nextpattern[0][2] = new Point(-1, 1);
		nextpattern[0][3] = new Point(0, 1);
		nextpattern[1][0] = new Point(-1, 1);
		nextpattern[1][1] = new Point(0, 1);
		nextpattern[1][2] = new Point(1, 1);
		nextpattern[1][3] = new Point(-2, 1);
		nextpattern[2][0] = new Point(0, 1);
		nextpattern[2][1] = new Point(-1, 1);
		nextpattern[2][2] = new Point(0, 0);
		nextpattern[2][3] = new Point(1, 0);
		nextpattern[3][0] = new Point(0, 1);
		nextpattern[3][1] = new Point(-1, 0);
		nextpattern[3][2] = new Point(0, 0);
		nextpattern[3][3] = new Point(1, 1);
		nextpattern[4][0] = new Point(-1, 1);
		nextpattern[4][1] = new Point(-1, 0);
		nextpattern[4][2] = new Point(0, 1);
		nextpattern[4][3] = new Point(1, 1);
		nextpattern[5][0] = new Point(1, 1);
		nextpattern[5][1] = new Point(0, 1);
		nextpattern[5][2] = new Point(-1, 1);
		nextpattern[5][3] = new Point(1, 0);
		nextpattern[6][0] = new Point(0, 1);
		nextpattern[6][1] = new Point(-1, 1);
		nextpattern[6][2] = new Point(1, 1);
		nextpattern[6][3] = new Point(0, 0);
	}
}

class TetrisPlay implements Runnable{
	boolean play = true;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(play) {
			
		}
	}
	
}