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
import com.project.entity.Activity;
import com.project.exceptions.ProjectException;
import com.project.services.ActivityService;

@Stateless
@Path("/activity")
public class ActivityRest {
	@EJB
	private ActivityService activityService;

	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Activity activity) throws ProjectException {
		try {
			this.activityService.add(activity);

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
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.activityService.getAll());

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener los estados de la actividad");
		}
	}

	@GET
	@Path("/getResume")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResume() throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.activityService.getWithOutResources());

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener los estados de la actividad");
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response remove(@PathParam("id")
	int id) throws ProjectException {
		try {
			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se ha eliminado el estado de la actividad", MessageSeverity.success));

			Activity item = new Activity();
			item.setId(id);

			this.activityService.remove(item);
			return Response.ok().status(Status.OK).entity(res.getResponseMessage()).type(MediaType.APPLICATION_JSON)
			        .build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error borrando el estado de la actividad");
		}
	}

	@POST
	@Path("/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id")
	int id, Activity activity) throws ProjectException {
		try {

			this.activityService.update(id, activity);

			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se actualizo correntamente el registro", MessageSeverity.success));

			return Response.ok().status(Status.CREATED).entity(res.getResponseMessage())
			        .type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error actualizando el estado de la actividad");
		}

	}
}
