package de.cellent.test.barInternalClient;

import javax.ejb.Remote;

@Remote
public interface BarInternalClient {
	
	String doBar(String msg);
}
