// EmployeeResource.java 

package com.deloitte.demo.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.deloitte.demo.model.Employee;
import com.deloitte.demo.service.EmployeeService;

@Path("/employees")
public class EmployeeResource {

	private EmployeeService empService =  new EmployeeService();
//	private EmployeeService empService =  EmployeeService.getInstance();
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getAllEmployees() {
		return empService.getAllEmployees();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeById(@PathParam("id") int id) {
	    Employee emp = empService.getEmployeeById(id);
	    if (emp != null) {
	        return Response.ok(emp).build();
	    } else {
	        return Response.status(Response.Status.NOT_FOUND)
	                       .entity("Employee not found")
	                       .build();
	    }
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEmployee(@PathParam("id") int id) {
	    Employee emp = empService.getEmployeeById(id);  // Check if the employee exists
	    
	    if (emp != null) {
	        empService.deleteEmployee(id);  // Delete the employee
	        return Response.status(Response.Status.NO_CONTENT).entity("Employee deleted successfully").build();
	    } else {
	        return Response.status(Response.Status.NOT_FOUND).entity("Employee not found").build();
	    }
	}
	

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEmployee(Employee employee) {
		Employee emp = empService.addEmployee(employee);
		return Response.status(Response.Status.CREATED).entity(emp).header("messsage", "employee added successfully!")
				.build();
	}
//	implement these methods - 
//	getEmployeeById
//	updateEmployee 
//	deleteEmployee 

}
