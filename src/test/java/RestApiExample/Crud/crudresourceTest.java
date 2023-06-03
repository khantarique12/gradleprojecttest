/**
 *
 */
package RestApiExample.Crud;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.component.Destroyable;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;

/**
 * @author khant
 *
 */
class crudresourceTest {

	/**
	 * @throws java.lang.Exception
	 */
	static Server jettyServer;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/api");

		jettyServer = new Server(7077);
		jettyServer.setHandler(context);

		ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);

		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
				CrudResource.class.getCanonicalName());
		System.out.println("Server Start");
		jettyServer.start();
		jettyServer.join();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {

		jettyServer.destroy();
		System.out.println("Server Close");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
//		System.out.println("Server Start");
//		jettyServer.start();
//		jettyServer.join();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {

	}

	/**
	 * Test method for {@link RestApiExample.Crud.CrudResource#getAll()}.
	 */
	@Test
	void testGetAll() {
		final String FULL_PATH = "http://localhost:7077/api/crud";
		final JerseyClient client = new JerseyClientBuilder().build();
		final JerseyWebTarget target = client.target(FULL_PATH);
		String response = target.request().get(String.class);
		System.out.println(response);
	}

	/**
	 * Test method for
	 * {@link RestApiExample.Crud.CrudResource#getById(java.lang.Long)}.
	 */
	@Test
	void testGetById() {
		final String FULL_PATH = "http://localhost:7077/api/crud/getbyid";
		final JerseyClient client = new JerseyClientBuilder().build();
		final JerseyWebTarget target = client.target(FULL_PATH + "/2");
		Response response = target.request().get();
		// CrudModel crudModel = response.readEntity(CrudModel.class);
		// System.out.println(crudModel.toString());
		System.out.println(response.getStatus());
		response.close();
	}

	/**
	 * Test method for
	 * {@link RestApiExample.Crud.CrudResource#deletePerson(java.lang.Long)}.
	 */
	@Test
	void testDeletePerson() {
		final String FULL_PATH = "http://localhost:7077/api/crud/delete";
		JerseyClient client = new JerseyClientBuilder().build();
		JerseyWebTarget target = client.target(FULL_PATH + "/2");
		Response response = target.request().delete();
		System.out.println(response.getStatus());
		response.close();
	}

	/**
	 * Test method for
	 * {@link RestApiExample.Crud.CrudResource#addNewPerson(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testAddNewPerson() {
		final String FULL_PATH = "http://localhost:7077/api/crud/addperson";
		JerseyClient client = new JerseyClientBuilder().build();
		JerseyWebTarget target = client.target(FULL_PATH);
		Response response = target.request().post(Entity.entity(new CrudModel(60, "Amir", "30"), "application/json"));
		System.out.println(response.getStatus());
		response.close();
	}

}
