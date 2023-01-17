package ChattingForm;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class SjTetris2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TetrisFrame2 tetrisForm = new TetrisFrame2("Sejong Tetris1");
		tetrisForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tetrisForm.setSize(1000, 729);
		tetrisForm.setVisible(true);
	}

}

class TetrisFrame2 extends JFrame {
	JPanel pan1, pan2;
	JButton gameStart, gameStop;
	TetrisPlay2 play, play2;
	Thread PlayCT, PlayCT2;
	//Container c;
	
	final int COL_CNT = 10;
	final int ROW_CNT = 20;
	final int START_X = 10;
	final int START_Y = 30;
	final int BLOCK_SIZE = 32;
	final int START_X2 = START_X + 500;
	int[][] m_Table;
	Rectangle m_nextRect;
	Rectangle m_mainRect;
	Rectangle m_nextRect2;
	Rectangle m_mainRect2;
	boolean m_bStart;
	Random rand;
	
	public TetrisFrame2() {}
	public TetrisFrame2(String str) {
		super(str);
		
		gameStart = new JButton("Game Start");
		gameStart.addActionListener(new StartHandler());
		gameStop = new JButton("Game Stop");
		gameStop.addActionListener(new StopHandler());
		
		pan1 = new JPanel();
		pan2 = new JPanel();
		pan1.addKeyListener(new KeyHandler());
		pan2.addKeyListener(new KeyHandler2());
		
		m_bStart = false;
		m_mainRect = new Rectangle(START_X, START_Y, BLOCK_SIZE*COL_CNT+4, BLOCK_SIZE*ROW_CNT+4);
		m_nextRect = new Rectangle(START_X+BLOCK_SIZE*COL_CNT+20, START_Y+30, 130, 80);
		m_mainRect2 = new Rectangle(START_X2, START_Y, BLOCK_SIZE*COL_CNT+4, BLOCK_SIZE*ROW_CNT+4);
		m_nextRect2 = new Rectangle(START_X2+BLOCK_SIZE*COL_CNT+20, START_Y+30, 130, 80);
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
		
		pan1.setFocusable(true);
		pan2.setFocusable(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.drawRect(m_mainRect.x, m_mainRect.y, m_mainRect.width, m_mainRect.height);
		g.setColor(Color.black);
		g.drawRect(m_nextRect.x, m_nextRect.y, m_nextRect.width, m_nextRect.height);
		g.setColor(Color.black);
		g.drawRect(m_mainRect2.x, m_mainRect2.y, m_mainRect.width, m_mainRect.height);
		g.setColor(Color.black);
		g.drawRect(m_nextRect2.x, m_nextRect2.y, m_nextRect.width, m_nextRect.height);
	}
	
	public class StartHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gameStart.setEnabled(false);
			gameStop.setEnabled(true);
			Graphics gra = getGraphics();
			m_bStart = true;
			play = new TetrisPlay2(COL_CNT, ROW_CNT, START_X, START_Y, BLOCK_SIZE, 
					m_nextRect, m_mainRect, m_bStart, gameStart, gameStop, gra, pan1);
			play.PlayStart();
			PlayCT = new Thread(play);
			PlayCT.start();
			play2 = new TetrisPlay2(COL_CNT, ROW_CNT, START_X2, START_Y, BLOCK_SIZE, 
					m_nextRect2, m_mainRect2, m_bStart, gameStart, gameStop, gra, pan2);
			PlayCT2 = new Thread(play2);
			PlayCT2.start();
		}
	}
	
	public class StopHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gameStart.setEnabled(true);
			gameStop.setEnabled(false);
			m_bStart = false;
			play.PlayStop();
		}
	}
	
	public class KeyHandler implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(m_bStart) {
				if (e.getKeyCode() == 37)
				{
					//System.out.println("왼쪽 누름");
					play.MoveLeft();
				}
		        if (e.getKeyCode() == 38)
		        {
		        	//System.out.println("위 누름");
		        	play.RolateBlock(false);
				}    
		        if (e.getKeyCode() == 39)
		        {
		        	//System.out.println("오른쪽 누름");
		        	play.MoveRight();
		        }   
		        if (e.getKeyCode() == 40)
		        {
		        	//System.out.println("아래 누름");
		        	play.MoveDown();
		        }
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class KeyHandler2 implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(m_bStart) {
				if (e.getKeyCode() == 97 || e.getKeyCode() == 65)
				{
					//System.out.println("왼쪽 누름");
					play2.MoveLeft();
				}
		        if (e.getKeyCode() == 119|| e.getKeyCode() == 87)
		        {
		        	//System.out.println("위 누름");
		        	play2.RolateBlock(false);
				}    
		        if (e.getKeyCode() == 100 || e.getKeyCode() == 68)
		        {
		        	//System.out.println("오른쪽 누름");
		        	play2.MoveRight();
		        }   
		        if (e.getKeyCode() == 115 || e.getKeyCode() == 83)
		        {
		        	//System.out.println("아래 누름");
		        	play2.MoveDown();
		        }
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}

class TetrisPlay2 implements Runnable{
	Point [][] pattern; //테트릭스 패턴
	Point [][] nextpattern; // 다음 패턴
	int m_nNextPattern;
	int m_nPattern;
	int m_nBitType;
	int m_nRot;
	int m_nX;
	int m_nY;
	
	JPanel pan1;
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
	
	TetrisPlay2(){}
	TetrisPlay2(int COL_CNT, int ROW_CNT, int START_X, int START_Y, int BLOCK_SIZE, Rectangle m_nextRect,
			Rectangle m_mainRect, boolean m_bStart, JButton gameStart, JButton gameStop, Graphics g, JPanel pan1)
	{
		blockPattern();
		this.COL_CNT = COL_CNT;
		this.ROW_CNT = ROW_CNT;
		this.START_X = START_X;
		this.START_Y = START_Y;
		this.BLOCK_SIZE = BLOCK_SIZE;
		this.m_nextRect = m_nextRect;
		this.m_mainRect = m_mainRect;
		this.m_bStart = m_bStart;
		this.gameStart = gameStart;
		this.gameStop = gameStop;
		this.gra = g;
		this.pan1 = pan1;
		
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		m_Table = new int[ROW_CNT][COL_CNT];
		m_nX = COL_CNT/2;
		m_nY = 0;
		m_nPattern = 0;
		m_nRot = 0;
		m_nBitType = 1;
		m_nNextPattern = 0;
		InitialGame();
	}
	
	@Override
	public void run() {
		while(m_bStart) {
			DrawScr();
			BlockDown();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		menset();
		if(!m_bStart)
			System.out.println("종료\n");
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
	
	private void DrawScr() {
		int row, col;
		for(row = 0; row < ROW_CNT; row++) {
			for(col = 0; col < COL_CNT; col++) {
				if(m_Table[row][col] == -1) {
					gra.setColor(Color.white);
					gra.fillRect(START_X + 2 + col*BLOCK_SIZE, START_Y + 2 + row*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
				}
				else {
					gra.setColor(Color.blue);
					gra.fillRect(START_X + 2 + col*BLOCK_SIZE, START_Y + 2 + row*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
				}
				NextBlock(m_bStart);
			}
		}
	}
	
	private void InitialGame() {
		menset();
		DrawScr();
		m_nPattern = rand.nextInt(6);
		m_nRot = 0;
		m_nY = 1;
		m_nX = COL_CNT/2;
		DrawBlock(true);
	}
	
	public void DrawBlock(boolean bFlag) {
		for(int i = 0; i < 4; i++) {
			if(bFlag) {
				gra.setColor(Color.blue);
				gra.fillRect(START_X + 2 + (m_nX + pattern[m_nPattern][i + m_nRot * 4].x)*BLOCK_SIZE,
						START_Y + 2 + (m_nY + pattern[m_nPattern][i+m_nRot*4].y)*BLOCK_SIZE, 
						BLOCK_SIZE, BLOCK_SIZE);
			}
			else {
				gra.setColor(Color.white);
				gra.fillRect(START_X + 2 + (m_nX + pattern[m_nPattern][i + m_nRot * 4].x)*BLOCK_SIZE,
						START_Y + 2 + (m_nY + pattern[m_nPattern][i+m_nRot*4].y)*BLOCK_SIZE, 
						BLOCK_SIZE, BLOCK_SIZE);
			}
		}
	}
	
	private boolean BlockDown() {
		if(!IsAround(m_nX, m_nY + 1)) {
			SetTable();
			return false;
		}
		DrawBlock(false);
		m_nY++;
		DrawBlock(true);
		return true;
	}
	
	public boolean IsAround(int nX, int nY) {
		int i, row, col;
		for(i = 0; i < 4; i++) {
			col = nX + pattern[m_nPattern][i + m_nRot * 4].x;
			row = nY + pattern[m_nPattern][i + m_nRot * 4].y;
			if(col < 0 || col > COL_CNT - 1 || row < 1|| row > ROW_CNT - 1) {
				return false;
			}
			if(m_Table[row][col] != -1) {
				return false;
			}
		}
		return true;
	}
	
	private void SetTable() {
		int i, row, col, sw;
		for(i = 0; i < 4; i++) {
			m_Table[m_nY + pattern[m_nPattern][i + m_nRot * 4].y][m_nX + pattern[m_nPattern][i + m_nRot * 4].x] = m_nPattern;
		}
		for(row = ROW_CNT-1; row >= 0; row--) {
			sw = 0;
			for (col = 0; col < COL_CNT; col++)
			{
				if (m_Table[row][col] == -1)
					sw = -1;
			}
			if (sw == 0)
			{
				for (i = row; i > 0; i--)
				{
					for (col = 0; col < COL_CNT; col++)
					{
						m_Table[i][col] = m_Table[i - 1][col];
					}
				}
				for (col = 0; col < COL_CNT; col++)
				{
					gra.setColor(Color.white);
					gra.fillRect(START_X + 2 + col*BLOCK_SIZE, START_Y + 2 + row*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						System.out.println("오류가 발생했습니다.\n");
						System.exit(1);
					}
				}
				DrawScr();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("오류가 발생했습니다.\n");
					System.exit(1);
				}
				row++;
			}
		}
		m_nX = COL_CNT / 2;
		m_nY = 1;
		m_nPattern = m_nNextPattern;
		NextBlock(false);
		m_nNextPattern = rand.nextInt(6);
		NextBlock(true);
		m_nRot = 1;
		if(!IsAround(m_nX, m_nY + 1)) {
			gameStart.setEnabled(true);
			gameStop.setEnabled(false);
			m_bStart = false;
			pan1.setFocusable(false);
			return;
		}
	}
	
	private void NextBlock(boolean bFlag) {
		int i, x = 50, y = 10;
		if (m_nNextPattern == 0)
			x = 65;
		else if (m_nNextPattern == 1)
		{
			x = 65; 
			y = 0;
		}
			
		if (bFlag)
		{
			for (i = 0; i < 4; i++)
			{
				gra.setColor(Color.blue);
				gra.fillRect(m_nextRect.x + x + (nextpattern[m_nNextPattern][i].x) * BLOCK_SIZE,
						m_nextRect.y + y + (nextpattern[m_nNextPattern][i].y) * BLOCK_SIZE,
						BLOCK_SIZE, BLOCK_SIZE);
			}
		}
		else
		{
			gra.setColor(Color.white);
			gra.fillRect(m_nextRect.x, m_nextRect.y, m_nextRect.width, m_nextRect.height);
		}
	}
	
	public void RolateBlock(boolean bFlag) {
		int nRot = m_nRot;
		DrawBlock(false);
		if (++m_nRot > 3)
			m_nRot = 0;
		if (!IsAround(m_nX, m_nY))
			m_nRot = nRot;
		DrawBlock(true);
	}
	
	public void MoveDown() {
		while(BlockDown()) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("방향키 오류 발생");
				System.exit(1);
			}
		}
	}
	
	public void MoveRight()
	{
		if (!IsAround(m_nX + 1, m_nY))
			return;
		DrawBlock(false);
		m_nX++;
		DrawBlock(true);
	}


	public void MoveLeft()
	{
		if (!IsAround(m_nX - 1, m_nY))
			return;
		DrawBlock(false);
		m_nX--;
		DrawBlock(true);
	}
	
	public void PlayStop() {
		m_bStart = false;
	}
	
	public void PlayStart() {
		NextBlock(false);
	}
	
	private void blockPattern() {
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