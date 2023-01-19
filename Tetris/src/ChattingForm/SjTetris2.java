package ChattingForm;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import javax.swing.*;

public class SjTetris2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TetrisFrame2 tetrisForm = new TetrisFrame2("Sejong Tetris1");
		tetrisForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tetrisForm.setSize(1100, 770);
		tetrisForm.setVisible(true);
	}

}

class TetrisFrame2 extends JFrame {
	JPanel pan1, pan2, pan3, pan11, pan12;
	JTextField serverIp, portNo, talkName, messageBox;
	JButton gameStart, gameStop, connectButton, disConnectButton, sendButton;
	JRadioButton serverBt, clientBt, aloneBt;
	TetrisPlay2 play, play2;
	Thread PlayCT, PlayCT2;
	ButtonGroup mode;
	int modenum = 0;
	
	final int COL_CNT = 10;
	final int ROW_CNT = 20;
	final int START_X = 10;
	final int START_Y = 40;
	final int BLOCK_SIZE = 32;
	final int START_X2 = START_X + 500;
	
	int[][] m_Table;
	Rectangle m_nextRect;
	Rectangle m_mainRect;
	Rectangle m_nextRect2;
	Rectangle m_mainRect2;
	boolean m_bStart;
	boolean m_bStart2;
	Random rand;
	
	public TetrisFrame2() {}
	public TetrisFrame2(String str) {
		super(str);
		
		gameStart = new JButton("Game Start");
		gameStart.addActionListener(new StartHandler());
		gameStop = new JButton("Game Stop");
		gameStop.addActionListener(new StopHandler());
		messageBox = new JTextField(30);
		messageBox.setEnabled(false);
		serverIp = new JTextField("localhost", 10);
		serverIp.setEditable(false);
		portNo = new JTextField("1234", 10);
		portNo.setEditable(false);
		talkName = new JTextField("혼자놀기",10);
		talkName.setEditable(false);
		Label label1 = new Label(" Server Ip");
		Label label2 = new Label(" Port No");
		Label label3 = new Label(" Name");
		
		serverBt = new JRadioButton("server");
		clientBt = new JRadioButton("client");
		aloneBt = new JRadioButton("혼자놀기");
		aloneBt.setSelected(true);
		mode = new ButtonGroup();
		RadioHandler radioCt = new RadioHandler();
		serverBt.addActionListener(radioCt);
		clientBt.addActionListener(radioCt);
		aloneBt.addActionListener(radioCt);
		mode.add(serverBt);
		mode.add(clientBt);
		mode.add(aloneBt);
		connectButton = new JButton("connect");
		connectButton.addActionListener(new CBHandler());
		connectButton.setEnabled(false);
		disConnectButton = new JButton("disconnect");
		disConnectButton.addActionListener(new DBHandler());
		disConnectButton.setEnabled(false);
		sendButton = new JButton("send");
		sendButton.addActionListener(new SBHandler());
		sendButton.setEnabled(false);
		
		pan1 = new JPanel();
		pan2 = new JPanel();
		pan3 = new JPanel();
		pan11 = new JPanel();
		pan12 = new JPanel();
		
		pan1.setLayout(new BorderLayout());
		pan1.addKeyListener(new KeyHandler());
		pan2.setLayout(new GridLayout(20, 1, 0, 10));
		pan3.setLayout(new BorderLayout());
		
		m_bStart = false;
		m_bStart2 = false;
		m_mainRect = new Rectangle(START_X, START_Y, BLOCK_SIZE*COL_CNT+4, BLOCK_SIZE*ROW_CNT+4);
		m_nextRect = new Rectangle(START_X+BLOCK_SIZE*COL_CNT+20, START_Y+30, 130, 80);
		m_mainRect2 = new Rectangle(START_X2, START_Y, BLOCK_SIZE*COL_CNT+4, BLOCK_SIZE*ROW_CNT+4);
		m_nextRect2 = new Rectangle(START_X2+BLOCK_SIZE*COL_CNT+20, START_Y+30, 130, 80);
		gameStop.setEnabled(false);
		
		pan11.add(messageBox);
		pan11.add(sendButton);
		pan12.add(gameStart);
		pan12.add(gameStop);
		
		pan3.add("North", pan11);
		pan3.add("South", pan12);

		pan2.add(label1);
		pan2.add(serverIp);
		pan2.add(label2);
		pan2.add(portNo);
		pan2.add(label3);
		pan2.add(talkName);
		pan2.add(serverBt);
		pan2.add(clientBt);
		pan2.add(aloneBt);
		pan2.add(connectButton);
		pan2.add(disConnectButton);
		
		
		//add(mpan1);
		pan1.add("South", pan3);
		pan1.add("East", pan2);
		add(pan1);
		
		pan1.setFocusable(true);
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
			gameStart.setEnabled(false);
			gameStop.setEnabled(true);
			Graphics gra = getGraphics();
			m_bStart = true;
			m_bStart2 = true;
			play = new TetrisPlay2(COL_CNT, ROW_CNT, START_X, START_Y, BLOCK_SIZE, 
					m_nextRect, m_mainRect, m_bStart, gameStart, gameStop, gra, pan1);
			play.PlayStart();
			PlayCT = new Thread(play);
			PlayCT.start();
			play2 = new TetrisPlay2(COL_CNT, ROW_CNT, START_X2, START_Y, BLOCK_SIZE, 
					m_nextRect2, m_mainRect2, m_bStart2, gameStart, gameStop, gra, pan1);
			play2.PlayStart();
			PlayCT2 = new Thread(play2);
			PlayCT2.start();
			if(modenum != 0) {
				messageBox.setEnabled(true);
				sendButton.setEnabled(true);
			}
		}
	}
	
	public class StopHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gameStart.setEnabled(true);
			gameStop.setEnabled(false);
			sendButton.setEnabled(false);
			messageBox.setEnabled(false);
			m_bStart = false;
			m_bStart2 = false;
			play.PlayStop();
			play2.PlayStop();
		}
	}
	
	
	
	public class RadioHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == serverBt) {
				modenum = 1;
				talkName.setText("server");
				talkName.setEditable(false);
				connectButton.setEnabled(true);
			} else if (e.getSource() == clientBt) {
				modenum = 2;
				talkName.setText("손님");
				talkName.setEditable(true);
				connectButton.setEnabled(true);
			}
			else if (e.getSource() == aloneBt) {
				modenum = 0;
				talkName.setText("혼자 놀기");
				talkName.setEditable(false);
				connectButton.setEnabled(false);
			}
			//System.out.println("출력" + modenum);
		}
	}
	
	public class CBHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			serverBt.setEnabled(false);
			clientBt.setEnabled(false);
			aloneBt.setEnabled(false);
			serverIp.setEnabled(false);
			portNo.setEnabled(false); 
			talkName.setEnabled(false);
			connectButton.setEnabled(false);
			disConnectButton.setEnabled(true);
		}
	}
	
	public class DBHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			serverBt.setEnabled(true);
			clientBt.setEnabled(true);
			aloneBt.setEnabled(true);
			if(modenum == 1) {
				serverIp.setEnabled(true);
				portNo.setEnabled(true); 
				connectButton.setEnabled(true);
				disConnectButton.setEnabled(false);
			}
			else if(modenum == 2) {
				serverIp.setEnabled(true);
				portNo.setEnabled(true); 
				talkName.setEnabled(true);
				connectButton.setEnabled(true);
				disConnectButton.setEnabled(false);
			}
		}
	}
	
	public class SBHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
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
			if(m_bStart2) {
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
		public void keyReleased(KeyEvent e) {}
		@Override
		public void keyTyped(KeyEvent e) {}
	}
}

//server
class TChatServer extends Thread{
	ServerSocket serverSocket = null;
	Socket clientSocketet = null;
	ChatThread chatTrd;
	boolean bool = true;
	Vector<ChatThread> vClient = new Vector<>();
	
	int iportNo;
	TextArea showText;
	JList<String> list;
	DefaultListModel<String> model;
	
	TChatServer(){}
	TChatServer(TextArea showText, int iportNo, JList<String> l, DefaultListModel<String> m){
		this.showText = showText;
		this.iportNo = iportNo;
		this.list = l;
		this.model = m;
	}
	
	public void run()
	{
		try {
			serverSocket = new ServerSocket(1234);
		}catch(IOException e) {
			System.out.append("Server Socket 생성 오류 발생 !");
			System.exit(1);
		}
		showText.append("Chatting Server3이 1234번 Port에서 접속을 기다립니다.\n");
		try {
			while(bool) {
				clientSocketet = serverSocket.accept();
				chatTrd = new ChatThread(clientSocketet, vClient, showText, list, model);
				chatTrd.start();
				vClient.addElement(chatTrd);
			}
			serverSocket.close();
		}
		catch(IOException e) {
			System.out.println("접속 실패입니다.");
			System.exit(1);
		}
	}
	
	public void ServerStop() 
	{
		bool = false;
		try {
			for(int i = 0; i < vClient.size(); i++)
			{
				vClient.get(i).serverdisconnectMessage();
				vClient.get(i).clientSocket.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ServerStart()
	{
		bool = true;
	}
	
	public void ReceiveThreadClose(String data)
	{
		for(int i = 0; i < vClient.size(); i++)
		{
			if(data == vClient.get(i).strName)
			{
				try {
					vClient.get(i).disconnectMessage();
					vClient.get(i).clientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("연결해제 실패");
				}
			}
		}
	}
	
	public void SendMessage(String data) {
		for(int i = 0; i < vClient.size(); i++) {
			vClient.get(i).SendMessage(data);
		}
	}
}

class ChatThread extends Thread{
	Socket clientSocket = null;
	PrintWriter socketOut;
	BufferedReader socketIn;
	String strInput, strName = "NoName";
	Vector<ChatThread> vClient;
	TextArea showText;
	JList<String> list;
	DefaultListModel<String> model;
	
	public ChatThread() {}
	public ChatThread(Socket socket, Vector<ChatThread> v, TextArea showText, JList<String> l, DefaultListModel<String> m) {
		clientSocket = socket;
		this.vClient = v;
		this.showText = showText;
		this.list = l;
		this.model = m;
	}
	
	public void removeClient() throws IOException{
		vClient.removeElement(this);
		broadcast("[" + strName + "] 님이 퇴장하셨습니다.");
	}
	
	public void sendUserList() throws IOException{
		socketOut.println("< 현재 접속자 " + vClient.size() + "명 명단 >");
		for(int i = 0; i < vClient.size(); i++) {
			ChatThread trd = ((ChatThread)vClient.elementAt(i));
			socketOut.println(trd.strName);
		}
	}
	
	public void run() {
		try {
			showText.append("Client:" + clientSocket.toString() + "\n에서 접속하였습니다.\n");
			socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			socketOut.println("SjChatServer");
			strInput = socketIn.readLine();
			if(strInput.equals("SjChatClient")) {
				socketOut.println("<단축키> : /h(도움말), /u(접속자목록), /r 대화명 (대화명 변경)");
				//socketOut.println("대화명을 입력하세요 !");
				strName = socketIn.readLine();
				broadcast("[" + strName + "] 님이 입장하셨습니다.");
				ListSort();
				
				while((strInput = socketIn.readLine()) != null) {
					if(strInput.equals("/h")) {
						socketOut.println("<단축키> : /h(도움말), /u(접속자목록), /r 대화명 (대화명 변경)");
					}else if(strInput.equals("/u")) {
						sendUserList();
					}
					else if(strInput.regionMatches(0, "/r", 0, 2)) {
						String new_name = strInput.substring(2).trim();
						broadcast("접속자 " + strName + " 님의 대화명이 [" + new_name + "](으)로 바뀌었습니다.");
						strName = new_name;
					}
					else {
						broadcast("[" + strName + "]" + strInput);
					}
				}
			}else {
				socketOut.println("잘못된 Client입니다.");
			}
			socketOut.close();
			socketIn.close();
			clientSocket.close();
			removeClient();
		}catch(IOException e) {
			try {
				removeClient();
			}catch(IOException e1) {}
			showText.append(" " + strName + "의 접속이 끊겼습니다.\n");
			ListSort();
		}
	}
	
	public void broadcast(String msg) throws IOException{
		for(int i = 0; i < vClient.size(); i++) {
			ChatThread trd = ((ChatThread)vClient.elementAt(i));
			trd.socketOut.println(msg);
		}
		showText.append(msg + "\n");
	}
	
	public void ListSort()
	{
		for(int i = 0; i < model.size(); i++)
		{
			model.remove(i);
		}
		for(int i = 0; i < vClient.size(); i++)
		{
			model.addElement(vClient.get(i).strName);
		}
		list = new JList<>(model);
	}
	
	public void disconnectMessage() {
		socketOut.println("강퇴되었습니다.");
	}
	
	public void serverdisconnectMessage() {
		socketOut.println("서버 연결이 끊겼습니다.");
		ListSort();
	}
	
	public void SendMessage(String data) {
		System.out.println(data);
		socketOut.println(data);
	}
}

//client
class TChatClient{
	Socket echoSocket = null;
	PrintStream socketOut = null;
	BufferedReader socketIn = null;
	BufferedReader stdIn;
	String strUser, strMsg;
	ReceiveThread rec;
	JTextArea showText;
	JTextField messageBox;
	
	String talkName;
	
	TChatClient(){}
	TChatClient(JTextArea ST, JTextField MB){
		showText = ST;
		messageBox = MB;
	}
	
	public void connect(int iportNo, String sseverIp, String stalkName) {
		try {
			echoSocket = new Socket(sseverIp, iportNo);//new Socket("localhost", 1234);
			socketOut = new PrintStream(echoSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			strMsg = socketIn.readLine();
			talkName = stalkName;
			//if(strMsg.equals("Sj10ChatServer")) {
			if(strMsg.equals("SjChatServer")) {
				socketOut.println("SjChatClient");
				socketOut.println(talkName);
				rec = new ReceiveThread(socketIn, showText);
				rec.start();
			}
			else {
				System.out.println("잘못된 Server입니다.");
				socketOut.close();
				socketIn.close();
				echoSocket.close();
			}
		}
		catch(UnknownHostException e) {
			System.err.println("Server가 없습니다.");
			System.exit(1);
		}
		catch(IOException e) {
			System.err.println("입출력  Error.");
			System.exit(1);
		}
		catch(Exception e) {
			System.out.println("연결이 끊겼습니다.");
			System.exit(1);
		}
	}
	
	public void disconnect()
	{
		try {
			socketOut.close();
			socketIn.close();
			echoSocket.close();
		}
		catch(UnknownHostException e) {
			System.err.println("Server가 없습니다.");
			System.exit(1);
		}
		catch(IOException e) {
			System.err.println("입출력  Error.");
			System.exit(1);
		}
		catch(Exception e) {
			System.out.println("연결이 끊겼습니다.");
			System.exit(1);
		}
	}
	
	public void send(String stalkName) {
		if(!talkName.equals(stalkName))
		{
			strUser = "/r" + stalkName;
			socketOut.println(strUser);
			talkName = stalkName;
		}
		strUser = messageBox.getText();
		socketOut.println(strUser);
	}
}

class ReceiveThread extends Thread{
	BufferedReader socketIn = null;
	String strSocket;
	JTextArea showText;
	
	ReceiveThread(){}
	ReceiveThread(BufferedReader socketIn, JTextArea showText){
		this.socketIn = socketIn;
		this.showText = showText;
	}
	public void run() {
		showText.append("Server에 접속됨");
		try {
			while((strSocket = socketIn.readLine()) != null) {
				showText.append(strSocket + "\n");
			}
		}
		catch(Exception e) {
			showText.append("연결이 끊겼습니다.");
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
		m_nPattern = rand.nextInt(7);
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
		m_nNextPattern = rand.nextInt(7);
		NextBlock(true);
		m_nRot = 1;
		if(!IsAround(m_nX, m_nY + 1)) {
			gameStart.setEnabled(true);
			gameStop.setEnabled(false);
			m_bStart = false;
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
		if(m_bStart) {
			int nRot = m_nRot;
			DrawBlock(false);
			if (++m_nRot > 3)
				m_nRot = 0;
			if (!IsAround(m_nX, m_nY))
				m_nRot = nRot;
			DrawBlock(true);
		}
	}
	
	public void MoveDown() {
		if(m_bStart) {
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
	}
	
	public void MoveRight()
	{
		if(m_bStart) {
			if (!IsAround(m_nX + 1, m_nY))
				return;
			DrawBlock(false);
			m_nX++;
			DrawBlock(true);
		}
	}


	public void MoveLeft()
	{
		if(m_bStart) {
			if (!IsAround(m_nX - 1, m_nY))
				return;
			DrawBlock(false);
			m_nX--;
			DrawBlock(true);
		}
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