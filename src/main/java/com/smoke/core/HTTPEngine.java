package com.smoke.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.smoke.dto.SmokeHttpResponseDTO;
import com.smoke.dto.SmokeHttpWrapperDTO;
import com.smoke.enumeration.HTTPRequestType;
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
	
	public ConcurrentHashMap<String,String> threadHeaders;
	
	public HTTPEngine(){
		threadHeaders = new ConcurrentHashMap<>();
	}

	public SmokeHttpResponseDTO httpGetRequest(SmokeHttpRequestDTO smokeHttpRequestDTO){
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(smokeHttpRequestDTO.getUrl());
		httpGet.addHeader("accept","application/json");
		setCookie(httpGet);
		SmokeHttpResponseDTO smokeHTTPResponseDTO = new SmokeHttpResponseDTO();
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if(httpResponse.getStatusLine().getStatusCode() != 200 ){
				LOGGER.severe("ERROR get call for "+smokeHttpRequestDTO.getUrl()+" failed with status %s"+httpResponse.getStatusLine().getStatusCode());
			}else{
				smokeHTTPResponseDTO.setHeaders(httpResponse.getAllHeaders());
				smokeHTTPResponseDTO.setStatusLine(httpResponse.getStatusLine());
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
				String responseBody = null;
				StringBuffer stringBuffer = new StringBuffer();
				while((responseBody = bufferedReader.readLine())!= null){
					stringBuffer.append(responseBody);
				}
				smokeHTTPResponseDTO.setResponse(new String(stringBuffer));
			}
		} catch (IOException e) {
			LOGGER.severe("ERROR get call for "+smokeHttpRequestDTO.getUrl()+" threw exception");
			e.printStackTrace();
		}
		return smokeHTTPResponseDTO;
	}
	
	
	public SmokeHttpResponseDTO httpPostRequest(SmokeHttpRequestDTO smokeHttpRequestDTO){
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(smokeHttpRequestDTO.getUrl());
		httpPost.addHeader("accept", "application/json");
		httpPost.addHeader("Content-type", "application/json");
		setCookie(httpPost);
		SmokeHttpResponseDTO smokeHTTPResponseDTO = new SmokeHttpResponseDTO();
		
		try{
			httpPost.setEntity(new StringEntity(smokeHttpRequestDTO.getRequestPayload()));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode() != 200 && httpResponse.getStatusLine().getStatusCode() != 201 ){
				LOGGER.severe( "ERROR post call for "+smokeHttpRequestDTO.getUrl()+" failed with status "+httpResponse.getStatusLine().getStatusCode());
			}else{
				readCookie(httpResponse.getAllHeaders());
				smokeHTTPResponseDTO.setHeaders(httpResponse.getAllHeaders());
				smokeHTTPResponseDTO.setStatusLine(httpResponse.getStatusLine());
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
				String responseBody = null;
				StringBuffer stringBuffer = new StringBuffer();
				while((responseBody = bufferedReader.readLine()) != null){
					stringBuffer.append(responseBody);
				}
				smokeHTTPResponseDTO.setResponse(new String(stringBuffer));
			}
		}catch(IOException e){
			LOGGER.severe("ERROR post call for "+smokeHttpRequestDTO.getUrl()+" threw exception");
		}
		return smokeHTTPResponseDTO;
	}
	
	public SmokeHttpWrapperDTO[] httpSmokeRunner(SmokeHttpWrapperDTO[] wrapperDTO){
		for(int i = 0; i<wrapperDTO.length; i++){
			if(HTTPRequestType.POST.equals(wrapperDTO[i].getSmokeHttpRequestDTO().getHttpRequestType())){
				wrapperDTO[i].setSmokeHttpResponseDTO(httpPostRequest(wrapperDTO[i].getSmokeHttpRequestDTO()));
			}
			if(HTTPRequestType.GET.equals(wrapperDTO[i].getSmokeHttpRequestDTO().getHttpRequestType())){
				wrapperDTO[i].setSmokeHttpResponseDTO(httpGetRequest(wrapperDTO[i].getSmokeHttpRequestDTO()));
			}
		}
		return wrapperDTO;
	}
	
	private void readCookie(Header[] headers){
		for(int i = 0; i<headers.length ; i++){
			if("set-cookie".equalsIgnoreCase(headers[i].getName())){
				threadHeaders.put(headers[i].getName(), headers[i].getValue());
			}
		}
	}
	
	private void setCookie(HttpRequestBase request){
		for(Map.Entry<String, String> map : threadHeaders.entrySet()){
			request.addHeader(map.getKey(), map.getValue());
		}
	}
}
