package com.simon.spider;

import java.util.LinkedList;

/**
 * 
 * @author zhangww
 * @date   2015-10-9
 * @description
 * 待爬行队列
 */
public class Queue {

	private LinkedList<Object> queue = new LinkedList<Object>();

	public void enQueue(Object t) {
		queue.add(t);
	}

	public Object deQueue() {
		return queue.removeFirst();
	}

	public boolean contains(Object t) {
		return queue.contains(t);
	}

	public boolean empty() {
		return queue.isEmpty();
	}
}
