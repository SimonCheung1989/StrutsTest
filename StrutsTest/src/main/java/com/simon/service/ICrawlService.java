package com.simon.service;

/**
 * 索引服务
 * @author Administrator
 *
 */
public interface ICrawlService {

	void startCrawl(String deepth, String seeds);

	void suspendCrawl(String deepth, String seeds);

	void resumeCrawl(String deepth, String seeds);

	void stopCrawl(String deepth, String seeds);
}
