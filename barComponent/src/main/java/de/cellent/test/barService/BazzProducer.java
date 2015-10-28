package de.cellent.test.barService;

import javax.enterprise.inject.Produces;

public class BazzProducer {

//	@Produces 
	public Bazz getBazzService() {
		System.out.println(this.getClass().getSimpleName() + "Producing");
		return new BazzImpl();
	}
}
