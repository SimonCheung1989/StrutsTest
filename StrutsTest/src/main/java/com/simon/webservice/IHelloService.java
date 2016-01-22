package com.simon.webservice;

import javax.jws.WebService;

@WebService
public interface IHelloService {
	public String sayHello(String name);
}
