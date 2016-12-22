package com.smoke.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.smoke.dto.SmokeHTTPResponseDTO;
import com.smoke.dto.SmokeHttpRequestDTO;

/**
 * this class has all the
 * 
 * @author pbhawan
 *
 */
public class HTTPEngine {
	
	private static final String THIS_COMPONENT_NAME = HTTPEngine.class.getName();
	
	private static final Logger LOGGER = Logger.getLogger(THIS_COMPONENT_NAME);

	@SuppressWarnings("null")
	public SmokeHTTPResponseDTO httpGetRequest(SmokeHttpRequestDTO smokeHttpRequestDTO){
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(smokeHttpRequestDTO.getUrl());
		httpGet.addHeader("accept","application/json");
		SmokeHTTPResponseDTO smokeHTTPResponseDTO = null;
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
			if(httpResponse.getStatusLine().getStatusCode() > 199 && httpResponse.getStatusLine().getStatusCode() < 300 ){
				LOGGER.log(Level.SEVERE, "get call for %s failed with status %s"+smokeHttpRequestDTO.getUrl()+httpResponse.getStatusLine().getStatusCode());
			}else{
				smokeHTTPResponseDTO.setHeaders(httpResponse.getAllHeaders());
				smokeHTTPResponseDTO.setStatusLine(httpResponse.getStatusLine());
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
				String responseBody = null;
				StringBuffer stringBuffer = new StringBuffer();
				while((responseBody = bufferedReader.readLine())!= null){
					stringBuffer.append(responseBody);
				}
				smokeHTTPResponseDTO.setResponse(responseBody);
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "get call for %s threw exception"+smokeHttpRequestDTO.getUrl());
			e.printStackTrace();
		}
		return smokeHTTPResponseDTO;
	}
}
