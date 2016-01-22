package com.simon.spider;

import java.io.IOException;
import java.util.Set;

import jodd.io.NetUtil;

public class Spider {
	private LinkQueue linkQueue;
	
	public Spider(){
		linkQueue = new LinkQueue();
	}

	/**
	 * 初始化种子队列
	 * 
	 * @param seeds
	 */
	private void initCrawlerWithSeeds(String[] seeds) {
		for (int i = 0; i < seeds.length; i++) {
			UrlVO urlVO = new UrlVO();
			urlVO.setUrl(seeds[i]);
			urlVO.setLevel(0);
			linkQueue.addUnvisitedUrl(urlVO);
		}
	}

	/**
	 * 开始爬行
	 * 
	 * @param seeds
	 */
	public void crawling(String[] seeds, ILinkFilter filter, IHtmlParser htmlParser) {
		initCrawlerWithSeeds(seeds);

		while (!linkQueue.unVisitedUrlsEmpty()) {
			UrlVO visitUrlVO = (UrlVO) linkQueue.unVisitedUrlDeQueue();
			if (visitUrlVO == null || visitUrlVO.getUrl() == null) {
				continue;
			}
			// 解析url返回内容
			this.parse(visitUrlVO.getUrl(), htmlParser);
			linkQueue.addVisitedUrl(visitUrlVO);

			//解析网页内容中的连接，放入爬行队列
			Set<UrlVO> links = SpiderTools.extracLinks2(visitUrlVO, filter);
			for (UrlVO urlVO : links) {
				linkQueue.addUnvisitedUrl(urlVO);
			}
		}
	}
	
	/**
	 * 内容解析
	 * @param url
	 * @param htmlParser
	 */
	private void parse(String url, IHtmlParser htmlParser) {
		if(htmlParser!=null){
			String htmlContent = "";
			try {
				htmlContent = NetUtil.downloadString(url);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			htmlParser.parseHtmlContent(url, htmlContent);
		}
	}
}
