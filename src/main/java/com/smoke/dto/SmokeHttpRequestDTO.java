package com.smoke.dto;

import org.apache.http.Header;

import com.smoke.enumeration.HTTPRequestType;

/**
 * Smoke http request to have
 * 1) HTTPRequest type
 * 2) HTTP url
 * 3) Request payload
 * 4) HTTP Headers 
 * 
 * @author pbhawan
 *
 */
public class SmokeHttpRequestDTO {
	HTTPRequestType httpRequestType;
	
	String url;
	
	String requestPayload;
	
	Header[] headers;

	public HTTPRequestType getHttpRequestType() {
		return httpRequestType;
	}

	public void setHttpRequestType(HTTPRequestType httpRequestType) {
		this.httpRequestType = httpRequestType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRequestPayload() {
		return requestPayload;
	}

	public void setRequestPayload(String requestPayload) {
		this.requestPayload = requestPayload;
	}

	public Header[] getHeaders() {
		return headers;
	}

	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}
	
}
