package com.project.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.project.dto.ResponseMessage;
import com.project.exceptions.ProjectException;
import com.project.services.ProjectService;

@Stateless
@Path("/project")
public class ProjectRest {

	@EJB
	private ProjectService projectService;

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjects() throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.projectService.getAll());

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Proyectos no encontrados");
		}
	}

	@GET
	@Path("/getResume")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjectsByBeneficiary() throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.projectService.getProjectsResume());

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Proyectos no encontrados");
		}
	}

	@GET
	@Path("/getByBeneficiary/{beneficiaryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjectsByBeneficiary(@PathParam("beneficiaryId")
	Integer beneficiaryId) throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.projectService.getProjectsByBeneficiary(beneficiaryId));

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Proyectos no encontrados");
		}
	}
}
