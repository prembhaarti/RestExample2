package com.example.rest.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.example.rest.dao.EmployeeDao;
import com.example.rest.model.Employee;


public class EmployeeResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public EmployeeResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Employee getEmployee() {

		Employee employee = EmployeeDao.instance.getEmployeeData().get(id);
		if (employee == null) {
			throw new RuntimeException("Employee not found");
		}

		return employee;
	}

	@DELETE
	public void deleteEmployee() {	
		Employee c = EmployeeDao.instance.getEmployeeData().remove(id);
		if (c == null)
			throw new RuntimeException("Employee Data Not Found For Deletion");
	}

}
