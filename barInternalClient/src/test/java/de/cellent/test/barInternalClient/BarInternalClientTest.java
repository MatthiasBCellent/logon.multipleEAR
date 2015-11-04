package de.cellent.test.barInternalClient;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BarInternalClientTest {

	private static BarInternalClient service;
	
	public static void main(String[] args) {
		BarInternalClientTest.setUp();
		
		BarInternalClientTest test = new BarInternalClientTest();
		test.testDoBar();
	}
	
	public void testDoBar() {
		System.out.println(service.doBar("do Bar"));
	}
	
	public static void setUp() {
		try {
			InitialContext ctx = new InitialContext();
			service = (BarInternalClient) ctx.lookup("ejb:barService_ear-0.0.1-SNAPSHOT/barInternalClient/BarInternalClientBean!de.cellent.test.barInternalClient.BarInternalClient");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
