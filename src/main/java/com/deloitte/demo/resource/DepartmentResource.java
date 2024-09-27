package com.deloitte.demo.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.deloitte.demo.model.Department;
import com.deloitte.demo.service.DepartmentService;

@Path("/departments")
public class DepartmentResource {
	
	private DepartmentService deptService = new DepartmentService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Department> getAllDepartments() {
        return deptService.getAllDepartments();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentById(@PathParam("id") int id) {
        Department department = deptService.getDepartmentById(id);
        if (department != null) {
            return Response.ok(department).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Department not found").build();
        }
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDepartment(@PathParam("id") int id, Department updatedDepartment) {
        Department updated = deptService.updateDepartment(id, updatedDepartment);
        if (updated != null) {
            return Response.ok(updated).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Department not found").build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDepartment(@PathParam("id") int id) {
        Department department = deptService.getDepartmentById(id);
        if (department != null) {
            deptService.deleteDepartment(id);
            return Response.status(Response.Status.NO_CONTENT).entity("Department deleted").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Department not found").build();
        }
    }
}
