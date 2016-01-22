package com.simon.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.simon.gdgs.tzgg.vo.TzggVO;
import com.simon.jdbc.OracleTools;
import com.simon.service.ISearchService;
import com.simon.solrj.SolrJBase;
import com.simon.util.CommonConstants;

public class SearchServiceImpl implements ISearchService{
	private static Logger logger = Logger.getLogger(Logger.class);

	@Override
	public List search(String keywords) {
		List<TzggVO> list = new ArrayList();
		if(keywords==null || "".equals(keywords)){
			keywords = "*";
		}
		keywords = keywords.replaceAll("\\s*", "");
		SolrJBase solrJBase = new SolrJBase(CommonConstants.SOLR_URL);
		SolrQuery query = new SolrQuery();
		query.setQuery("tzgg:"+keywords);
		query.setFields("id", "tzgg_ggbt", "tzgg_ggnr", "tzgg_ggly", "tzgg_ggrq", "tzgg_ggzz");
		query.setSort("id", ORDER.asc);
		query.setStart(0);
		query.setRows(100);
		query.setHighlight(true);
		query.setHighlightSimplePre("<highlighting>");
		query.setHighlightSimplePost("</highlighting>");
		query.setParam("hl.fl", "tzgg_ggbt,tzgg_ggnr");
		
		QueryResponse response = solrJBase.queryBeanDocs(query);
		
		list = response.getBeans(TzggVO.class);
		
		if(!"*".equals(keywords)){
			Map<String, Map<String, List<String>>> map = response.getHighlighting();
			for(TzggVO vo : list){
				Map<String, List<String>> innerMap = map.get(vo.getUuid());
				if(innerMap!=null){
					if(innerMap.get("tzgg_ggbt")!=null){
						vo.setGgbt(innerMap.get("tzgg_ggbt").get(0));
					}
					if(innerMap.get("tzgg_ggnr")!=null){
						vo.setGgnr(innerMap.get("tzgg_ggnr").get(0));
					}
				}
			}
		}
		
		return list;
	}

	@Override
	public TzggVO getDetail(String uuid) {
		TzggVO tzggVO = null;
		OracleTools oracleTools = new OracleTools("demo", "demo");
		ResultSet resultSet = oracleTools.selectSql("select * from ws_tzgg where uuid='"+uuid+"'");
		try {
			if(resultSet!=null && resultSet.next()){
				try {
					tzggVO = new TzggVO();
					tzggVO.setUuid(uuid);
					tzggVO.setGgbt(resultSet.getString("ggbt"));
					tzggVO.setGgly(resultSet.getString("ggly"));
					tzggVO.setGgrq(resultSet.getString("ggrq"));
					tzggVO.setGgzz(resultSet.getString("ggzz"));
					tzggVO.setGgnr(resultSet.getString("ggnr"));
					tzggVO.setGgfj(resultSet.getString("ggfj"));
					tzggVO.setGglj(resultSet.getString("gglj"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tzggVO;
	}

}
