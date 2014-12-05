package com.project.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ProjectExceptionHandler implements ExceptionMapper<ProjectException> {

	@Override
	public Response toResponse(ProjectException arg0) {
		return Response.serverError().entity(arg0.getMessage()).build();
	}

}
