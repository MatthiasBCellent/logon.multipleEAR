package de.cellent.test.barInternalClient;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.sql.DataSource;

import de.cellent.test.barService.BarServiceLocal;

@Stateless
@Remote(BarInternalClient.class)
public class BarInternalClientBean implements BarInternalClient {

	// this EJB is in another jar within the ear ... so we can check,
	// if CDI works across artifacts
//	@EJB
//	@Inject
	private BarServiceLocal serviceLocal;
	
	@Resource(lookup="java:jboss/datasources/logon")
	private DataSource dataSource;
	
	@EJB
	private LookupHelper helper; 
	
	public String doBar(String msg) {
		return this.serviceLocal.doBar(msg);
	}
	
	@PostConstruct
	public void init() {
		// pretty much old fashioned ... delegating the things to a helper if not injecting the things
		this.serviceLocal = helper.getServiceLocal();
	}

	public void getConnection() {
		try {
			// let's see whether we can obtain a connection
			this.dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
