package com.simon.test;

import java.lang.reflect.Method;

public class MethodReflect {

	public static void main(String[] args) throws Exception {
		String str="1111";
		System.out.println(!(str).equals("aaa"));
	}
	
	public void t(String str){
		System.out.println(str);
	}
}
