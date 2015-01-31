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
import com.project.entity.Activity;
import com.project.entity.Responsible;
import com.project.exceptions.ProjectException;
import com.project.services.ResponsibleService;

@Stateless
@Path("/resources")
public class ResponsibleRest {

	@EJB
	private ResponsibleService responsibleService;

	@PUT
	@Path("/add/{idActivity}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Responsible responsible, @PathParam("idActivity")
	int idActivity) throws ProjectException {
		Activity activity = new Activity();
		activity.setId(idActivity);

		this.responsibleService.add(responsible, activity);

		ResponseMessage res = new ResponseMessage();
		res.getMessages().add(new Message("Se agrego correntamente el registro", MessageSeverity.success));

		return Response.ok().status(Status.CREATED).entity(res.getResponseMessage()).type(MediaType.APPLICATION_JSON)
		        .build();
	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResponsibles() throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.responsibleService.getAll());

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("ERROR RESPONSABILIDAD NO ENCONTRADA");
		}
	}

	@DELETE
	@Path("/delete/{idActivity}/{idResponsible}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response remove(@PathParam("idActivity")
	int idActivity, @PathParam("idResponsible")
	int idResponsible) throws ProjectException {
		ResponseMessage res = new ResponseMessage();
		res.getMessages().add(new Message("Se ha eliminado el responsable de la Actividad", MessageSeverity.success));

		Activity inActivity = new Activity();
		inActivity.setId(idActivity);

		Responsible itemResponsible = new Responsible();
		itemResponsible.setId(idResponsible);

		this.responsibleService.remove(inActivity, itemResponsible);
		return Response.ok().status(Status.OK).entity(res.getResponseMessage()).type(MediaType.APPLICATION_JSON)
		        .build();
	}
}
