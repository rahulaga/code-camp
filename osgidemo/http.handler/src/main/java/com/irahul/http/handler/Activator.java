package com.irahul.http.handler;
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
import org.eclipse.equinox.http.HttpService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import com.irahul.shared.ComplexService;
/**
 * Activator for bundle
 * 
 * @author Rahul Agarwal
 *
 */
public class Activator implements BundleActivator, ServiceListener{
	private MyHandler myHandler;
	private BundleContext bundleContext;

	public void start(BundleContext context) throws Exception {
		//we need this later
		bundleContext = context;
		
		//we are interested in service updates
		context.addServiceListener(this);
		
		//load referenced services		
		//http service to register handler
		ServiceReference svcRef = context.getServiceReference("org.osgi.service.http.HttpService");
		HttpService httpService = (HttpService)context.getService(svcRef);
		
		//create and register http handler (catch all)
		myHandler = new MyHandler();		
		httpService.registerServlet("/", myHandler, null, null);
		
		//get a list of ComplexService (there may be more than 1)
		ServiceReference[] complexSvcRefs = context.getServiceReferences("com.irahul.shared.ComplexService",null);
		if(complexSvcRefs!=null){
			for(ServiceReference ref:complexSvcRefs){
				myHandler.addComplexService((ComplexService)context.getService(ref));
			}
		}
		
		System.err.println("http.handler started!");
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void serviceChanged(ServiceEvent event) {
		//some service has been registred/removed
		Object svc=bundleContext.getService(event.getServiceReference());
		
		if(svc instanceof ComplexService){
			if(event.getType()==ServiceEvent.REGISTERED){
				System.err.println("New Service added");
				myHandler.addComplexService((ComplexService)svc);
			}
			else{
				System.err.println("Service stopped");
				myHandler.removeComplexService((ComplexService)svc);
			}
		}
	}
}
