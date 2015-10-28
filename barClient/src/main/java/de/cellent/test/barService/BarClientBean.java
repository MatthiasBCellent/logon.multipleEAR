package de.cellent.test.barService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.cellent.test.util.InternalJNDIBrowserBean;

/**
 * This is the test, whether we can inject BarService from another ear ...
 * 
 * @author mbohnen
 *
 */

@Stateless
@Remote(BarClient.class)
public class BarClientBean implements BarClient {

	// will not work across deployments (different jars, ears)
//	@EJB
//	private BarService service;
	
	// hard-wired lookup string
//	@EJB(lookup = "java:global/barService_ear-0.0.1-SNAPSHOT/barComponent/BarServiceBean")
//	private BarService service;
	
	// hand-made 
//	@EJB(mappedName = "java:global/BarService/BarBeanByMappedName")
//	private BarService service;
	
	// old school
	private BarService service;
	
	@EJB
	private InternalJNDIBrowserBean jndiBrowser;
	
	public String accessBar(String msg) {
		String ret = service.doBar(msg);
		return ret;
	}
	
	// this is the old-school alternative
	@PostConstruct
	public void init() {
		try {
			Context ctx = new InitialContext();
//			this.service = (BarService) ctx.lookup("ejb:barService_ear-0.0.1-SNAPSHOT/barComponent/BarServiceBean!de.cellent.test.barService.BarService");
			this.service = (BarService) ctx.lookup(jndiBrowser.getLookupString(BarService.class));
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
