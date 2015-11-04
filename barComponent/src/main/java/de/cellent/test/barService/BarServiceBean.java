package de.cellent.test.barService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(BarService.class)
@Local(BarServiceLocal.class)
@Stateless
@EJB(name = "java:global/BarService/BarBeanByMappedName", beanInterface = BarService.class)
public class BarServiceBean implements BarService, BarServiceLocal {

//	@Inject
	private Bazz bazz = new BazzProducer().getBazzService();
	
	public String doBar(String msg) {
		return bazz.doBazz(msg);
	}

	@PostConstruct
	public void init() {
		System.out.println("init");
	}
}
