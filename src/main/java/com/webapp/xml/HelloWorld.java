package com.webapp.xml;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//Service Endpoint Interface
@SOAPBinding(style = Style.RPC)
@WebService
public interface HelloWorld{

	@WebMethod String getHelloWorldAsString();

}