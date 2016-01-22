package com.simon.swsxtzs;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;

/**
 * 
 * <p>Company: 税友软件集团有限公司</p>
 * <p>Copyright: Copyright(c) 2009-2018</p>
 * 
 * @author	zhangww
 * @date 	2015年8月12日
 * @version 1.0
 * @description
 * XML节点处理抽象类
 */
public abstract class XmlNodeTransformer {
	private Map<String, Object> map = new HashMap<String, Object>();
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public abstract void transformNode(Document document); 
}
