package com.simon.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Test {
	
	public static void main(String[] args){
		VOTest vo1 = new VOTest();
		vo1.setName("simon1");
		vo1.setType("GZ");
		vo1.setValue("100");
		
		VOTest vo2 = new VOTest();
		vo2.setName("simon1");
		vo2.setType("JJ");
		vo2.setValue("100");
		
		VOTest vo3 = new VOTest();
		vo3.setName("simon2");
		vo3.setType("GZ");
		vo3.setValue("100");
		
		VOTest vo4 = new VOTest();
		vo4.setName("simon3");
		vo4.setType("GZ");
		vo4.setValue("100");
		
		VOTest vo5 = new VOTest();
		vo5.setName("simon3");
		vo5.setType("JJ");
		vo5.setValue("100");
		
		VOTest vo6 = new VOTest();
		vo6.setName("simon4");
		vo6.setType("GZ");
		vo6.setValue("100");
		
		VOTest vo7 = new VOTest();
		vo7.setName("simon5");
		vo7.setType("GZ");
		vo7.setValue("100");
		
		
		List<VOTest> list = new ArrayList<VOTest>();
		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		list.add(vo4);
		list.add(vo5);
		list.add(vo6);
		list.add(vo7);
		
		Set<String> jjSet = new HashSet<String>();
		Set<String> gzSet = new HashSet<String>();
		Map<String, VOTest> map = new HashMap<String, VOTest>();
		
		for(VOTest vo : list){
			map.put(vo.getName(), vo);
			if("GZ".equals(vo.getType())){
				gzSet.add(vo.getName());
			}else if("JJ".equals(vo.getType())){
				jjSet.add(vo.getName());
			}
		}
		
		gzSet.removeAll(jjSet);
		for(String str : gzSet){
			System.out.println(str);
		}
		
	}

}
