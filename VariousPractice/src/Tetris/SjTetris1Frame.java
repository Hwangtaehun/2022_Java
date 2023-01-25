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
		{{0,0}, {0,-1}, {-1,0}, {-1,-1}, {0,0}, {0,-1}, {-1,0}, {-1,-1},
		 {0,0}, {0,-1}, {-1,0}, {-1,-1}, {0,0}, {0,-1}, {-1,0}, {-1,-1}},
		{{0,0}, {1,0}, {-1,0}, {-2,0}, {0,0}, {0,1}, {0,2}, {0,-1},
		 {0,0}, {1,0}, {-1,0}, {-2,0}, {0,0}, {0,1}, {0,2}, {0,-1}},
		{{0,0}, {-1,0}, {0,-1}, {1,-1}, {0,0}, {0,1}, {-1,0}, {-1,-1},
		 {0,0}, {-1,0}, {0,-1}, {1,-1}, {0,0}, {0,1}, {-1,0}, {-1,-1}},
		{{0,0}, {-1,-1}, {0,-1}, {1,0}, {0,0}, {-1,0}, {-1,1}, {0,-1},
		 {0,0}, {-1,-1}, {0,-1}, {1,0}, {0,0}, {-1,0}, {-1,1}, {0,-1}},
		{{-1,0}, {-1,1}, {0,1}, {1,1}, {0,1}, {1,1}, {1,0}, {1,-1},
		 {-1,-1}, {0,-1}, {1,-1}, {1,0}, {0,-1}, {1,-1}, {1,0}, {-1,1}},
		{{-1,1}, {0,1}, {1,1}, {1,0}, {0,-1}, {1,-1}, {1,0}, {1,1},
		 {-1,0}, {-1,-1}, {0,-1}, {1,-1}, {-1,1}, {1,0}, {-1,-1}, {0,1}},
		{{0,0}, {-1,0}, {1,0}, {0,1}, {0,0}, {0,-1}, {0,1}, {1,0},
		 {0,0}, {-1,0}, {1,0}, {0,-1}, {0,0}, {-1,0}, {0,-1}, {0,1}}
	};
	
	SjTetris1Frame(){}
	SjTetris1Frame(String title){
		super(title);
		colorType = new Color[8];	
	}
	
	void setColorType() {
		
	}
	
	void setEnabledStartButton(Boolean bool) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	class MyKeyHandler1 extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			
		}
	}
}
