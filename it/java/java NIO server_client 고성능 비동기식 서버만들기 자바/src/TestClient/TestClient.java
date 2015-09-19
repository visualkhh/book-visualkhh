import java.io.*;
import java.net.*;

public class TestClient implements Runnable {

	private Socket socket;
	private OutputStream out;
	private InputStream in;

	public TestClient(String host, int port) {
		int result = 0;
		try {
			socket = new Socket(host, port);

			in = socket.getInputStream();
			out = socket.getOutputStream();

			new Thread(this).start();

			while ((result = sendMessage()) != -1){}
		} catch(IOException ie) {
		}
	}

	private int sendMessage() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String message = null;
		int nBytes = 0;
		try {		
			message = in.readLine();
			out.write(message.getBytes());

			if (message.equals("quit") || message.equals("shutdown")) {					
				out.close();
				return -1;
			}
		} catch(IOException ie) { 
        }
		return nBytes;
	}

	public void run() {
		try {
			byte buffer[] = new byte[4096];
			
			while (true) {
				int r = in.read(buffer);
				String message = new String(buffer, 0, r);

				System.out.println(message);
			}
		} catch(IOException ie) {
		}
	}

	public static void main(String[] args) {
		if (args.length < 2) 
			System.out.println("java TestClient [host] [port]");

		new TestClient(args[0], Integer.parseInt(args[1]));
	}
}
