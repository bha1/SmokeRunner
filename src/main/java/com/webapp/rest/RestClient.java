package com.webapp.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.IOUtils;


@Path("/client")
public class RestClient {

	@SuppressWarnings("resource")
	@GET
	@Path("/excel")
	@Produces("application/vnd.ms-excel")
	public Response processExcel(){
		ResponseBuilder responseBuilder = null;
		Response response = null;
		    File tempFile;
			try {
				tempFile = File.createTempFile("sample", "xlsx");
System.out.println("step 1");
		    tempFile.deleteOnExit();
		    System.out.println("step 2");
		    FileOutputStream out = new FileOutputStream(tempFile);
		    System.out.println("step 3");
		    InputStream in = getClass().getClassLoader().getResourceAsStream(".");
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String resource;

		      while( (resource = br.readLine()) != null ) {
		        System.out.println( resource );
		      }
		   // throw new FileNotFoundException("file not found"+getClass().getClassLoader().getResourceAsStream("REST_API.xlsx"));
		    IOUtils.copy(getClass().getClassLoader().getResourceAsStream("REST_API.xlsx"), out);
		    System.out.println("step 4");
			responseBuilder = Response.ok((Object)tempFile);
			response = responseBuilder.build();
			System.out.println("step 5");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			response.header("Content-Disposition",
//					"attachment; filename=new-excel-file.xls");
		return response;
	}
	
}
