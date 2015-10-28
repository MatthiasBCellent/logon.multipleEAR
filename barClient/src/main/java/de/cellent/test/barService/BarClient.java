package de.cellent.test.barService;

import javax.ejb.Remote;

@Remote
public interface BarClient {
	
	public String accessBar(String msg);
}
