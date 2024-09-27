// DepartmentResource.java
package com.deloitte.demo.resource;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.deloitte.demo.model.Department;
import com.deloitte.demo.service.DepartmentService;

@Path("/dept")
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
        Department dept = deptService.getDepartmentById(id);
        if (dept != null) {
            return Response.ok(dept).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Department not found").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDepartment(@PathParam("id") int id) {
        Department dept = deptService.getDepartmentById(id); // Check if the department exists

        if (dept != null) {
            deptService.deleteDepartment(id); // Delete the department
            return Response.status(Response.Status.NO_CONTENT).entity("Department deleted successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Department not found").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDepartment(Department department) {
        Department dept = deptService.addDepartment(department);
        return Response.status(Response.Status.CREATED).entity(dept).header("message", "Department added successfully!").build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDepartment(@PathParam("id") int id, Department updatedDepartment) {
        Department dept = deptService.updateDepartment(id, updatedDepartment);
        if (dept == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Department not found for ID: " + id).build();
        }
        return Response.ok().entity(dept).header("message", "Department updated successfully!").build();
    }
}
