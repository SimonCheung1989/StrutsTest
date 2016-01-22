package com.simon.spider;

/**
 * 
 * @author zhangww
 * @date   2015-10-9
 * @description
 * URL过滤接口
 */
public interface ILinkFilter {
	public boolean accept(UrlVO urlVO);
}
