package com.spring.webservice.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.spring.webservice.exception.CustomException;

@Service
@Path("/api")
public class TestService {

	@GET
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response test() {
		return Response.ok(new ResponseModel("body")).build();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN_VALUE)
	@Path("/text")
	public Response test1() {
		return Response.ok("Hello Jersey").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML_VALUE)
	@Path("/xml")
	public Response test3() {
		return Response.ok(new XmlResponseModel("body")).build();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN_VALUE)
	@Path("/textex")
	public Response test2() throws Exception {
		throw new CustomException("Test Exception");
	}
	
	class ResponseModel {
		String body;
		
		ResponseModel(String body) { this.body = body; }
		public String getBody() { return body; }
	}
	
	@XmlRootElement(name = "root")
	static class XmlResponseModel {
		
		@XmlAttribute(name = "item")
		String body;
		
		XmlResponseModel() {}
		XmlResponseModel(String body) { this.body = body; }
		public String getBody() { return body; }
	}
}
