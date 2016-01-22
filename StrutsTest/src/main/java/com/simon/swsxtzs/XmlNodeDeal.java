package com.simon.swsxtzs;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

/**
 * 
 * <p>Company: 税友软件集团有限公司</p>
 * <p>Copyright: Copyright(c) 2009-2018</p>
 * 
 * @author	zhangww
 * @date 	2015年8月12日
 * @version 1.0
 * @description
 * XML节点处理工具类
 */
public class XmlNodeDeal {
	
	public static String dealXml(InputStream input, List<XmlNodeTransformer> list) throws Exception{
		SAXReader reader = new SAXReader();
		Document document = reader.read(input);
		if(list!=null){
			for(XmlNodeTransformer transformer : list){
				transformer.transformNode(document);
			}
		}
		return document.asXML();
	}
	
}
