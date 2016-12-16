package com.webapp.xml;

import javax.jws.WebService;

//Service Implementation Bean

@WebService(endpointInterface = "com.webapp.xml.HelloWorld")
public class HelloWorldImpl implements HelloWorld{

	@Override
	public String getHelloWorldAsString() {
		return "Hello World JAX-WS";
	}
}
