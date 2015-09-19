package net.daum.javacafe.queue;

import net.daum.javacafe.event.Job;

public interface Queue {
	
	public Job pop(int eventType);
	public void push(Job job);

}
