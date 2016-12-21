package com.webapp.xml;

import javax.jws.WebService;

@WebService(endpointInterface = "com.webapp.xml.HelloWorld")
public class SomeOtherImpl implements HelloWorld {

	@Override
	public String getHelloWorldAsString() {
		return "Hello World JAX-WS";
	}
	
	

}
