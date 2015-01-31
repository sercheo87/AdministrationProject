package com.project.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.project.dto.Message;
import com.project.dto.MessageSeverity;
import com.project.dto.ResponseMessage;

@Provider
public class ProjectExceptionHandler implements ExceptionMapper<ProjectException> {

	@Override
	public Response toResponse(ProjectException arg0) {

		ResponseMessage res = new ResponseMessage();
		res.getMessages().add(new Message(arg0.getMessage(), MessageSeverity.error));
		return Response.serverError().entity(res).type(MediaType.APPLICATION_JSON).build();
	}

}
