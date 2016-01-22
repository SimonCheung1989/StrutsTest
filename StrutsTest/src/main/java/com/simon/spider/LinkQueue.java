package com.simon.spider;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author zhangww
 * @date   2015-10-9
 * @description
 * 爬行队列Bean
 */
public class LinkQueue {

	private Set<UrlVO> visitedUrl = new HashSet<UrlVO>();

	private Queue unVisitedUrl = new Queue();

	public Queue getUnVisitedUrl() {
		return unVisitedUrl;
	}

	public void addVisitedUrl(UrlVO urlVO) {
		visitedUrl.add(urlVO);
	}

	public void removeVisitedUrl(UrlVO urlVO) {
		visitedUrl.remove(urlVO);
	}

	public Object unVisitedUrlDeQueue() {
		return unVisitedUrl.deQueue();
	}

	public void addUnvisitedUrl(UrlVO urlVO) {
		if (urlVO != null && !visitedUrl.contains(urlVO)
				&& !unVisitedUrl.contains(urlVO)) {
			unVisitedUrl.enQueue(urlVO);
		}
	}

	public int getVisitedUrlNum() {
		return visitedUrl.size();
	}

	public boolean unVisitedUrlsEmpty() {
		return unVisitedUrl.empty();
	}
}
