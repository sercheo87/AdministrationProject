package com.project.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.project.entity.Resources;
import com.project.exceptions.ProjectException;
import com.project.services.ResourcesService;

@Stateless
@Path("/resources")
public class ResourcesRest {
	@EJB
	private ResourcesService resourcesService;

	@GET
	@Path("/getInformation")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Resources> getInformation() {
		return resourcesService.getInformationBasic();
	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResources() throws ProjectException {
		try {
			return Response.ok().entity(resourcesService.getAllResources()).build();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("ERROR CATEGORIA NO ENCONTRADA");
		}
	}
}
