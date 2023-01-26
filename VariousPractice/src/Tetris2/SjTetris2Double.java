package Tetris2;
import java.awt.*;
import javax.swing.*;

public class SjTetris2Double {
	public static void main(String[] args) {
		SjTetris2FrameD mainFrame = new SjTetris2FrameD(" Sejong Tetris 2 인용");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setBackground(Color.green);
		mainFrame.setSize(600, 500);
		mainFrame.setVisible(true);
	}
}
