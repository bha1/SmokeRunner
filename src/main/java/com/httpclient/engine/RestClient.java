package com.httpclient.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class RestClient {

	public void makeHttpRequest(){
		
	}
	
	public static void main(String[] args) {
		try{
			HttpClient defaultHttpClient = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet("http://localhost:8080/RESTfulExample/rest/hello/get");
		getRequest.addHeader("accept","application/json");
		
		HttpResponse response = defaultHttpClient.execute(getRequest);
		
		if(response.getStatusLine().getStatusCode() != 200){
			throw new RuntimeException("failed get : "+ response.getStatusLine().getStatusCode());
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
		String output;
		System.out.println("Output from server ... \n");
		while( (output = reader.readLine()) != null ){
			System.out.println(output);
		}
		}catch(ClientProtocolException e){
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
