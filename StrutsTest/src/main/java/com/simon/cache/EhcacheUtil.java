package com.simon.cache;

import net.sf.ehcache.CacheManager;

public class EhcacheUtil {

	private EhcacheUtil() {
	}

	private static CacheManager cacheManager = null;

	static {
		cacheManager = CacheManager.create();
	}

	public static CacheManager getCacheManager() {
		return cacheManager;
	}

}
