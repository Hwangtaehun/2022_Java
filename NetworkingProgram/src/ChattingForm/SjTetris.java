package ChattingForm;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class SjTetris {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TetrisFrame tetrisForm = new TetrisFrame("Sejong Tetris1");
		tetrisForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//tetrisForm.setSize(350, 600);
		tetrisForm.setSize(514, 729);
		tetrisForm.setVisible(true);
	}
}

class TetrisFrame extends JFrame{
	JPanel pan1, pan2;
	JButton gameStart, gameStop;
	TetrisPlay play;
	Thread PlayCT;
	
	final int COL_CNT = 10;
	final int ROW_CNT = 20;
	final int START_X = 10;
	final int START_Y = 30;
	final int BLOCK_SIZE = 32;
	int[][] m_Table;
	Rectangle m_nextRect;
	Rectangle m_mainRect;
	boolean m_bStart;
	Random rand;
	
	public TetrisFrame() {}
	public TetrisFrame(String str) {
		super(str);
		gameStart = new JButton("Game Start");
		gameStart.addActionListener(new StartHandler());
		gameStop = new JButton("Game Stop");
		gameStop.addActionListener(new StopHandler());
		
		pan1 = new JPanel();
		pan2 = new JPanel();
		
		m_bStart = false;
		m_mainRect = new Rectangle(START_X, START_Y, BLOCK_SIZE*COL_CNT+4, BLOCK_SIZE*ROW_CNT+4);
		m_nextRect = new Rectangle(START_X+BLOCK_SIZE*COL_CNT+20, START_Y+30, 130, 80);
		gameStop.setEnabled(false);
		
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		m_Table = new int[ROW_CNT][COL_CNT];
		for(int i = 0; i < ROW_CNT; i++)
		{
			for(int j = 0 ; j < COL_CNT; j++)
			{
				m_Table[i][j] = -1;
			}
		}
		
		pan2.add(gameStart);
		pan2.add(gameStop);
		
		add("North", pan1);
		add("South", pan2);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.white);
		g.fillRect(m_mainRect.x, m_mainRect.y, m_mainRect.width, m_mainRect.height);
		g.setColor(Color.white);
		g.fillRect(m_nextRect.x, m_nextRect.y, m_nextRect.width, m_nextRect.height);
	}
	
	public class StartHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gameStart.setEnabled(false);
			gameStop.setEnabled(true);
			Graphics gra = getGraphics();
			m_bStart = true;
			play = new TetrisPlay(COL_CNT, ROW_CNT, START_X, START_Y, BLOCK_SIZE, m_Table, 
					m_nextRect, m_mainRect, m_bStart, rand, gameStart, gameStop, gra);
			PlayCT = new Thread(play);
			PlayCT.start();
		}
	}
	
	public class StopHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gameStart.setEnabled(true);
			gameStop.setEnabled(false);
		}
	}
}

class TetrisPlay implements Runnable{
	Point [][] pattern; //테트릭스 패턴
	Point [][] nextpattern; // 다음 패턴
	int m_nPattern;
	int m_nBitType;
	int m_nRot;
	int m_nX;
	int m_nY;
	
	JButton gameStart, gameStop;
	int COL_CNT;
	int ROW_CNT;
	int START_X;
	int START_Y;
	int BLOCK_SIZE;
	int[][] m_Table;
	Rectangle m_nextRect;
	Rectangle m_mainRect;
	boolean m_bStart;
	Random rand;
	Graphics gra;
	
	TetrisPlay(){}
	TetrisPlay(int COL_CNT, int ROW_CNT, int START_X, int START_Y, int BLOCK_SIZE, int[][] m_Table, Rectangle m_nextRect,
			Rectangle m_mainRect, boolean m_bStart, Random rand, JButton gameStart, JButton gameStop, Graphics gra)
	{
		blockPattern();
		this.COL_CNT = COL_CNT;
		this.ROW_CNT = ROW_CNT;
		this.START_X = START_X;
		this.START_Y = START_Y;
		this.BLOCK_SIZE = BLOCK_SIZE;
		this.m_Table = m_Table;
		this.m_nextRect = m_nextRect;
		this.m_mainRect = m_mainRect;
		this.m_bStart = m_bStart;
		this.rand = rand;
		this.gameStart = gameStart;
		this.gameStop = gameStop;
		this.gra = gra;
		
		m_nX = COL_CNT/2;
		m_nY = 0;
		m_nPattern = 0;
		m_nRot = 0;
		m_nBitType = 1;
	}
	
	@Override
	public void run() {
		while(m_bStart) {
			
		}
		menset();
	}
	
	private void menset() {
		for(int i = 0; i < ROW_CNT; i++)
		{
			for(int j = 0 ; j < COL_CNT; j++)
			{
				m_Table[i][j] = -1;
			}
		}
	}
	
	private void blockPattern() {
		Point [][] pattern; //테트릭스 패턴
		Point [][] nextpattern; // 다음 패턴
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