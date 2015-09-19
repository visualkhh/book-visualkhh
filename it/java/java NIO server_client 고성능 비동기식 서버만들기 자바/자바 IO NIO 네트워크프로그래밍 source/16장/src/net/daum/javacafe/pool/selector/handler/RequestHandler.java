package net.daum.javacafe.pool.selector.handler;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import net.daum.javacafe.event.Job;
import net.daum.javacafe.event.NIOEvent;
import net.daum.javacafe.queue.ChattingRoom;
import net.daum.javacafe.queue.Queue;

public class RequestHandler extends HandlerAdaptor {

	private Queue queue = null;
	private Selector selector = null;
	private String name = "RequestHandler-";
	
	private Vector newClients = new Vector();
	
	public RequestHandler(Queue queue, Selector selector, int index) {
		this.queue = queue;
		this.selector = selector;
		setName(name + index);
	}

	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				processNewConnection();
				int keysReady = selector.select(1000);	
				System.out.println("@RequestHandler(" + getName() + ") selected : " + keysReady );	
				if (keysReady > 0) {
					processRequest();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void processNewConnection() throws ClosedChannelException {
		Iterator iter = newClients.iterator();
		while (iter.hasNext()) {
			SocketChannel sc = (SocketChannel) iter.next();
			sc.register(selector, SelectionKey.OP_READ);
			ChattingRoom.getInstance().add(sc);
			System.out.println("@RequestHandler(" + getName() + ") success regist : " + sc.toString() );
		}
		newClients.clear();
	}
	
	private void processRequest() {
		Iterator iter = selector.selectedKeys().iterator();	
		while (iter.hasNext()) {
			SelectionKey key = (SelectionKey) iter.next();
			iter.remove();			
			pushMyJob(key);
		}
	}
	
	private void pushMyJob(SelectionKey key) {
		Map session = new HashMap();
		session.put("SelectionKey", key);
		Job job = new Job(NIOEvent.READ_EVENT, session);			
		queue.push(job);
	}

	public void addClient(SocketChannel sc) {
		newClients.add(sc);
	}

}
