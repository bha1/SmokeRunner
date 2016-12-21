package com.rest.resources;

public class RestRequest {
	RequestType httpRequestType;
	
	String url;
	
	String requestPayload;
	
	String header;
	
	public void setHeader(String header) {
		this.header = header;
	}
	
	public String getHeader() {
		return header;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setHttpRequestType(RequestType httpRequestType) {
		this.httpRequestType = httpRequestType;
	}
	
	public RequestType getHttpRequestType() {
		return httpRequestType;
	}
	
	public void setRequestPayload(String requestPayload) {
		this.requestPayload = requestPayload;
	}
	
	public String getRequestPayload() {
		return requestPayload;
	}
}
