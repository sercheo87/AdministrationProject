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
import com.project.entity.StateResponsible;
import com.project.exceptions.ProjectException;
import com.project.services.StateResponsibleService;

@Stateless
@Path("/stateResponsible")
public class StateResponsibleRest {
	@EJB
	private StateResponsibleService stateResponsibleService;

	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addResponsible(StateResponsible stateResponsible) throws ProjectException {
		try {
			this.stateResponsibleService.add(stateResponsible);

			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se agrego correntamente el registro", MessageSeverity.success));

			return Response.ok().status(Status.CREATED).entity(res.getResponseMessage())
			        .type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando el nuevo estado del recurso");
		}
	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResponsible() throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.stateResponsibleService.getAll());

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener los estados del responsable");
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeResponsible(@PathParam("id")
	int id) throws ProjectException {
		try {
			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se ha eliminado el estado del responsable", MessageSeverity.success));

			StateResponsible stateResponsible = new StateResponsible();
			stateResponsible.setId(id);

			this.stateResponsibleService.remove(stateResponsible);
			return Response.ok().status(Status.OK).entity(res).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error borrando el estado del responsable");
		}
	}

	@POST
	@Path("/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateResource(@PathParam("id")
	int id, StateResponsible stateResponsible) throws ProjectException {
		try {

			this.stateResponsibleService.update(id, stateResponsible);

			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se actualizo correntamente el registro", MessageSeverity.success));

			return Response.ok().status(Status.CREATED).entity(res.getResponseMessage())
			        .type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error actualizando el estado del responsable");
		}
	}
}
