package Tetris2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SjTetris2FrameD extends JFrame implements ActionListener{
	JButton gameStartBt;
	JButton gameStopBt;
	JPanel bottomPanel;
	SjTetris2PanelD leftPanel;
	SjTetris2PanelD rightPanel;
	Color[] colorType;
	
	SjTetris2FrameD(){}
	SjTetris2FrameD(String title){
		super(title);
		colorType = new Color[8];
		setColorType();
		
		setLayout(null);
		
		leftPanel = new SjTetris2PanelD(colorType);
		leftPanel.setBounds(10, 10, 181, 370);
		leftPanel.setBackground(Color.CYAN);
		leftPanel.initForm();
		getContentPane().add(leftPanel);
		
		rightPanel = new SjTetris2PanelD(colorType);
		rightPanel.setBounds(395, 10, 181, 370);
		rightPanel.setBackground(Color.ORANGE);
		rightPanel.initForm();
		getContentPane().add(rightPanel);
		
		bottomPanel = new JPanel();
		bottomPanel.setBounds(10, 400, 571, 36);
		bottomPanel.setBackground(Color.YELLOW);
		gameStartBt = new JButton("Game Start");
		gameStartBt.addActionListener(this);
		bottomPanel.add(gameStartBt);
		gameStopBt = new JButton("Game Stop");
		gameStopBt.addActionListener(this);
		bottomPanel.add(gameStopBt);
		getContentPane().add(bottomPanel);
		
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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == gameStartBt) {
			leftPanel.gameStart();
			rightPanel.gameStart();
			this.requestFocus();
		}
		else {
			leftPanel.gameStop();
			rightPanel.gameStop();
		}
	}
	
	class MyKeyHandler1 extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			int keyCode = (int)e.getKeyCode();
			
			if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT||
			   keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_UP	) {
				rightPanel.keyPressed(keyCode);
			}
			if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D||
			   keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_W	) {
				leftPanel.keyPressed(keyCode);
			}
		}
	}
}
