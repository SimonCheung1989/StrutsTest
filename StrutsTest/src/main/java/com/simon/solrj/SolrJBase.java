package com.simon.solrj;

import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;

/**
 * 
 * @author zhangww
 * @date   2015-10-9
 * @description
 * SolrJBase基础类
 */
public class SolrJBase {
	
	private static Logger logger = Logger.getLogger(SolrJBase.class);
	
	private HttpSolrServer server;
	
	private String solrUrl;
	
	public SolrJBase(String solrUrl){
		this.solrUrl = solrUrl;
	}
	
	private void init() {
		server = new HttpSolrServer(solrUrl);

		server.setMaxRetries(1); 			// defaults to 0. > 1 not recommended.
		server.setConnectionTimeout(5000); 	// 5 seconds to establish TCP
		server.setSoTimeout(1000); 			// socket read timeout
		server.setDefaultMaxConnectionsPerHost(100);
		server.setMaxTotalConnections(100);
		server.setFollowRedirects(false); 	// defaults to false
		server.setAllowCompression(true);
	}

	private void destory() {
		server = null;
		System.runFinalization();
		System.gc();
	}
	
	/**
	 * 添加索引
	 * @param beans
	 */
	public void addBeanDocs(Iterator<?> beans) {
		try {
			init();
			server.addBeans(beans);
			server.commit();
			destory();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.debug("--------------------------");
	}
	
	public QueryResponse queryBeanDocs(SolrQuery query) {
		init();
		server.setMaxRetries(1); 				// defaults to 0. > 1 not recommended.
		server.setConnectionTimeout(5000); 		// 5 seconds to establish TCP
		server.setParser(new XMLResponseParser());
		server.setSoTimeout(3000); 				// socket read timeout
		server.setDefaultMaxConnectionsPerHost(100);
		server.setMaxTotalConnections(100);
		server.setFollowRedirects(false); 		// defaults to false
		server.setAllowCompression(true);

		// 使用SolrQuery传递参数，SolrQuery的封装性更好
		server.setRequestWriter(new BinaryRequestWriter());
		QueryResponse response = null;
		try {
			response = server.query(query);
			// 搜索得到的结果数
			logger.debug("Find:" + response.getResults().getNumFound());
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		destory();
		return response;
	}

}
