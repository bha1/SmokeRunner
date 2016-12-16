package com.client.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.rest.resources.RestRequest;

public class RestClientEngine {

	private StringBuffer getRequest(RestRequest restRequest) {
		StringBuffer stringBuffer = new StringBuffer();
		String output = new String("failed !!");
		try {
			HttpClient defaultHttpClient = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(restRequest.getUrl());
			request.addHeader("accept", "application/json");
			request.addHeader("Content-type", "application/json");
			request.setEntity(new StringEntity(
					"{\"dictionaryArray\":[{\"nameValuePairDTOArray\":[{\"name\":\"offerId\",\"value\":\"NCS006\",\"genericName\":\"offerId\"},{\"name\":\"campaignCode\",\"value\":\"LRH\",\"genericName\":\"campaignCode\"}]}]}"));
			HttpResponse response = defaultHttpClient.execute(request);

//			if (response.getStatusLine().getStatusCode() != 200) {
//				throw new RuntimeException("failed get : " + response.getStatusLine().getStatusCode());
//			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			// System.out.println("Output from server ... \n");
			while ((output = reader.readLine()) != null) {
				stringBuffer.append(output);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return stringBuffer;
	}

	public static void main(String[] args) {
		RestRequest restRequest = new RestRequest();
		restRequest.setUrl("http://ofss601022:7002/digx/v1/submissions/");
		RestClientEngine restClientEngine = new RestClientEngine();
		StringBuffer str = restClientEngine.getRequest(restRequest);
		System.out.println(str);
		JSONTokener jsonTokener = new JSONTokener(new String(str));
		System.out.println(jsonTokener.next(2));
	}

}
