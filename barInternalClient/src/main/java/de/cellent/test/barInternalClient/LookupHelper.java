package de.cellent.test.barInternalClient;

import java.rmi.NotBoundException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.cellent.test.barService.BarServiceLocal;
import de.cellent.test.jndi.JNDIBrowser;

@LocalBean
@Stateless
public class LookupHelper {
	
	@EJB
	private BarServiceLocal serviceLocal;
	
	@EJB(mappedName = "java:global/JNDIBrowser")
	private JNDIBrowser browser;
	
	public LookupHelper() {
		this.init();
	}

	@PostConstruct
	public void init() {
		try {	
//			String jndi = browser.getLookupString(BarServiceLocal.class);
//			System.out.println(jndi);
			// the old fashioned lookup
			Context ctx = new InitialContext();
//			serviceLocal = (BarServiceLocal) ctx.lookup("java:global/barService_ear-0.0.1-SNAPSHOT/barComponent/BarServiceBean!de.cellent.test.barService.BarServiceLocal");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public BarServiceLocal getServiceLocal() {
		return serviceLocal;
	}

	public void setServiceLocal(BarServiceLocal serviceLocal) {
		this.serviceLocal = serviceLocal;
	}
	
}
