package com.webapp.rest;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.smoke.core.StreamMachine;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/smoke")
public class SmokeEndPoint {
	
	@GET
	@Produces("application/vnd.ms-excel")
	@Path("/templateFile")
	public Response generateTemplateFile(){
		ResponseBuilder responseBuilder = null;
		Response response = null;
		StreamMachine streamMachine = new StreamMachine();
		
		responseBuilder = Response.ok((Object)streamMachine.getTemplateFile());
		responseBuilder.header("Content-Disposition",
				"attachment; filename=\"template.xlsx\"");
		response = responseBuilder.build();
		return response;
	}
	
	@POST
	@Produces("application/vnd.ms-excel")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/excel")
	public Response runTest(@FormDataParam("uploadFile") InputStream inputStream,
            @FormDataParam("uploadFile") FormDataContentDisposition fileFormDataContentDisposition){
		Response response = null;
		ResponseBuilder responseBuilder = null;
		StreamMachine streamMachine = new StreamMachine();

		responseBuilder = Response.ok((Object)streamMachine.smokeRunner(inputStream));
		responseBuilder.header("Content-Disposition", "attachment; filename=\""+fileFormDataContentDisposition.getFileName()+"\"");
		response = responseBuilder.build();
		return response;
	}
}
