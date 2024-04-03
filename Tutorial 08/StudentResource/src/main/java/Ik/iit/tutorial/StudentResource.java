package Ik.iit.tutorial;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/*
 * @author Mr.Nazhim
 */
@Path("/student")
public class StudentResource {

    // Static map to store students
    private static Map<Integer, Student> students = new HashMap<>();
    private static AtomicInteger idCounter = new AtomicInteger(0);

    // The static block runs similar to how a constructor work in Java classes, it runs first but in this case you do not need to create an instance so using static block
    // is more meaningful
    static {
        // Adding sample students to the map
        students.put(1, new Student(1, "John", "Doe"));
        students.put(2, new Student(2, "Alice", "Smith"));
        students.put(3, new Student(3, "Bob", "Johnson"));
        // Add more sample students as needed
    }

    // Get all students
//    @GET
//    @Path("/all")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getStudents() {
//        if (!students.isEmpty()) {
//            return Response.status(Response.Status.OK).entity(students.values()).build();
//        } else {
//            return Response.status(Response.Status.NO_CONTENT).entity(students).build();
//        }
//    }
    // Get all students
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Collection<Student> getAllStudents() {
        // Assuming students is a static field holding all the students
        return students.values(); // Assuming students is a Map<Integer, Student>
    }

    // Get a specific student by ID
    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("userId") int userId) {
        Student student = findStudentById(userId);

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
        int newId = generateId(); // Generate a new student ID
        newStudent.setId(newId); // Set the ID for the new student
        
        students.put(newId, newStudent); // Add the new student to the students map

        return Response.status(Response.Status.CREATED)
                .entity("Student with ID " + newId + " created successfully.")
                .build();
    }

    // Update student
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{userId}")
    public String updateStudent(@PathParam("userId") int userId, Student updateRequest) {
        if (students.containsKey(userId)) {
            Student existingStudent = students.get(userId);

            existingStudent.setFirstName(updateRequest.getFirstName());
            existingStudent.setLastName(updateRequest.getLastName());

            return "Student with ID " + userId + " updated successfully.";
        } else {
            return "Student with ID " + userId + " not found.";
        }
    }

    // Delete student
    @DELETE
    @Path("/delete/{userId}")
    public Response deleteStudent(@PathParam("userId") int userId) {
        if (students.containsKey(userId)) {
            students.remove(userId); // Remove the student with the specified ID

            return Response.status(Response.Status.OK)
                    .entity("Student with ID " + userId + " deleted successfully.")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with ID " + userId + " not found.")
                    .build();
        }
    }

    // Helper method to find a student by ID
    private Student findStudentById(int id) {
        if (students.containsKey(id)) {
            return students.get(id);
        } else {
            return null;
        }
    }

    // Helper method to generate unique IDs
    private int generateId() {
        int maxId = 0;
        for (Student student : students.values()) {
            maxId = Math.max(maxId, student.getId());
        }
        return maxId + 1;
    }
}
