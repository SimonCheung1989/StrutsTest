package com.simon.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface TestService {
	String sayHello(@WebParam(name = "text")String name);
}
