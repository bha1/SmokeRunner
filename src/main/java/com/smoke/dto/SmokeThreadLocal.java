package com.smoke.dto;

import java.util.LinkedHashMap;

public class SmokeThreadLocal {
	private LinkedHashMap<String, String> threadMap;

	public LinkedHashMap<String, String> getThreadMap() {
		return threadMap;
	}

	public void setThreadMap(LinkedHashMap<String, String> threadMap) {
		this.threadMap = threadMap;
	}
}
