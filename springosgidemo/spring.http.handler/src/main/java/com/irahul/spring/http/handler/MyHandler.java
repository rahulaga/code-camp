package com.irahul.spring.http.handler;
/*
 * Copyright (c) 2009 Rahul Agarwal (http://www.irahul.com) All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.osgi.service.http.HttpService;

import com.irahul.shared.ComplexService;
/**
 * Process an incoming http request
 * @author Rahul Agarwal
 *
 */
public class MyHandler implements Servlet {
	private HttpService httpService;
	private List<ComplexService> complexServices;
	
	public void setHttpService(HttpService httpService){
		this.httpService = httpService;		
	}

	public void setComplexServices(List<ComplexService> complexServices) {
		this.complexServices = complexServices;
	}
	
	//ideally this should be externalized. Have a manager listen for servlets but keeping it simple
	public void init() throws Exception{
		httpService.registerServlet("/", this, null, null);	
	}

	public void service(ServletRequest request, ServletResponse response)
		throws ServletException, IOException {
		
		//we may have discovered more than 1 service
		if(complexServices==null|| complexServices.size()==0){
			response.getOutputStream().print("No Service found!");
		}
		else{
			//there are 1...n services, pick one
			//using a random pick
			response.getOutputStream().print(complexServices.get(
					new Random().nextInt(complexServices.size())).doSomething());
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}
