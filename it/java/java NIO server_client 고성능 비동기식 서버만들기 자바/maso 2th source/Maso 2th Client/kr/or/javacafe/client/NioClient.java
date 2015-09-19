package kr.or.javacafe.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;

/**
 * @(#)NioClient.java
 * 
 * @author <a href="mailto:johnleen@hanmail.net">Song Ji-Hoon.</a>
 * @version 1.12, 02/10/29
 * 
 * NioClient 클래스는 3개의 UI 형태를 갖도록 만들었다.
 * 맥 OS 의 아쿠아와 현재 시스템(아마도 대부분 윈도우) UI, 그리고 스윙의 디폴트 UI 인
 * 메탈을 사용할 수 있다.
 */
public class NioClient extends JFrame implements ActionListener, MouseListener {
	
	private Font font;
	private JLabel label;
	private TextArea ta;
	private TextField tf;
	private JButton btn, btn2;
	private JMenuBar menuBar;
	private JMenuItem menuItem;
	private JMenu menu;
	
	private SocketChannel client;
	private InetAddress ia;
	private InetSocketAddress isa;
	private ReceiveThread rt;
	
	private static final String HttpRequestHeader = "POST / HTTP/1.1\r\n\r\n";
	private static final String XmlHeader = "<?xml version='1.0' encoding='UTF-8'?><request><command>";
	private static final String CommandEnd = "</command><message>";
	private static final String MessageEnd = "</message></request>";
	private static final String MessageCommand = "MessageCommand";
	private static final String WowCommand = "WowCommand";
	
	public NioClient(String title) {
		super(title);
			
		initGUI();	
		connectToServer();	
		receiveMessage(); 					
	}
	
	private void setMenu() {
		menuBar = new JMenuBar();		

  		menu = new JMenu("파일(F)");
		menu.setMnemonic('F');
		menuBar.add(menu);

  		menuItem = new JMenuItem("프로그램 종료(X)");
		menuItem.setMnemonic('X');
		menuItem.setActionCommand("exit");
		menuItem.addActionListener(this);
  		menuItem.setBackground(Color.white);
		menu.add(menuItem);
			
    	setJMenuBar(menuBar);  	
	}
	
	public void initGUI() {
		setMenu();
		
		font = new Font("Times New Roman", 1, 20);
		label = new JLabel("www.JavaCafe.or.kr", JLabel.CENTER);
		label.setForeground(Color.blue);
		label.setFont(font);
		
		ta = new TextArea();
		ta.setEditable(false);
		ta.setFocusable(false);
	
		btn = new JButton("Send");
		btn.setActionCommand("send");
		btn.addActionListener(this);
		btn.addMouseListener(this);
		
		btn2 = new JButton("Wow");
		btn2.setActionCommand("wow");
		btn2.addActionListener(this);
		btn2.addMouseListener(this);
		
		tf = new TextField();
		tf.addActionListener(this);
		
		getContentPane().add("North", label);
		getContentPane().add("Center", ta);		
		getContentPane().add("East", btn);
		getContentPane().add("West", btn2);
		getContentPane().add("South", tf);

		setSize(400, 250);
		setResizable(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/title.gif"));

	    Dimension dim = getToolkit().getScreenSize();
	    setLocation((dim.width - getWidth()) / 2, (dim.height - getHeight()) / 2);		
	
	    show();
	    
	    tf.requestFocus();
	    ta.setFocusable(true);
	    
	    addExitListener();    
	}
	
	private void addExitListener() {
		addWindowListener(
		    new WindowAdapter() {
		        public void windowClosing(WindowEvent event) {
					exit();
		        }
	        }
		);
	}

	public void exit() {
		System.out.println(" NioClient exit...");
		System.out.println(" Good Bye~!");		
		System.exit(0);
	}
	
	public void connectToServer() {
		try {
			//ia = InetAddress.getByName("218.235.126.118");
			ia = InetAddress.getLocalHost(); 	
			isa = new InetSocketAddress(ia, 4567);				
			client = SocketChannel.open(isa);
			client.configureBlocking(false);		   
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void sendMessage(String cmd) {
		String msg = null;
		ByteBuffer buffer = ByteBuffer.allocate(2048);

		try {
			if (tf.getText().equals(""))
				return;
				
			msg = createMessage(cmd, tf.getText());
			tf.setText("");				
			buffer = ByteBuffer.wrap(msg.getBytes("UTF-8"));			
			client.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String createMessage(String cmd, String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append(HttpRequestHeader);
		sb.append(XmlHeader);
		sb.append(cmd);
		sb.append(CommandEnd);
		sb.append(msg);
		sb.append(MessageEnd);
		return sb.toString();
	}
	
	private void receiveMessage() {
		rt = new ReceiveThread("Receive Thread", client);
		rt.start();
	}
	
	public void appendMessage(String msg) {
		ta.append(msg + "\n");
	}
	
	// *************** Action Event. ******************
	public void actionPerformed(ActionEvent e) { 
		String cmd = e.getActionCommand();

		if (cmd.equals("send")) { 
			sendMessage(MessageCommand);
			tf.requestFocus(); 
			return;
		} else if (cmd.equals("exit")) 	{ 
			exit(); 
			return;		 
		} else if (cmd.equals("wow")) 	{ 
			sendMessage(WowCommand);  
			tf.requestFocus();
			return;		 
		} 
		sendMessage(MessageCommand);
	}
	
	
	// ************** Mouse Event. ********************
	public void mouseEntered(MouseEvent e)	{
		JButton btn = (JButton)e.getSource();
		btn.setForeground(Color.red);
	}
    public void mouseExited(MouseEvent e)		{
		JButton btn = (JButton)e.getSource();
		btn.setForeground(Color.black);
	}
	public void mouseClicked(MouseEvent e)	{}
	public void mouseDragged(MouseEvent e)	{}
	public void mousePressed(MouseEvent e)	{}
    public void mouseReleased(MouseEvent e) 	{} 
    
    
    class ReceiveThread extends Thread { 
		private SocketChannel sc = null;
		private boolean isRunning = true;
		
		private Charset charset;
		private CharsetDecoder decoder;
		private CharBuffer charBuffer;
		
		private ByteArrayInputStream in;
		private SAXParserFactory factory;
		private SAXParser parser;
		private SaxHandler handler;
	
		public ReceiveThread(String str, SocketChannel client) {
			super(str);
			sc = client;
			
			charset = Charset.forName("UTF-8");
			decoder = charset.newDecoder();
						
			try {
				factory = SAXParserFactory.newInstance(); 
				parser = factory.newSAXParser(); 
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException ex) {
				ex.printStackTrace();
			}
		}
	
		public void run() {
			int size;
			ByteBuffer buffer = ByteBuffer.allocateDirect(2048);
			try {
				while (isRunning) {
					while (true) {
						size = sc.read(buffer);
						if (size > 0) {
							buffer.flip();
							charBuffer = decoder.decode(buffer);						
						    String temp = charBuffer.toString();
						    byte[] bb = temp.substring(temp.indexOf("\r\n\r\n")).trim().getBytes("UTF-8");							    					    
							String[] msg = parsingXML(bb);
							if (msg.length > 0) {								
								appendMessage(msg[0]);
								System.out.println(msg[0]);
							}									
						}	
						buffer.clear();						
						this.sleep(100);													
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException ex) {
			}         
		}

		private String[] parsingXML(byte[] xml) {
			String[] param = null;
			ArrayList list = null;
		
			if (xml == null) 
				return param;
		
			in = new ByteArrayInputStream(xml);
			try {          
				handler = SaxHandler.getInstance(); 
				parser.parse(in, handler);          
				list = handler.getContents();  
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		
			param = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {  
				param[i] = (String) list.get(i);
			}		
			handler.clearSaxHandler();
		
			return param;
		}	
		
		public void setIsRunning(boolean isRunning) { this.isRunning = isRunning; }
		public boolean getIsRunning() { return isRunning; }
    }

    
	//**************    Main   *****************
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("*********** 사용법 ***************");
			System.out.println("java -classpath skinlf.jar; kr.or.javacafe.client.NioClient [aqua | system | metal] ");
			System.exit(1);		
		}
		
		try {  
			if (args[0].equals("aqua")) {
				// 아쿠아 UI..
				SkinLookAndFeel.setSkin(SkinLookAndFeel.loadThemePack ("aquathemepack.zip"));
				UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
			} else if (args[0].equals("system")) {
				// System UI.. 
				String ui = UIManager.getSystemLookAndFeelClassName();
				UIManager.setLookAndFeel(ui);
			} else if (args[0].equals("metal")) {
				// Metal UI..
			}
		} catch (Throwable t) {
			System.out.println(" Uncaught Exception: " + t); 
			t.printStackTrace();
		}

		new NioClient("NioClient");
	}
}
