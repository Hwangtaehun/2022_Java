package Tetris;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class SjTetris1Panel extends JPanel {
	final static int X_CNT = 12;
	final static int Y_CNT = 24;
	final static int SIZE = 20;
	final static int NEXT_XCNT = 6;
	final static int NEXT_YCNT = 6;
	
	byte [][][] Pattern;
	byte [][] dispMap;
	Color [] colorType;
	
	byte patternNo;
	byte nextPatternNo;
	int curX, curY;
	int m_nRot;
	
	Thread g_Thread;
	boolean  bGame;
	Random rr;
	SjTetris1Frame frame;
	
	public SjTetris1Panel() {}
	public SjTetris1Panel(SjTetris1Frame frame){
		this.frame = frame;
		this.colorType = frame.colorType;
		this.Pattern = frame.Pattern;
	}
	
	void initForm() {
		dispMap = new byte[X_CNT][Y_CNT];
		rr = new Random();
		initGame();
	}
	
	void initGame() {
		curX = X_CNT/2;
		curY = 1;
		clearMap();
		patternNo = (byte)(rr.nextInt(7));
		nextPatternNo = (byte)(rr.nextInt(7));
		m_nRot = 0;
	}
	
	public void clearMap() {
		int x, y;
		for(x = 0; x < X_CNT; x++) {
			for(y = 0; y < Y_CNT; y++) {
				dispMap[x][y] = 7;
			}
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		drawScreen(g);
	}
	
	public void drawScreen(Graphics gra) {
		int x, y;
		for(x = 0; x < X_CNT; x++) {
			for(y = 0; y < Y_CNT; y++) {
				gra.setColor(colorType[dispMap[x][y]]);
				gra.fillRect(x*SIZE, y*SIZE, SIZE, SIZE);
			}
		}
		
		gra.setColor(Color.blue);
		for(x = 0; x < X_CNT; x++) {
			for(y = 0; y < Y_CNT; y++) {
				gra.drawRect(x*SIZE, y*SIZE, SIZE, SIZE);
			}
		}
		
		drawNextBlock(gra);
	}
	
	public void drawBlock(boolean check) {
		int i;
		Graphics gra = getGraphics();
		if(check) {
			gra.setColor(colorType[patternNo]);
		}
		else {
			gra.setColor(colorType[dispMap[curX][curY]]);
		}
		for(i = 0; i < 4; i++) {
			gra.fillRect((curX + Pattern[patternNo][i+m_nRot*4][0])*SIZE, 
					(curY + Pattern[patternNo][i+m_nRot*4][1])*SIZE, SIZE, SIZE);
		}
		
		gra.setColor(Color.blue);
		for(i = 0; i < 4; i++) {
			gra.drawRect((curX + Pattern[patternNo][i+m_nRot*4][0])*SIZE, 
					(curY + Pattern[patternNo][i+m_nRot*4][1])*SIZE, SIZE, SIZE);
		}
	}
	
	public void drawNextBlock(Graphics gra) {
		int i;
		gra.setColor(Color.white);
		gra.fillRect(260, 10, NEXT_XCNT*SIZE, NEXT_YCNT*SIZE);
		gra.setColor(colorType[nextPatternNo]);
		
		for(i = 0; i < 4; i++) {
			gra.fillRect(320 + (Pattern[nextPatternNo][i+m_nRot*4][0])*SIZE, 
						  50 + (Pattern[nextPatternNo][i+m_nRot*4][1])*SIZE, SIZE, SIZE);
		}
	}
	
	boolean isAround(int cx, int cy) {
		int x, y;
		for(int i = 0; i < 4; i++) {
			x = cx + Pattern[patternNo][i+m_nRot*4][0];
			y = cy + Pattern[patternNo][i+m_nRot*4][1];
			if(x < 0 || x >= X_CNT || y < 0 || y >= Y_CNT) {
				return false;
			}
			if(dispMap[x][y] < 7)
				return false;
		}
		return true;
	}
	
	void blockDown() {
		while(moveDown()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ie) {}
		}
	}
	
	boolean moveDown() {
		if(!isAround(curX, curY + 1)) {
			setTable();
			return false;
		}
		drawBlock(false);
		curY++;
		drawBlock(true);
		return true;
	}
	
	void moveLeft() {
		if(!isAround(curX - 1, curY)) 
			return;
		drawBlock(false);
		curX--;
		drawBlock(true);
	}
	
	void moveRight() {
		if(!isAround(curX + 1, curY))
			return;
		drawBlock(false);
		curX++;
		drawBlock(true);
	}
	
	void rolateBlock() {
		int nRot = m_nRot;
		drawBlock(false);
		if(m_nRot < 3)
			m_nRot++;
		else
			m_nRot = 0;
		if(!isAround(curX, curY))
			m_nRot = nRot;
		drawBlock(true);
	}
	
	void setTable() {
		int x, y, sw, i;
		
		for(i = 0; i < 4; i++) {
			try {
				dispMap[curX+Pattern[patternNo][i + m_nRot*4][0]][curY+Pattern[patternNo][i+m_nRot*4][1]] = patternNo;
			}
			catch(IndexOutOfBoundsException ie) {}
		}
		
		for(y = Y_CNT-1; y >= 0; y--) {
			sw = 0;
			for(x = 0; x < X_CNT; x++) {
				if(dispMap[x][y] == 7)
					sw = 1;
			}
			if(sw == 0)
			{
				for(i = y; i > 0; i--) {
					for(x = 0; x < X_CNT; x++) {
						dispMap[x][i] = dispMap[x][i - 1];
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException ie) { }
					super.repaint();
				}
				y++;
			}
		}
		
		curX = X_CNT/2;
		curY = 1;
		patternNo = nextPatternNo;
		nextPatternNo = (byte)(rr.nextInt(7));
		drawNextBlock(getGraphics());
		
		if(!isAround(curX, curY + 1)) {
			gameStop();
			frame.setEnabledStartButton(true);
			System.out.println("game end");
		}
	}
	
	public void gameStop() {
		bGame = false;
	}
	
	public void gameStart() {
		initGame();
		repaint();
		gameReStart();
	}
	
	public void gameReStart() {
		bGame = true;
		g_Thread = new game1();
		g_Thread.start();
	}
	
	public void keyPressed(int keyCode) {
		if(!bGame)
			return;
		
		switch(keyCode) {
		case KeyEvent.VK_LEFT:
			moveLeft();
			break;
			
		case KeyEvent.VK_RIGHT:
			moveRight();
			break;
			
		case KeyEvent.VK_DOWN:
			moveDown();
			break;
			
		case KeyEvent.VK_UP:
			rolateBlock();
			break;
			
		case KeyEvent.VK_SPACE:
		case KeyEvent.VK_ENTER:
			gameStop();
			blockDown();
			gameReStart();
			break;
		}
	}
	
	int delayTime = 500;
	class game1 extends Thread{
		public void run() {
			while(bGame) {
				try {
					Thread.sleep(delayTime);
				} catch (InterruptedException e) {}
				moveDown();
			}
		}
	}
}