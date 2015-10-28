package de.cellent.test.barService;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.cellent.test.barService.util.JNDIBrowser;

public class BarServiceTest {
	
	private static BarService service;


	public static void main(String[] args) {
		BarServiceTest.setUp();
		BarServiceTest test = new BarServiceTest();
		
		test.testDoBar();
	}

	@BeforeClass
	public static void setUp() {
		try {
			InitialContext ctx = new InitialContext();
//			service = (BarService) ctx.lookup("ejb:/barService-0.0.1-SNAPSHOT/BarServiceBean!de.cellent.test.barService.BarService");
//			service = (BarService) ctx.lookup("ejb:barService_ear-0.0.1-SNAPSHOT/barService/BarServiceBean!de.cellent.test.barService.BarService");
			String jndi = JNDIBrowser.getInstance().getLookupString(BarService.class);
			service = (BarService) ctx.lookup(jndi);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void testDoBar() {
		System.out.println(service.doBar("Hi theRe"));
	}
}
