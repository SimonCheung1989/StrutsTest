package com.simon.webservice.impl;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.simon.webservice.TestService;

@WebService(endpointInterface="com.simon.webservice.TestService",serviceName="TestService") 
public class TestServiceImpl implements TestService {
	@Override
	public String sayHello(@WebParam(name = "text") String name) {
		return name + " hello!";
	}
}
