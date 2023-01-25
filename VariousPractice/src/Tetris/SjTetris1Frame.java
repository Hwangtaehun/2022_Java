package Tetris;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SjTetris1Frame extends JFrame implements ActionListener{
	JButton gameStartBt;
	JButton gameStopBt;
	JPanel bottomPanel;
	SjTetris1Panel leftPanel;
	Color[] colorType;
	
	final static byte Pattern[][][] = {
			{ { 0, 0 }, { 0, -1 }, { -1, 0 }, { -1, -1 }, { 0, 0 }, { 0, -1 }, { -1, 0 }, { -1, -1 }, 
			{ 0, 0 }, { 0, -1 }, { -1, 0 }, { -1, -1 }, { 0, 0 }, { 0, -1 }, { -1, 0 }, { -1, -1 } },
			{ { 0, 0 }, { 1, 0 }, { -1, 0 }, { -2, 0 }, { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, -1 }, 
			{ 0, 0 }, { 1, 0 }, { -1, 0 }, { -2, 0 }, { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, -1 } },
			{ { 0, 0 }, { -1, 0 }, { 0, -1 }, { 1, -1 }, { 0, 0 }, { 0, 1 }, { -1, 0 }, { -1, -1 }, 
			{ 0, 0 }, { -1, 0 }, { 0, -1 }, { 1, -1 }, { 0, 0 }, { 0, 1 }, { -1, 0 }, { -1, -1 } },
			{ { 0, 0 }, { -1, -1 }, { 0, -1 }, { 1, 0 }, { 0, 0 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, 
			{ 0, 0 }, { -1, -1 }, { 0, -1 }, { 1, 0 }, { 0, 0 }, { -1, 0 }, { -1, 1 }, { 0, -1 } },
			{ { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, 
			{ -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } },
			{ { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 },
			{ -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { -1, 1 }, { -1, 0 }, { -1, -1 }, { 0, 1 } },
			{ { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 }, 
			{ 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } }
	};
	
	SjTetris1Frame(){}
	SjTetris1Frame(String title){
		super(title);
		colorType = new Color[8];
		setColorType();
		leftPanel = new SjTetris1Panel(this);
		leftPanel.setBackground(Color.CYAN);
		
		getContentPane().add("Center", leftPanel);
		bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.YELLOW);
		
		gameStartBt = new JButton("Game Start");
		gameStartBt.addActionListener(this);
		bottomPanel.add(gameStartBt);
		
		gameStopBt = new JButton("Game Stop");
		gameStopBt.addActionListener(this);
		bottomPanel.add(gameStopBt);
		
		getContentPane().add("South", bottomPanel);
		setEnabledStartButton(true);
		leftPanel.initForm();
		addKeyListener(new MyKeyHandler1());
	}
	
	void setColorType() {
		colorType[0] = Color.yellow;
		colorType[1] = Color.cyan;
		colorType[2] = Color.gray;
		colorType[3] = Color.green;
		colorType[4] = Color.magenta;
		colorType[5] = Color.orange;
		colorType[6] = Color.pink;
		colorType[7] = Color.lightGray;
	}
	
	void setEnabledStartButton(Boolean bool) {
		gameStartBt.setEnabled(bool);
		gameStopBt.setEnabled(!bool);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == gameStartBt) {
			leftPanel.gameStart();
			this.requestFocus();
			setEnabledStartButton(false);
		}
		else {
			leftPanel.gameStop();
			setEnabledStartButton(true);
		}
	}
	
	class MyKeyHandler1 extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			int keyCode = (int)e.getKeyCode();
			leftPanel.keyPressed(keyCode);
		}
	}
}
