package com.webapp.service;

import com.webapp.service.request.StringRequest;
import com.webapp.service.response.StringResponse;

public class StringService {
	public StringResponse convertUppercase(StringRequest stringRequest) {
		StringResponse stringResponse = new StringResponse();
		stringResponse.setInput(stringRequest.getInput());
		stringResponse.setOutput(stringRequest.getInput().toUpperCase());
		return stringResponse;
	}

	public Comparable[] heapSort(Comparable[] inputArray) {
		return null;
	}
}
