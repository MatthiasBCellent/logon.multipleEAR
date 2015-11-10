package de.cellent.test.barService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;

public class BarClientTest {

	private static BarClient client;
	
	public static void main(String[] args) {
		BarClientTest.init();
		BarClientTest test = new BarClientTest();
		
		test.testAccessBar();
	}
	
	public void testAccessBar() {
		System.out.println(client.accessBar("FooBazzzz"));
	}
	
	@BeforeClass
	public static void init() {
		try {
			Context ctx = new InitialContext();
			client = (BarClient) ctx.lookup("ejb:barClient_ear-0.0.1-SNAPSHOT/barClient/BarClientBean!de.cellent.test.barService.BarClient");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
