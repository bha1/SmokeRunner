package com.webapp.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.IOUtils;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/client")
public class RestClient implements IRestClient {

	@POST
	@Path("/excel")
	@Produces("application/vnd.ms-excel")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Override
	public Response processExcel(@FormDataParam("uploadFile") InputStream fileInputStream,
            @FormDataParam("uploadFile") FormDataContentDisposition fileFormDataContentDisposition){
		ResponseBuilder responseBuilder = null;
		Response response = null;
		    File tempFile;
			try {
			tempFile = File.createTempFile("sample", "xlsx");
		    tempFile.deleteOnExit();
		    System.out.println("step 2");
		    FileOutputStream out = new FileOutputStream(tempFile);
		    
		    //InputStream in = getClass().getClassLoader().getResourceAsStream(".");
	//	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		   // throw new FileNotFoundException("file not found"+getClass().getClassLoader().getResourceAsStream("REST_API.xlsx"));
		   // IOUtils.copy(getClass().getClassLoader().getResourceAsStream("REST_API.xlsx"), out);
		    System.out.println("step 3");
		    System.out.println(fileInputStream);
		    System.out.println(fileFormDataContentDisposition.getFileName());
		    IOUtils.copy(fileInputStream, out);
		    System.out.println("step 4");
			responseBuilder = Response.ok((Object)tempFile);
			responseBuilder.header("Content-Disposition",
					"attachment; filename=\"sample.xlsx\"");
			response = responseBuilder.build();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return response;
	}
	
	@POST
	@Path("/testCases")
	@Produces("application/vnd.ms-excel")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Override
	public Response processTestCases(@FormDataParam("uploadFile") InputStream fileInputStream,
			@FormDataParam("uploadFile") FormDataContentDisposition fileFormDataContentDisposition){
		Response response = null;
		
		
		return response;
	}
	
}
