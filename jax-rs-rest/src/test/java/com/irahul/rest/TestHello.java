package com.irahul.rest;


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

	@Test
	public void testHello(){
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080").path("jax-rs-rest/hello");
		 
		Response response =	target.request(MediaType.TEXT_PLAIN).get();

		System.out.println(response);		
		Assert.assertEquals("Hello Code Camp!", response.readEntity(String.class));				
	}
	
	@Test
	public void testHelloName(){
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080").path("jax-rs-rest/hello").queryParam("name", "foo");
		 				 
		Response response =	target.request(MediaType.TEXT_PLAIN).get();

		System.out.println(response);
		Assert.assertEquals("Hello foo!", response.readEntity(String.class));				
	}
	
	@Test
	public void testHelloXML(){
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080").path("jax-rs-rest/hello");
		 				 
		Response response =	target.request(MediaType.APPLICATION_XML).get();

		System.out.println(response);
		Assert.assertEquals("<?xml version=\"1.0\"?><hello>Hello Code Camp</hello>", response.readEntity(String.class));				
	}
	
	@Test
	public void testHelloJSON(){
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080").path("jax-rs-rest/hello");
		 				 
		Response response =	target.request(MediaType.APPLICATION_JSON).get();

		System.out.println(response);
		Assert.assertEquals("{\"hello\":\"Hello Code Camp\"}", response.readEntity(String.class));				
	}
}
