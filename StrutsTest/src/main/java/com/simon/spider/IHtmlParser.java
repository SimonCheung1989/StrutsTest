package com.simon.spider;

/**
 * 
 * @author zhangww
 * @date   2015-10-9
 * @description
 * html解析接口
 */
public interface IHtmlParser {
	
	/**
	 * 解析HTML文件内容获取需要的信息
	 * @param url
	 * @param htmlContent
	 */
	public void parseHtmlContent(String url, String htmlContent);
}
