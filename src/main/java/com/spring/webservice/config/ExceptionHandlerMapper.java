package com.spring.webservice.config;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.spring.webservice.exception.CustomException;

@Provider
class ExceptionHandlerMapper implements ExceptionMapper<CustomException> {

	@Override
	public Response toResponse(CustomException exception) {
		return Response.serverError()
				.entity(new ErrorResponse(exception.getMessage()))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	class ErrorResponse {
		String error;

		ErrorResponse(String error) { this.error = error; }
		
		public String getError() { return error; }
	}
}
