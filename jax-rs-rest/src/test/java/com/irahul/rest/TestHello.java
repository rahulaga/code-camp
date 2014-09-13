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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

/**
 * Using jersey client to make calls
 * 
 * @author rahul
 *
 */
public class TestHello {
	private static final String URI_PATH = "jax-rs-rest/hello";
	private static final String HTTP_LOCALHOST = "http://localhost:8080";

	@Test
	public void testHello(){		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST).path(URI_PATH);
		 
		Response response =	target.request(MediaType.TEXT_PLAIN).get();

		System.out.println(response);		
		Assert.assertEquals("Hello Code Camp!", response.readEntity(String.class));				
	}
	
	@Test
	public void testHelloName(){		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST).path(URI_PATH).queryParam("name", "foo");
		 				 
		Response response =	target.request(MediaType.TEXT_PLAIN).get();

		System.out.println(response);
		Assert.assertEquals("Hello foo!", response.readEntity(String.class));				
	}
	
	@Test
	public void testHelloXML(){		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST).path(URI_PATH);
		 				 
		Response response =	target.request(MediaType.APPLICATION_XML).get();

		System.out.println(response);
		Assert.assertEquals("<?xml version=\"1.0\"?><hello>Hello Code Camp</hello>", response.readEntity(String.class));				
	}
	
	@Test
	public void testHelloJSON(){		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST).path(URI_PATH);
		 				 
		Response response =	target.request(MediaType.APPLICATION_JSON).get();

		System.out.println(response);
		Assert.assertEquals("{\"hello\":\"Hello Code Camp\"}", response.readEntity(String.class));				
	}
}
