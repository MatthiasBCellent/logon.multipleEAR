package de.cellent.test.barService;

import javax.ejb.Remote;

@Remote
public interface BarService {
	public String doBar(String msg);
}
