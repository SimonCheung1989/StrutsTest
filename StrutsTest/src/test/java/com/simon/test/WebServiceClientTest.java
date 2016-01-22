package com.simon.test;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.simon.webservice.IHelloService;
import com.simon.webservice.TestService;

public class WebServiceClientTest {

		/**
		 * @param args
		 */
		public static void main(String[] args) {
			
			//首先右键run as 运行com.hsy.server.webServiceApp类，然后再运行这段客户端代码
			JaxWsProxyFactoryBean jwpfb = new JaxWsProxyFactoryBean();
			jwpfb.setServiceClass(IHelloService.class);
			jwpfb.setAddress("http://localhost:8090/StrutsTest/servlet/XFireServlet/HelloService");
			IHelloService hw = (IHelloService) jwpfb.create();
	        System.out.println(hw.sayHello("Simon"));
	        
	    }

}
