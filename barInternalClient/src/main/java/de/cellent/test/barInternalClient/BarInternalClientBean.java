package de.cellent.test.barInternalClient;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import de.cellent.test.barService.BarServiceLocal;

@Stateless
@Remote(BarInternalClient.class)
public class BarInternalClientBean implements BarInternalClient {

	// this EJB is in another jar within the ear ... so we can check,
	// if CDI works across artifacts
	@EJB
	private BarServiceLocal serviceLocal;
	
	public String doBar(String msg) {
		return serviceLocal.doBar(msg);
	}

}
