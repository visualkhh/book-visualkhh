package net.daum.javacafe.server;

import java.io.File;
import java.io.IOException;

import net.daum.javacafe.pool.PoolManager;
import net.daum.javacafe.pool.buffer.ByteBufferPool;
import net.daum.javacafe.pool.buffer.ByteBufferPoolIF;
import net.daum.javacafe.pool.selector.AcceptSelectorPool;
import net.daum.javacafe.pool.selector.RequestSelectorPool;
import net.daum.javacafe.pool.selector.SelectorPoolIF;
import net.daum.javacafe.pool.thread.ThreadPool;
import net.daum.javacafe.pool.thread.ThreadPoolIF;
import net.daum.javacafe.queue.BlockingEventQueue;
import net.daum.javacafe.queue.Queue;


public class AdvancedChatServer {
	
	private Queue queue = null;
	private SelectorPoolIF acceptSelectorPool = null;
	private SelectorPoolIF requestSelectorPool = null;
	
	private ByteBufferPoolIF byteBufferPool = null;
	
	ThreadPoolIF acceptThreadPool = null;
	ThreadPoolIF readWriteThreadPool = null;
	
	public AdvancedChatServer() {
		try {
			initResource();
			startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initResource() throws IOException {
		// ByteBufferPool 积己..
		File bufferFile = new File("C:/Buffer.tmp");
		if (!bufferFile.exists()) {
			bufferFile.createNewFile();
		}
		bufferFile.deleteOnExit();
		byteBufferPool = new ByteBufferPool(20*1024, 40*2048, bufferFile);
		
		// Queue 积己..
		queue = BlockingEventQueue.getInstance();		
		
		// PoolManager 俊 ByteBufferPool 殿废..
		PoolManager.registByteBufferPool(byteBufferPool);
		
		// ThreadPool 积己..
		acceptThreadPool = new ThreadPool(
				queue, "net.daum.javacafe.pool.thread.processor.AcceptProcessor");
		readWriteThreadPool = new ThreadPool(
				queue, "net.daum.javacafe.pool.thread.processor.ReadWriteProcessor");

		// SelectorPool 积己..
		acceptSelectorPool = new AcceptSelectorPool(queue);
		requestSelectorPool = new RequestSelectorPool(queue);
		
		// PoolManager 俊 SelectorPool 殿废..
		PoolManager.registAcceptSelectorPool(acceptSelectorPool);
		PoolManager.registRequestSelectorPool(requestSelectorPool);	
	}
	
	private void startServer() {
		acceptThreadPool.startAll();
		readWriteThreadPool.startAll();
		
		acceptSelectorPool.startAll();
		requestSelectorPool.startAll();
	}

	public static void main(String[] args) {
		AdvancedChatServer server = new AdvancedChatServer();
	}
	
}
