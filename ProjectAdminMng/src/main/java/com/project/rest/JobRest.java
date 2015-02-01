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
import com.project.entity.Job;
import com.project.exceptions.ProjectException;
import com.project.services.JobService;

@Stateless
@Path("/job")
public class JobRest {
	@EJB
	private JobService jobService;

	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addResponsible(Job job) throws ProjectException {
		try {
			this.jobService.add(job);

			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se agrego correntamente el registro", MessageSeverity.success));

			return Response.ok().status(Status.CREATED).entity(res.getResponseMessage())
			        .type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando el cargo del responable");
		}
	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResponsible() throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.jobService.getAll());

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener los cargos del responsable");
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeResponsible(@PathParam("id")
	int id) throws ProjectException {
		try {
			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se ha eliminado el cargo del responsable", MessageSeverity.success));

			Job job = new Job();
			job.setId(id);

			this.jobService.remove(job);
			return Response.ok().status(Status.OK).entity(res).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error borrando el cargo del responsable");
		}
	}

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateResource(Job job) throws ProjectException {
		try {

			this.jobService.update(job);

			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se actualizo correntamente el registro", MessageSeverity.success));

			return Response.ok().status(Status.CREATED).entity(res.getResponseMessage())
			        .type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error actualizando el cargo del responsable");
		}
	}
}
