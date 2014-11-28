package com.project.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.project.entity.User;
import com.project.services.UserServicio;

@Stateless
@Path("/user")
public class UserRest {
	@EJB
	private UserServicio userServicio;

	@GET
	@Path("/getAll")
	@Produces("application/json")
	public List<User> buscar() {
		System.out.println("sch llamando a servicio");
		return userServicio.recuperartodos();

	}
}
