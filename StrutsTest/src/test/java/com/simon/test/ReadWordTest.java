package com.simon.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.junit.Test;

import com.simon.jdbc.OracleTools;

public class ReadWordTest {
	private static final String TITLE_3_PARTTEN = "(\\d+)\\.(\\d+)\\.(\\d+)—(\\d{3})(.+)";
	private static final String BT = "【(.+)】";
	
	public void test() throws Exception{
		ReadWordTest test = new ReadWordTest();
		test.readDoc("D:\\工作记录\\15.珠海工作记录\\8.办税指南\\2.2完整版.doc");
	}
	
	public void readDoc(String filePath) throws Exception{
		Set<String> set = new HashSet<String>();
		List<String> list = new ArrayList<String>();
		OracleTools oracleTools = new OracleTools("demo", "demo");
		oracleTools.createConnection();
		Pattern p3, p2, p1, bt;
		p3 = Pattern.compile(TITLE_3_PARTTEN);
		bt = Pattern.compile(BT);
		InputStream is = new FileInputStream(filePath);
		HWPFDocument doc = new HWPFDocument(is);
		Range range = doc.getRange();
		int paraNum = range.numParagraphs();
		String str = "";
		String preTitle = "";
		String title = "";
		String third_bt = "";
		String cd_id = "";
		StringBuilder sb = new StringBuilder();
		boolean isStart = false;
		for (int i = 0; i < paraNum; i++) {
			str = range.getParagraph(i).text();
			Matcher m = p3.matcher(str);
			if(m.find()){
				title = m.group(4);
				third_bt = m.group(5);
				cd_id = String.format("%02d", Integer.parseInt(m.group(1)))+String.format("%02d", Integer.parseInt(m.group(2)))+String.format("%03d", Integer.parseInt(m.group(3)));
				if(!set.contains(title)){
					isStart = true;
					set.add(title);
					if(list.size()!=0){
						System.out.println(list.get(list.size()-1));
						System.out.println(sb.toString());
						
					}
					sb = new StringBuilder();
					sb.append("<p style=\"text-align:center\"><strong><span style=\"font-size:24px\">"+third_bt+"</span></strong></p>");
					list.add(cd_id);
				}else{
					if(isStart){
						sb.append("<p>"+str+"</p>");
					}
				}
			}else{
				if(isStart){
					Matcher m_bt = bt.matcher(str);
					if(m_bt.find()){
						if(str.contains("基本流程")){
							sb.append("<p><span style=\"font-size:14px\"><strong>"+str+"</strong></span></p>");
							sb.append("<p><img alt=\"\" src=\"/ZhBsfwtWeb/images/bszn_png/"+list.get(list.size()-1)+"01.png\" style=\"height:425px; width:440px\" /></p>");
							sb.append("<p>&nbsp;</p>");
							sb.append("<p><span style=\"font-size:14px\"><strong>【表单下载】</strong></span></p>");
							sb.append("<p>&nbsp;</p>");
							sb.append("<p><span style=\"font-size:14px\"><strong>【办理规范】</strong></span></p>");
							isStart = false;
						}else{
							sb.append("<p><span style=\"font-size:14px\"><strong>"+str+"</strong></span></p>");
						}
					}else{
						sb.append("<p>"+str+"</p>");
					}
				}
			}
		}
		if(list.size()!=0){
			System.out.println(list.get(list.size()-1));
			System.out.println(sb.toString());
		}
	}
}
