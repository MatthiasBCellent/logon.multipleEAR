package de.cellent.test.barService;

import javax.enterprise.inject.Any;

//https://issues.jboss.org/browse/AS7-4567
//@Any
public class BazzImpl implements Bazz {

	public String doBazz(String bazz) {
		
		return this.getClass().getSimpleName()  +": " +  bazz.toUpperCase();
	}

}
