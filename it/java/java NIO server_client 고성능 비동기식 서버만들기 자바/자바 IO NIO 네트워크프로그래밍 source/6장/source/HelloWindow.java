import java.io.Serializable;
import java.awt.event.*;
import java.awt.*;

public class HelloWindow extends Frame implements Serializable{
	
	public HelloWindow(){
		super("Hello Window");
		setLayout(new BorderLayout());
		add("Center", new Label("Hello Window"));
		addWindowListener(new WindowEventHandler());
		setSize(200, 200);
	}
	

	class WindowEventHandler extends WindowAdapter implements Serializable{
		public void windowClosing(WindowEvent e) {
			System.out.println("윈도우를 종료합니다.");
			System.exit(0);
		}
	}
}
