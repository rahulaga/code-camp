package com.irahul.rest;
/**
 * Copyright (c) 2014 Rahul Agarwal (http://www.irahul.com) All rights 
 * reserved.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
