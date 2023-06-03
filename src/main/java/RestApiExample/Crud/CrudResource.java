
package RestApiExample.Crud;

import java.util.List;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/crud")
public class CrudResource {

	// CrudService Object
	CrudService crudservice = new CrudService();

	// This endpoint to Get All Data
	// this method get the information of the Person
	// http://localhost:7077/api/crud/getall
	@GET
	@Produces("application/json")
	public Response getAll() {
		// crudservice crudservice = new crudservice();
		List<CrudModel> list = crudservice.getData();
		if (!list.isEmpty()) {
			return Response.ok(list).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	// This endpoint to Get Data By Person Id
	// this method get the information of the Person
	// http://localhost:7077/api/crud/getbyid/31
	@GET
	@Path("/getbyid/{id}")
	@Produces("application/json")
	public Response getById(@PathParam("id") Long id) {
		// crudservice crudservice = new crudservice();
		CrudModel crudmodel = crudservice.getDataById(id);
		if (crudmodel.getPersonId() != 0L) {
			return Response.ok("Successfully Find Person" + crudmodel).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	// This endpoint to Delete Data By Person Id
	// http://localhost:7077/api/crud/delete/31
	@DELETE
	@Path("/delete/{id}")
	@Produces("application/json")
	public Response deletePerson(@PathParam("id") Long id) {
		// crudservice crudservice = new crudservice();
		// CrudModel crudmodel = crudservice.deleteById(id);
		boolean result = crudservice.deleteById(id) != null;
		// System.out.println(result);
		if (result) {
			return Response.ok("Successfully Deleted Person " + result).build();
		} else {
			return Response.notModified().build();
		}
	}

	// This endpoint to Post Data
	// http://localhost:7077/api/crud/addperson/Frank/26
	@POST
	@Path("/addperson/{name}/{age}")
	@Produces("application/json")
	public Response addNewPerson(@PathParam("name") String name, @PathParam("age") String age) {
		/// crudservice crudservice = new crudservice();
		CrudModel crudmodel = crudservice.insert(name, age);
		return Response.ok("Successfully Insert Person via POST request" + crudmodel).build();
	}

	// This endpoint to Post Data
	// http://localhost:7077/api/crud/updateperson/Frank/26
	@PUT
	@Path("/updateperson/{name}/{age}")
	@Produces("application/json")
	public Response updatePerson(@PathParam("name") String name, @PathParam("age") String age) {
		/// crudservice crudservice = new crudservice();
		CrudModel crudmodel = crudservice.updatePerson(name, age);
		return Response.ok("Successfully Update Person via PUT request" + crudmodel).build();
	}
}
