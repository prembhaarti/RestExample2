package com.example.rest.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.example.rest.dao.EmployeeDao;
import com.example.rest.model.Employee;

@Path("/employees")
public class EmployeesResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;


	// Return the list of employees for applications
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		employees.addAll(EmployeeDao.instance.getEmployeeData().values());
		return employees;
	}

	// retuns the number of employees
	// Use http://localhost:8080/de.vogella.jersey.employee/rest/employees/count
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = EmployeeDao.instance.getEmployeeData().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newEmployee(@FormParam("id") String id,
			@FormParam("name") String name,
			@FormParam("project") String project,
			@Context HttpServletResponse servletResponse) throws IOException {
		Employee employee = new Employee(id, name, project);
		int count;
		try {
			count = Integer.parseInt(getCount()) + 1;

		} catch (Exception e) {
			throw new RuntimeException("Error in creating new Employee");
		}
		EmployeeDao.instance.getEmployeeData().put(employee.getId(),
				employee);

		servletResponse.sendRedirect("../restexample.html");
	}

	// Defines that the next path parameter after employees is
	// treated as a parameter and passed to the EmployeeResources
	// Allows to type
	// http://localhost:8080/de.vogella.jersey.employee/rest/employees/1
	// 1 will be treaded as parameter employee and passed to EmployeeResource
	@Path("{employee}")
	public EmployeeResource getEmployee(@PathParam("employee") String id) {
		return new EmployeeResource(uriInfo, request, id);
	}

}