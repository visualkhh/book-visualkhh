package kr.or.javacafe.pool;

import java.util.ArrayList;

import kr.or.javacafe.AdvancedNioServer;

/**
 * @(#)ThreadPool.java
 * 
 * @author <a href="mailto:johnleen@hanmail.net">Song Ji-Hoon.</a>
 * @version 1.10, 02/10/21
 * 
 * ThreadPool 클래스는 Thread 를 상속하는 WorkerThread 클래스를 큐에 넣고 재사용한다.
 */
public class ThreadPool {

	private static final int MAX_POOLSIZE = 15;	
	private static int poolSize = 5;

	private final ArrayList queue = new ArrayList();
	
	private boolean wait = false;
	private int total = 0;
	private int index = 0;
	
	private AdvancedNioServer server;
	
	public ThreadPool(AdvancedNioServer server) { 
		this(poolSize, server); 
		this.server = server;
	}

	public ThreadPool(int size, AdvancedNioServer server) {
		poolSize = size;
		
    		
		for (index = 0; index < poolSize; index++) {
			WorkerThread thread = new WorkerThread(this, server);
			thread.setName("Worker" + (index + 1));
			thread.start();
			queue.add(thread);
			total++;
		}
	}
	
	public WorkerThread getThread() {
		WorkerThread worker = null;

		if (queue.size() > 0) {
			synchronized (queue) {    
				worker = (WorkerThread) queue.remove(0);
			}
		} else {
			if (wait) {
				return waitQueue();		
			} else {
				if (index < MAX_POOLSIZE) {
					worker = new WorkerThread(this, server);
					worker.setName("Worker" + (index + 1));
					worker.start();	
					total++;
					return worker;
				} else {
					return waitQueue();
				}
			}	
		}
		
		System.out.println("********  Total Thread : " + total + "  *********");
		System.out.println("********  Remain Thread : " + queue.size() + "  *********");
		
		return worker;
	}

	private synchronized WorkerThread waitQueue() {
		while (queue.isEmpty()) {
			try {
                queue.wait();
            } catch (InterruptedException ignored) {
			}
		}
        return (WorkerThread) queue.remove(0);
	}

	public void putThread(WorkerThread thread) {
		if (queue.size() >= poolSize) {
			thread = null;
			--index;
		} else {
			synchronized (queue) {
				queue.add(thread);
				queue.notify();
			}
		}
	}
	
	public boolean isWait() { return wait; }
	public void setWait(boolean wait) { this.wait = wait; }	
}
