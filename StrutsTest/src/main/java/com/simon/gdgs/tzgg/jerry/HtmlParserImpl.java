package com.simon.gdgs.tzgg.jerry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import jodd.jerry.Jerry;
import jodd.lagarto.dom.Node;
import jodd.lagarto.dom.Text;

import org.apache.log4j.Logger;
import com.simon.gdgs.tzgg.vo.TzggVO;
import com.simon.jdbc.OracleTools;
import com.simon.solrj.SolrJBase;
import com.simon.spider.IHtmlParser;
import com.simon.spider.SpiderTools;
import com.simon.util.CommonConstants;

public class HtmlParserImpl implements IHtmlParser {
	private static Logger logger = Logger.getLogger(HtmlParserImpl.class);

	@Override
	public void parseHtmlContent(String url, String htmlContent) {
		String ggbt = "", ggrq = "", ggly = "", ggzz = "", ggfj = "";
		Jerry doc = Jerry.jerry(htmlContent);
		// 标题
		ggbt = doc.$(".contenttitle").find("h1").html();
		// 公告信息；日期，来源，作者
		Jerry j = doc.$(".contentdata").find("span");
		Node[] nodes = j.get();

		for (Node node : nodes) {
			if (node.getTextContent() != null) {
				if (node.getTextContent().contains("日期")) {
					ggrq = node.getTextContent();
				} else if (node.getTextContent().contains("来源")) {
					ggly = node.getTextContent();
				} else if (node.getTextContent().contains("作者")) {
					ggzz = node.getTextContent();
				}
			}
		}
		// 获取文档内容p节点
		j = doc.$("#contentpp").find("p");
		nodes = j.get();
		StringBuilder ggnr = new StringBuilder();
		for (int i = 0, len = nodes.length; i < len; i++) {
			ggnr.append(nodes[i].getTextContent().trim());
		}
		// 如果没有获取到文档p节点，直接获取文档div内容
		if (j == null || nodes == null || nodes.length == 0) {
			String contentpp = doc.$("#contentpp").text();
			ggnr.append(contentpp);
		}
		// 公告附件
		j = doc.$(".relnews");
		if (j != null && j.html() != null && !"".equals(j.html())) {
			Jerry jerry_a = j.find("a");
			nodes = jerry_a.get();
			for(Node node : nodes){
				node.setAttribute("href", SpiderTools.getAbsoluteUrl(url, node.getAttribute("href")));
			}
			ggfj = j.htmlAll(true);
		}
		
		// 公告附件
		j = doc.$("a[oldsrc]");
		nodes = j.get();
		if (nodes.length>0) {
			for(Node node : nodes){
				node.setAttribute("href", SpiderTools.getAbsoluteUrl(url, node.getAttribute("href")));
				ggfj += node.getHtml()+"<br>";
			}
		}

		logger.debug("公告标题：" + ggbt);
		logger.debug("公告日期：" + ggrq);
		logger.debug("公告来源：" + ggly);
		logger.debug("公告作者：" + ggzz);
		logger.debug("公告内容：" + ggnr.toString());
		logger.debug("公告附件：" + ggfj);

		if (ggbt != null && !"".equals(ggbt)) {
			String uuid = UUID.randomUUID().toString();
			int gghashcode = ggnr.toString().hashCode();

			logger.debug("插入表ws_tzgg");
			OracleTools oracleTools = new OracleTools("demo", "demo");
			int res = oracleTools
					.insert2("insert into ws_tzgg(uuid, ggbt, ggrq, ggly, ggzz, ggnr, ggfj, gglj, gghashcode) values('"
							+ uuid
							+ "','"
							+ ggbt
							+ "','"
							+ ggrq
							+ "','"
							+ ggly
							+ "','"
							+ ggzz
							+ "',empty_clob(), '"
							+ ggfj + "', '"+url+"', "+gghashcode+")", uuid, ggnr.toString());
			
			if(res>0){
				logger.debug("建立索引");
				Collection<TzggVO> docs = new ArrayList<TzggVO>();
				TzggVO tzggVO = new TzggVO();
				tzggVO.setUuid(uuid);
				tzggVO.setGgbt(ggbt);
				tzggVO.setGgrq(ggrq);
				tzggVO.setGgly(ggly);
				tzggVO.setGgzz(ggzz);
				tzggVO.setGgnr(ggnr.toString());
				docs.add(tzggVO);
				SolrJBase solrBase = new SolrJBase(CommonConstants.SOLR_URL);
				solrBase.addBeanDocs(docs.iterator());
			}
		}
	}

}
