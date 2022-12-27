package Chatting;
import java.awt.*;
import java.awt.event.*;

public class Sj6ChartForm3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChatFrame13 cf = new ChatFrame13("Sejong Chatting3");
		cf.setVisible(true);
	}

}

class ChatFrame13 extends Frame{
	Panel pan1, pan2, pan3, pan11, pan12, pan21, pan22;
	TextArea showText;
	TextField severAddr, portNo, talkName, messageBox;
	Button connectButton, disconnectButton, sendButton;
	
	ChatFrame13(){}
	ChatFrame13(final String str){
		super(str);
		pan1 = new Panel();
		pan2 = new Panel();
		pan3 = new Panel();
		pan11 = new Panel();
		pan12 = new Panel();
		pan21 = new Panel();
		pan22 = new Panel();
		
		showText = new TextArea(10, 50);
		severAddr = new TextField("localhost", 10);
		portNo = new TextField("1234", 10);
		talkName = new TextField("¼Õ´Ô", 10);
		messageBox = new TextField(30);
		connectButton = new Button("Connect");
		disconnectButton = new Button("DisConnect");
		sendButton = new Button("Send");
		setSize(570,240);
		//addWindowListener(new Sj6WindowHandler());
		pan1.setBackground(Color.GREEN);
		pan2.setBackground(Color.YELLOW);
		pan11.setBackground(Color.LIGHT_GRAY);
		pan12.setBackground(Color.ORANGE);
		pan21.setBackground(Color.CYAN);
		pan22.setBackground(Color.BLUE);
		
		pan1.setLayout(new BorderLayout());
		pan12.setLayout(new BorderLayout());
		pan2.setLayout(new BorderLayout());
		pan21.setLayout(new BorderLayout());
		pan22.setLayout(new BorderLayout());
		
		pan21.add(showText);
		pan22.add(messageBox);
		
		pan2.add("North", pan21);
		pan2.add("Center", pan22);
		
		pan11.setLayout(new GridLayout(4, 2, 0, 10));
		pan11.add(new Label(" Server Ip"));
		pan11.add(severAddr);
		pan11.add(new Label(" Port No"));
		pan11.add(portNo);
		pan11.add(new Label(" Name"));
		pan11.add(talkName);
		pan11.add(connectButton);
		pan11.add(disconnectButton);
		pan12.add(sendButton);
		showText.setEditable(false);
		
		disconnectButton.setEnabled(false);
		sendButton.setEnabled(false);
		pan1.add("North", pan11);
		pan1.add("Center", pan12);
		
		add("East", pan1);
		add("Center", pan2);
	}
}