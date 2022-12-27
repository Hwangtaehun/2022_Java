package Chatting;
import java.awt.*;
import javax.swing.*;

public class Sj65ChatForm3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChatFrame63 chatForm = new ChatFrame63("Sejong Chatting 3");
		chatForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatForm.setSize(600, 260);
		chatForm.setVisible(true);
	}

}

class ChatFrame63 extends JFrame{
	JTextArea showText;
	JTextField severIp, portNo, talkName, messageBox;
	JButton connectButton, disConnectButton, sendButton;
	JPanel pan1, pan2, pan3, pan31, pan32;
	
	public ChatFrame63() {}
	public ChatFrame63(String str) {
		super(str);
		showText = new JTextArea(8,78);
		severIp = new JTextField("localhost", 7);
		portNo = new JTextField("1234", 4);
		talkName = new JTextField("¼Õ´Ô1", 4);
		connectButton = new JButton("Connect");
		disConnectButton = new JButton("DisConnect");
		messageBox = new JTextField(38);
		sendButton = new JButton("Send");
		
		pan1 = new JPanel();
		pan2 = new JPanel();
		pan3 = new JPanel();
		pan31 = new JPanel();
		pan32 = new JPanel();
		
		pan1.setBackground(Color.GREEN);
		pan2.setBackground(Color.YELLOW);
		pan3.setBackground(Color.CYAN);
		
		add("North", pan1);
		add("Center", pan2);
		add("South", pan3);
		
		pan2.setLayout(new BorderLayout());
		pan2.add(showText);
		showText.setEditable(false);
		
		pan31.add(new Label("Server IP"));
		pan31.add(severIp);
		pan31.add(new Label(" port No"));
		pan31.add(portNo);
		pan31.add(new Label(" ´ë  È­  ¸í"));
		pan31.add(talkName);
		pan31.add(connectButton);
		pan31.add(disConnectButton);
		disConnectButton.setEnabled(false);
		pan32.add(new Label("Message"));
		pan32.add(messageBox);
		messageBox.setEditable(false);
		pan32.add(sendButton);
		sendButton.setEnabled(false);
		
		pan3.setLayout(new BorderLayout());
		pan3.add("Center", pan31);
		pan3.add("South", pan32);
	}
}