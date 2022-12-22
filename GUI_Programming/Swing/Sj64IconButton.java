package Swing;
import javax.swing.*;
import java.awt.*;

public class Sj64IconButton {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame  frame = new JFrame("아이콘 버튼 이미지");
		SjswIconButton panel = new SjswIconButton();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
	}

}

class SjswIconButton extends JPanel{
	public SjswIconButton() {
		ImageIcon buttonImage = new ImageIcon("duke2.gif");
		ImageIcon pressImage = new ImageIcon("dukeMagnify.gif");
		ImageIcon selectImage = new ImageIcon("dukeWaveRed.gif");
		ImageIcon disabledImage = new ImageIcon("dukeSnooze.gif");
		ImageIcon rolloverImage = new ImageIcon("dukeWave.gif");
		ImageIcon selectrolloverImage = new ImageIcon("dukeSnooze.gif");
		
		AbstractButton button;
		setLayout(new GridLayout(1, 3, 5, 5));
		
		button = new JButton();
		button.setIcon(buttonImage);
		button.setPressedIcon(pressImage);
		button.setRolloverIcon(rolloverImage);
		add(button);
		
		button = new JButton();
		button.setIcon(buttonImage);
		button.setDisabledIcon(disabledImage);
		button.setEnabled(false);
		add(button);
		
		button = new JToggleButton();
		button.setIcon(buttonImage);
		button.setSelectedIcon(selectImage);
		button.setRolloverIcon(rolloverImage);
		
		button.setRolloverSelectedIcon(selectrolloverImage);
		add(button);
	}
}