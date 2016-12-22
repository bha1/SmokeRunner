package com.rest.resources;

import org.apache.http.Header;

import com.smoke.enumeration.HTTPRequestType;

public class HTTPRequestDTO {
	HTTPRequestType httpRequestType;
	
	String url;
	
	String requestPayload;
	
	Header[] headers;
	
	public void setHeader(Header[] headers) {
		this.headers = headers;
	}
	
	public Header[] getHeader() {
		return headers;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setHttpRequestType(HTTPRequestType httpRequestType) {
		this.httpRequestType = httpRequestType;
	}
	
	public HTTPRequestType getHttpRequestType() {
		return httpRequestType;
	}
	
	public void setRequestPayload(String requestPayload) {
		this.requestPayload = requestPayload;
	}
	
	public String getRequestPayload() {
		return requestPayload;
	}
}
