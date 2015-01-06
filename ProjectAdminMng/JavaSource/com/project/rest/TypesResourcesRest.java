package com.project.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.project.entity.TypeResource;
import com.project.exceptions.ProjectException;
import com.project.services.TypesResourcesService;

@Stateless
@Path("/typeResource")
public class TypesResourcesRest {
	@EJB
	private TypesResourcesService typesResourcesService;

	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addResource(TypeResource typeResource) throws ProjectException {
		try {
			this.typesResourcesService.newTypeResource(typeResource);

			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se agrego correntamente el registro", MessageSeverity.success));

			return Response.ok().status(Status.CREATED).entity(res.getResponseMessage())
			        .type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando en nuevo tipo de recurso");
		}
	}

	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delResource(TypeResource typeResource) throws ProjectException {
		try {
			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se ha eliminado el tipo de recurso", MessageSeverity.success));

			this.typesResourcesService.removeTypeResource(typeResource);
			return Response.ok().status(Status.OK).entity(res).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error borrando el tipo de recurso");
		}
	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResources() throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.typesResourcesService.getAll());

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener los tipos de recursos");
		}
	}

	@POST
	@Path("/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateResource(@PathParam("id")
	int id, TypeResource typeResource) throws ProjectException {
		try {

			this.typesResourcesService.updateTypeResource(id, typeResource);

			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se actualizo correntamente el registro", MessageSeverity.success));

			return Response.ok().status(Status.CREATED).entity(res.getResponseMessage())
			        .type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error actualizando el tipo de recurso");
		}
	}
}
