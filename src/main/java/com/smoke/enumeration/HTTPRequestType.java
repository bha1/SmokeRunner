package com.smoke.enumeration;


/**
 * this enumeration will map the http request type.
 * 
 * only adding 4 for now to restrict the scope of
 * minimal viable project
 * 
 * @author pbhawan
 *
 */
public enum HTTPRequestType {
	GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE");
	
	private final String value;
	
	private HTTPRequestType(String value) {
		this.value = value;
	}
	
	public String toString(){
		return value;
	}
	
	public static HTTPRequestType fromValue(String str){
		for(HTTPRequestType element : HTTPRequestType.values()){
			if(element.toString().equals(str)){
				return element;
			}
		}
		return null;
	}
}
