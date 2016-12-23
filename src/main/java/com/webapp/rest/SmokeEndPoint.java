package com.webapp.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.smoke.core.StreamMachine;

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

}
