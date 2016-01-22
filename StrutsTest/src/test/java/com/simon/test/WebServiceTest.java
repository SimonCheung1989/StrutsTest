package com.simon.test;

import javax.xml.ws.Endpoint;

import com.simon.webservice.impl.TestServiceImpl;

public class WebServiceTest {
	
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			 System.out.println("web service start");
	         TestServiceImpl implementor = new TestServiceImpl();
	         String address = "http://localhost:8880/testService";
	         Endpoint.publish(address, implementor);
	         System.out.println("web service started");
		}



}
