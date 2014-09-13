package com.irahul.rest;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
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
	private static final String URI_PATH = "jax-rs-rest-spring/hello";
	private static final String HTTP_LOCALHOST = "http://localhost:8080";

	@Test
	public void testHello(){		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST).path(URI_PATH);		 
		Response response =	target.request().get();

		System.out.println(response);		
		Assert.assertEquals("name not provided", response.readEntity(String.class));				
	}
	
	@Test
	public void testHelloHindi(){		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST).path(URI_PATH).queryParam("name", "hindi");		 				 
		Response response =	target.request().get();

		System.out.println(response);
		Assert.assertEquals("Namaste", response.readEntity(String.class));				
	}
	
	@Test
	public void testHelloEnglish(){		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST).path(URI_PATH).queryParam("name", "english");		 				 
		Response response =	target.request().get();

		System.out.println(response);
		Assert.assertEquals("Hello World!", response.readEntity(String.class));				
	}
	
	@Test
	public void testHelloNameFoo(){		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST).path(URI_PATH).queryParam("name", "foo");		 				 
		Response response =	target.request().get();

		System.out.println(response);
		Assert.assertEquals("unknown name:foo", response.readEntity(String.class));				
	}
}
