package net.daum.javacafe.pool.thread.processor;

import java.nio.channels.SocketChannel;

import net.daum.javacafe.event.Job;
import net.daum.javacafe.event.NIOEvent;
import net.daum.javacafe.pool.PoolManager;
import net.daum.javacafe.pool.selector.handler.HandlerAdaptor;
import net.daum.javacafe.queue.Queue;

public class AcceptProcessor extends Thread {
	
	private Queue queue = null;
	
	public AcceptProcessor(Queue queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				Job job = queue.pop(NIOEvent.ACCEPT_EVENT);
				SocketChannel sc = (SocketChannel) job.getSession().get("SocketChannel");
				sc.configureBlocking(false);
				HandlerAdaptor handler = (HandlerAdaptor) PoolManager.getRequestSelectorPool().get();
				handler.addClient(sc);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

}
