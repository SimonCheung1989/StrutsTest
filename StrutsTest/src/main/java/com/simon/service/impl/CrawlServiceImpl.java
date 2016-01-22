package com.simon.service.impl;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.simon.cache.EhcacheUtil;
import com.simon.gdgs.tzgg.jerry.HtmlParserImpl;
import com.simon.service.ICrawlService;
import com.simon.spider.IHtmlParser;
import com.simon.spider.ILinkFilter;
import com.simon.spider.Spider;
import com.simon.spider.UrlVO;

public class CrawlServiceImpl implements ICrawlService{

	@Override
	public void startCrawl(String deepth, String seeds) {
		final int seedsHascode = seeds.hashCode();
		CacheManager cacheManager = EhcacheUtil.getCacheManager();   
        //用配置文件中配置的colorcache创建cache缓存   
        final Cache cache = cacheManager.getCache("data-cache");   
        //查看cache中是否存在allColor的缓存   
        Element element = cache.get(seedsHascode);
        if(element!=null){
        	return;
        }
		final String seedsArray[] = seeds.split("\n");
		final Spider spider = new Spider();
		final int maxDeepth = Integer.parseInt(deepth);
		//url过滤器
		final ILinkFilter filter = new ILinkFilter() {
			@Override
			public boolean accept(UrlVO urlVO) {
				//只爬行该站点，并且爬行深度不超过最大值
				if (urlVO.getUrl().startsWith(
						"http://portal.gd-n-tax.gov.cn/pub/gdgsww/bsfw/tzgg/")
						&& urlVO.getLevel() < maxDeepth) {
					return true;
				}
				return false;
			}
		};
		//html解析实现类（具体业务具体实现）
		final IHtmlParser htmlParser = new HtmlParserImpl();
		
		Thread thread = new Thread(){
			@Override
			public void run() {
				try {
					spider.crawling(seedsArray, filter, htmlParser);
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
					cache.remove(seedsHascode);
				}
			}
		};
		thread.start();
        cache.put(new Element(seedsHascode, thread));
	}

	@Override
	public void suspendCrawl(String deepth, String seeds) {
		final int seedsHascode = seeds.hashCode();
		CacheManager cacheManager = EhcacheUtil.getCacheManager();   
        //用配置文件中配置的colorcache创建cache缓存   
        final Cache cache = cacheManager.getCache("data-cache");   
        //查看cache中是否存在allColor的缓存   
        Element element = cache.get(seedsHascode);
        if(element!=null){
        	Thread thread = (Thread) element.getObjectValue();
        	thread.suspend();
        }
	}

	@Override
	public void resumeCrawl(String deepth, String seeds) {
		final int seedsHascode = seeds.hashCode();
		CacheManager cacheManager = EhcacheUtil.getCacheManager();   
        //用配置文件中配置的colorcache创建cache缓存   
        final Cache cache = cacheManager.getCache("data-cache");   
        //查看cache中是否存在allColor的缓存   
        Element element = cache.get(seedsHascode);
        if(element!=null){
        	Thread thread = (Thread) element.getObjectValue();
        	thread.resume();
        }
	}

	@Override
	public void stopCrawl(String deepth, String seeds) {
		final int seedsHascode = seeds.hashCode();
		CacheManager cacheManager = EhcacheUtil.getCacheManager();   
        //用配置文件中配置的colorcache创建cache缓存   
        final Cache cache = cacheManager.getCache("data-cache");   
        //查看cache中是否存在allColor的缓存   
        Element element = cache.get(seedsHascode);
        if(element!=null){
        	Thread thread = (Thread) element.getObjectValue();
        	thread.interrupt();
        }
	}

}
