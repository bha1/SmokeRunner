package com.webapp.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.webapp.service.StringService;
import com.webapp.service.request.StringRequest;

@Path("/string")
public class StringRestService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/uppercase")
	public Response convertUppercase(StringRequest stringRequest){
		StringService stringService = new StringService();
		return Response.status(200).entity(stringService.convertUppercase(stringRequest)).build();
		
	}
}
