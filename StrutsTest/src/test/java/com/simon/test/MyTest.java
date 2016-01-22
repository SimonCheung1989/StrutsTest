package com.simon.test;

import org.junit.Test;

public class MyTest {

	@Test
	public void test(){
		String str1 = "abc";
		String str2 = "cde";
		tt(str1, str2);
		System.out.println(str1);
		
	}
	
	public static void tt(String str1, String str2){
		str1 = str2;
	}
}
