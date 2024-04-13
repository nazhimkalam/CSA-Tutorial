package Ik.iit.tutorial;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Collection;

@Path("/student")
public class StudentResource {

    // Get all students
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Collection<Student> getAllStudents() {
        return StudentDAO.getStudents().values();
    }

    // Get a specific student by ID
    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("userId") int userId) {
        Student student = StudentDAO.findStudentById(userId);

        if (student != null) {
            return Response.status(Response.Status.OK).entity(student).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Create new student
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response createStudent(Student newStudent) {
        StudentDAO.addStudent(newStudent);
        return Response.status(Response.Status.CREATED)
                .entity("Student created successfully.")
                .build();
    }

    // Update student
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{userId}")
    public Response updateStudent(@PathParam("userId") int userId, Student updateRequest) {
        StudentDAO.updateStudent(userId, updateRequest);
        return Response.status(Response.Status.OK)
                .entity("Student updated successfully.")
                .build();
    }

    // Delete student
    @DELETE
    @Path("/delete/{userId}")
    public Response deleteStudent(@PathParam("userId") int userId) {
        StudentDAO.deleteStudent(userId);
        return Response.status(Response.Status.OK)
                .entity("Student deleted successfully.")
                .build();
    }
}
