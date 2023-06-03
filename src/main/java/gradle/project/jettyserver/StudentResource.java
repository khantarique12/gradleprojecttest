package gradle.project.jettyserver;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/student")
public class StudentResource {
	// http://localhost:7070/api/student/info/Ted
	@GET
	@Path("/info/{user}")
	@Produces("application/json")
	public Response getStudentInformation(@PathParam("user") String user) {
		// this method get the information of the student
		return Response.ok("API for getting information for user " + user).build();
	}

	// http://localhost:7070/api/student
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response enrollStudent() {
		// for simplicity, we just return a string
		return Response.ok("Successfully enrolled student via POST request").build();
	}

	// http://localhost:7070/api/student
	@PUT
	@Produces("application/json")
	public Response addNewStudent() {
		return Response.ok("Added new Student").build();
	}

	// http://localhost:7070/api/student/Ted
	@DELETE
	@Path("/{user}")
	@Produces("text/plain")
	public Response deleteStudent(@PathParam("user") String user) {
		return Response.ok("Successfully deleted student " + user).build();
	}
}