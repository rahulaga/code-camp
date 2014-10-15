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
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.irahul.hellospring.Greeter;

/**
 * Say hello depending on language name
 * @author rahul
 *
 */
@Component
@Path("/hello")
public class Hello {
	@Autowired
	@Qualifier("hindiGreeter")
	private Greeter greeterHindi; 
	
	@Autowired
	@Qualifier("englishGreeter")
	private Greeter greeterEnglish;
	
	@GET
	public String sayHello(@QueryParam(value = "name") String name,
			@HeaderParam(value="X-custom") String myheader) {
		if(name==null){
			throw new RuntimeException("name not provided");
		}
		switch(name){
		case "hindi":
			return greeterHindi.sayHello();
		case "english":
			return greeterEnglish.sayHello();
		default:
			throw new RuntimeException("unknown name:"+name);
		}		
	}
}
