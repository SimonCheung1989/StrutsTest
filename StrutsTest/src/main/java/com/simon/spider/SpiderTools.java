package com.simon.spider;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import jodd.io.NetUtil;
import jodd.jerry.Jerry;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * 
 * @author zhangww
 * @date   2015-10-9
 * @description
 * html解析工具类
 */
public class SpiderTools {

	/**
	 * 爬去页面所有连接
	 * @param urlVO
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("serial")
	public static Set<UrlVO> extracLinks(UrlVO urlVO, ILinkFilter filter) {
		Set<UrlVO> links = new HashSet<UrlVO>();
		try {
			Parser parser = new Parser(urlVO.getUrl());
			parser.setEncoding("UTF-8");

			NodeFilter frameFilter = new NodeFilter() {
				@Override
				public boolean accept(Node node) {
					if (node.getText().startsWith("frame src=")) {
						return true;
					}
					return false;
				}
			};

			OrFilter linkFilter = new OrFilter(new NodeClassFilter(
					LinkTag.class), frameFilter);
			NodeList list = parser.extractAllNodesThatMatch(linkFilter);
			for (int i = 0; i < list.size(); i++) {
				Node tag = list.elementAt(i);
				if (tag instanceof LinkTag) {
					LinkTag link = (LinkTag) tag;
					String linkUrl = link.getLink();
					UrlVO linkUrlVO = new UrlVO();
					linkUrlVO.setUrl(linkUrl);
					linkUrlVO.setLevel(urlVO.getLevel() + 1);
					if (filter.accept(linkUrlVO)) {
						links.add(linkUrlVO);
					} else {
						String frame = tag.getText();
						int start = frame.indexOf("src=");
						if (start != -1) {
							frame = frame.substring(start);
						}
						int end = frame.indexOf(" ");
						String frameUrl = "";
						if (end == -1) {
							end = frame.indexOf(">"); 
							if (end - 1 > 5) {
								frameUrl = frame.substring(5, end - 1);
							}
						}

						UrlVO frameUrlVO = new UrlVO();
						frameUrlVO.setLevel(urlVO.getLevel() + 1);
						frameUrlVO.setUrl(frameUrl);

						if (filter.accept(frameUrlVO)) {
							links.add(frameUrlVO);
						}

					}
				}
			}

		} catch (ParserException e) {
			e.printStackTrace();
		}
		return links;
	}
	
	/**
	 * 通过Jerry获取页面所有连接
	 * @param urlVO
	 * @param filter
	 * @return
	 */
	public static Set<UrlVO> extracLinks2(UrlVO urlVO, ILinkFilter filter) {
		Set<UrlVO> links = new HashSet<UrlVO>();
		String htmlContent = "";
		try {
			htmlContent = NetUtil.downloadString(urlVO.getUrl());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Jerry doc = Jerry.jerry(htmlContent);
		Jerry newslist = doc.$(".newslist").find("a[href]");
		for (jodd.lagarto.dom.Node node : newslist.get()) {
			String linkUrl = node.getAttribute("href");
			if(linkUrl!=null && !"".equals(linkUrl)){
				UrlVO linkUrlVO = new UrlVO();
				linkUrlVO.setUrl(getAbsoluteUrl(urlVO.getUrl(), linkUrl));
				linkUrlVO.setLevel(urlVO.getLevel() + 1);
				if (filter.accept(linkUrlVO)) {
					links.add(linkUrlVO);
				}
			}
		}
		
		return links;
	}
	
	/**
	 * 根据网页URL以及连接地址的相对路径获取连接绝对路径
	 * @param htmlUrl
	 * @param hrefUrl
	 * @return
	 */
	public static String getAbsoluteUrl(String htmlUrl, String hrefUrl){
		if(htmlUrl==null || "".equals(htmlUrl)){
			return htmlUrl;
		}
		htmlUrl = htmlUrl.substring(0, htmlUrl.lastIndexOf("/")+1);
		htmlUrl += hrefUrl;
		return htmlUrl;
	}
	
	/**
	 * 根据网页URL连接获取文件名称
	 * @param hrefUrl
	 * @return
	 */
	public static String getFileName(String hrefUrl){
		if(hrefUrl==null || "".equals(hrefUrl)){
			return hrefUrl;
		}
		hrefUrl = hrefUrl.substring(hrefUrl.lastIndexOf("/")+1);
		
		return hrefUrl;
	}

}
