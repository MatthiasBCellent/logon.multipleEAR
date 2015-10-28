package de.cellent.test.barService;

import javax.inject.Inject;

public class Foo {

	@Inject
	private BarService service;

	public void doTest() {
		System.out.println(service.doBar("Hi there"));
	}
}
