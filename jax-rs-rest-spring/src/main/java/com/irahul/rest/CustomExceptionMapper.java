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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Map RuntimeException to a nicer message
 * @author rahul
 *
 */
@Provider
public class CustomExceptionMapper implements ExceptionMapper<RuntimeException> {
	private Log logger = LogFactory.getLog(getClass());

	@Override
	public Response toResponse(RuntimeException e) {
		logger.info("mapping exception",e);
		return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
	}

}
