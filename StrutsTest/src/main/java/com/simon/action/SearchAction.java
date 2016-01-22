package com.simon.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.simon.gdgs.tzgg.vo.TzggVO;
import com.simon.service.ISearchService;
import com.simon.service.impl.SearchServiceImpl;

@SuppressWarnings("serial")
public class SearchAction extends ActionSupport{
	private static Logger logger = Logger.getLogger(SearchAction.class);
	//检索关键词
	private String keywords;
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	private Map<String, Object> dataMap = new HashMap<String, Object>();

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	private ISearchService iSearchService = new SearchServiceImpl();
	
	public String search() throws Exception{
		logger.debug("----------开始检索------------");
		List list = iSearchService.search(keywords);
		dataMap.put("list", list);
		return SUCCESS;
	}
	
	private String uuid;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDetail() throws Exception{
		logger.debug("----------开始查询详细信息------------");
		TzggVO tzggVO = iSearchService.getDetail(uuid);
		dataMap.put("tzggVO", tzggVO);
		return SUCCESS;
	}
}
