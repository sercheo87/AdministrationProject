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
import com.project.entity.Project;
import com.project.exceptions.ProjectException;
import com.project.services.ProjectService;

@Stateless
@Path("/project")
public class ProjectRest {

	@EJB
	private ProjectService projectService;

	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Project project) throws ProjectException {
		try {
			this.projectService.add(project);

			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se agrego correntamente el registro", MessageSeverity.success));

			return Response.ok().status(Status.CREATED).entity(res.getResponseMessage())
					.type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando el proyecto");
		}
	}

	@PUT
	@Path("/{projectId}/addBeneficiary/{beneficiaryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBeneficiary(@PathParam("projectId") Integer projectId,
			@PathParam("beneficiaryId") Integer beneficiaryId) throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			this.projectService.addBeneficiary(projectId, beneficiaryId);
			res.getMessages().add(new Message("Se agrego correntamente el registro", MessageSeverity.success));

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (ProjectException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error general agregando el beneficiario");
		}
	}

	@GET
	@Path("/{projectId}/getActivities")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActivities(@PathParam("projectId") Integer projectId) throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.projectService.getActivities(projectId));

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Proyectos no encontrados");
		}
	}

	@GET
	@Path("/detail/{idProject}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjectById(@PathParam("idProject") Integer idProject) throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.projectService.getProjectById(idProject));

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el proyecto");
		}
	}

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
	public Response getProjectsByBeneficiary(@PathParam("beneficiaryId") Integer beneficiaryId) throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.projectService.getProjectsByBeneficiary(beneficiaryId));

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Proyectos no encontrados");
		}
	}

	@GET
	@Path("/{idProject}/getSummary")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSummary(@PathParam("idProject") Integer idProject) throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.projectService.getSummary(idProject));

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el sumario");
		}
	}

	@DELETE
	@Path("/{projectId}/removeBeneficiary/{beneficiaryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeBeneficiary(@PathParam("projectId") Integer projectId,
			@PathParam("beneficiaryId") Integer beneficiaryId) throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			this.projectService.removeBeneficiary(projectId, beneficiaryId);
			res.getMessages().add(new Message("Se elimino correntamente el registro", MessageSeverity.success));

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (ProjectException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error general removiendo el beneficiario");
		}
	}

}
