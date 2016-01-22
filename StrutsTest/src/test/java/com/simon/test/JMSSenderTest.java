package com.simon.test;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class JMSSenderTest {
	/**
	 * @param args
	 * @throws NamingException 
	 * @throws JMSException 
	 */
	public static void main(String[] args) throws NamingException, JMSException {
		//init JNDI context
		String JNDIFactory = "weblogic.jndi.WLInitialContextFactory";//define JNDI context factory
		String providerUrl = "t3://localhost:7201"; //define weblogic JMS url
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, JNDIFactory);
		env.put(Context.PROVIDER_URL, providerUrl);
		Context ctx = new InitialContext(env);
		
		//find connection factory
		String connFactoryJNDI = "jms/connectionFactory"; //jms connectionFactory JNDI name
		QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.lookup(connFactoryJNDI);
		//create queue connection
		QueueConnection qConn = (QueueConnection) connFactory.createConnection();
		//create session
		QueueSession qSession = qConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		//find queue by JNDI lookup
		Queue queue = (Queue) ctx.lookup("jms/queue");
		//create sender
		QueueSender qSender = qSession.createSender(queue);
		//create message
		Message msg = qSession.createTextMessage("helloSimon Message is from JMS Sender!");
		qSender.send(msg);
		qSender.close();
		qSession.close();
		qConn.close();
		
	}

}
