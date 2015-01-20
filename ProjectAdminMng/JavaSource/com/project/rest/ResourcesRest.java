package com.project.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.project.dto.Message;
import com.project.dto.MessageSeverity;
import com.project.dto.ResponseMessage;
import com.project.entity.ResourceActivity;
import com.project.exceptions.ProjectException;
import com.project.services.ResourcesService;

@Stateless
@Path("/resources")
public class ResourcesRest {
	@EJB
	private ResourcesService resourcesService;

	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(ResourceActivity resource) throws ProjectException {
		try {
			ResourceActivity item = new ResourceActivity();
			item.setQuantity(resource.getQuantity());
			// item.setActivity(resource.getActivity());
			item.setTypeResource(resource.getTypeResource());

			this.resourcesService.add(item);

			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se agrego correntamente el registro", MessageSeverity.success));

			return Response.ok().status(Status.CREATED).entity(res.getResponseMessage())
			        .type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando en nuevo estado de la actividad");
		}
	}

	@GET
	@Path("/getInformation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInformation() throws ProjectException {
		try {
			return Response.ok().entity(this.resourcesService.getInformationBasic()).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("ERROR CATEGORIA NO ENCONTRADA");
		}
	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResources() throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.resourcesService.getAll());

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("ERROR CATEGORIA NO ENCONTRADA");
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response remove(@PathParam("id")
	int id) throws ProjectException {
		try {
			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se ha eliminado el recurso de la Actividad", MessageSeverity.success));

			ResourceActivity item = new ResourceActivity();
			item.setId(id);

			this.resourcesService.remove(item);
			return Response.ok().status(Status.OK).entity(res.getResponseMessage()).type(MediaType.APPLICATION_JSON)
			        .build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error borrando el recurso de la actividad");
		}
	}
}