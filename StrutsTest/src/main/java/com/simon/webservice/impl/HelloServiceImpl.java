package com.simon.webservice.impl;

import com.simon.webservice.IHelloService;

public class HelloServiceImpl implements IHelloService {
	public String sayHello(String name) {
		return "Hello, " + name;
	}
}
