package com.irahul.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Say hello depending on Accept header
 * @author rahul
 *
 */
@Path("/hello")
public class Hello {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(@QueryParam(value = "name") String name) {
		if(name==null){
			return "Hello Code Camp!";
		}
		return "Hello " + name + "!";
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String sayHelloXML() {
		return "<?xml version=\"1.0\"?>" + "<hello>Hello Code Camp" + "</hello>";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHelloJSON() {
		return "{\"hello\":\"Hello Code Camp\"}";
	}
}
