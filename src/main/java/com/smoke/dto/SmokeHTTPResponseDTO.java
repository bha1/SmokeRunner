package com.smoke.dto;

import org.apache.http.Header;
import org.apache.http.StatusLine;

public class SmokeHttpResponseDTO {
	StatusLine statusLine;
	
	String response;
	
	Header[] headers;

	public StatusLine getStatusLine() {
		return statusLine;
	}

	public void setStatusLine(StatusLine statusLine) {
		this.statusLine = statusLine;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Header[] getHeaders() {
		return headers;
	}

	public void setHeaders(Header[] headers) {
		this.headers = headers;
	} 
	
}
