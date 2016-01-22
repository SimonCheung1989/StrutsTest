package com.simon.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.simon.service.ICrawlService;
import com.simon.service.impl.CrawlServiceImpl;

@SuppressWarnings("serial")
public class CrawlAction extends ActionSupport {
	
	private static Logger logger = Logger.getLogger(CrawlAction.class);

	private Map<String, Object> dataMap = new HashMap<String, Object>();
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	private String deepth;
	
	private String seeds;

	public String getDeepth() {
		return deepth;
	}

	public void setDeepth(String deepth) {
		this.deepth = deepth;
	}

	public String getSeeds() {
		return seeds;
	}

	public void setSeeds(String seeds) {
		this.seeds = seeds;
	}

	
	private ICrawlService iCrawlService = new CrawlServiceImpl();
	
	/**
	 * 开始爬行
	 * @return
	 */
	public String startCrawl(){
		logger.debug("startCrawl");
		iCrawlService.startCrawl(deepth, seeds);
		return SUCCESS;
	}
	
	/**
	 * 暂停爬行
	 * @return
	 */
	public String suspendCrawl(){
		iCrawlService.suspendCrawl(deepth, seeds);
		return SUCCESS;
	}
	
	/**
	 * 继续爬行
	 * @return
	 */
	public String resumeCrawl(){
		iCrawlService.resumeCrawl(deepth, seeds);
		return SUCCESS;
	}
	
	/**
	 * 停止抓取
	 * @return
	 */
	public String stopCrawl(){
		iCrawlService.stopCrawl(deepth, seeds);
		return SUCCESS;
	}
}
