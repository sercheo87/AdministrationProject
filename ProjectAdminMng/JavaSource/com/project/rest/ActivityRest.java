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
import com.project.exceptions.ProjectException;
import com.project.services.ActivityService;

@Stateless
@Path("/activity")
public class ActivityRest {
	@EJB
	private ActivityService service;

	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Activity activity) throws ProjectException {
		try {
			this.service.add(activity);

			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se agrego correntamente el registro", MessageSeverity.success));

			return Response.ok().status(Status.CREATED).entity(res.getResponseMessage())
					.type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando en nuevo la actividad");
		}
	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.service.getAll());

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener los estados de la actividad");
		}
	}

	@GET
	@Path("/get/{idActivity}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@PathParam("idActivity")
	int idActivity) throws ProjectException {
		Activity activity = new Activity();
		activity.setId(idActivity);

		ResponseMessage res = new ResponseMessage();
		res.setData(this.service.getActivity(activity));

		return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path("/delete/{idActivity}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response remove(@PathParam("idActivity")
	int idActivity) throws ProjectException {
		try {
			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se ha eliminado la actividad", MessageSeverity.success));

			Activity item = new Activity();
			item.setId(idActivity);

			this.service.remove(item);
			return Response.ok().status(Status.OK).entity(res.getResponseMessage()).type(MediaType.APPLICATION_JSON)
			        .build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error borrando el estado de la actividad");
		}
	}
}
