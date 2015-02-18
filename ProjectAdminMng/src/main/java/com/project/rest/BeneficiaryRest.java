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
import com.project.entity.Beneficiary;
import com.project.exceptions.ProjectException;
import com.project.services.BeneficiaryService;

@Stateless
@Path("/beneficiary")
public class BeneficiaryRest {

	@EJB
	private BeneficiaryService beneficiaryService;

	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Beneficiary beneficiary) throws ProjectException {
		try {
			this.beneficiaryService.add(beneficiary);

			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se agrego correntamente el registro", MessageSeverity.success));

			return Response.ok().status(Status.CREATED).entity(res.getResponseMessage())
			        .type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error registrando en nuevo beneficiario");
		}
	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.beneficiaryService.getAll());

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener los beneficiarios");
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response remove(@PathParam("id") int id) throws ProjectException {
		try {
			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se ha eliminado el beneficiario", MessageSeverity.success));

			Beneficiary item = new Beneficiary();
			item.setId(id);

			this.beneficiaryService.remove(item);
			return Response.ok().status(Status.OK).entity(res.getResponseMessage()).type(MediaType.APPLICATION_JSON)
			        .build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error borrando el beneficiario");
		}
	}

	@GET
	@Path("/summary")
	@Produces(MediaType.APPLICATION_JSON)
	public Response summary() throws ProjectException {
		try {

			ResponseMessage res = new ResponseMessage();
			res.setData(this.beneficiaryService.getSummary());

			return Response.ok().status(Status.OK).entity(res.getResponse()).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error al obtener el resumen de proyectos por beneficiarios");
		}
	}

	@POST
	@Path("/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Beneficiary beneficiary) throws ProjectException {
		try {

			this.beneficiaryService.update(id, beneficiary);

			ResponseMessage res = new ResponseMessage();
			res.getMessages().add(new Message("Se actualizo correntamente el registro", MessageSeverity.success));

			return Response.ok().status(Status.CREATED).entity(res.getResponseMessage())
			        .type(MediaType.APPLICATION_JSON).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProjectException("Error actualizando el beneficiario");
		}
	}
}