package RestApiExample.Crud;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class CrudService {

	protected SessionFactory sessionFactory;

	protected SessionFactory setup() {
		// A SessionFactory is set up once for an Application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure("/RestApiExample/Crud/hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
			// The Registry would be destroyed by the SessionFactroy, but we had a trouble
			// building Sessionactory
			// So destroy it manually
			ex.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);

		}
		return sessionFactory;
	}

	protected void exit() {
		sessionFactory.close();
	}

	// Get Person Data
	protected void create() {
		CrudModel crudmodel = new CrudModel();

		crudmodel.setName("Ted");
		crudmodel.setAge("29");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(crudmodel);
		session.getTransaction().commit();
		// session.close();
	}

	protected CrudModel insert(String name, String age) {
		setup();
		Session session = sessionFactory.openSession();
		CrudModel crudmodel = new CrudModel();
		crudmodel.setName(name);
		crudmodel.setAge(age);

		session.beginTransaction();
		session.save(crudmodel);
		session.getTransaction().commit();
		return crudmodel;
	}

	protected CrudModel updatePerson(String name, String age) {
		setup();
		Session session = sessionFactory.openSession();
		CrudModel crudmodel = new CrudModel();
		crudmodel.setName(name);
		crudmodel.setAge(age);

		session.beginTransaction();
		session.update(crudmodel);
		session.getTransaction().commit();
		return crudmodel;
	}

	// Get List of All Data
	protected List<CrudModel> getData() {

		List<CrudModel> list = new ArrayList<>();
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		list = session.createQuery("SELECT a FROM CrudModel a", CrudModel.class).getResultList();
		session.getTransaction().commit();
		return list;

	}

	// Get Person Data By Person ID
	protected CrudModel getDataById(long id) {
		setup();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		CrudModel crudmodel = session.get(CrudModel.class, id);
		crudmodel.getPersonId();
		crudmodel.getName();
		crudmodel.getAge();
		session.getTransaction().commit();
		return crudmodel;
	}

	// Delete Person Data By Person ID
	protected CrudModel deleteById(long id) {

		setup();
		Session session = sessionFactory.openSession();
		CrudModel crudmodel = session.get(CrudModel.class, id);
		// crudmodel.setPersonId(id);
		session.delete(crudmodel);
		session.beginTransaction();
		session.getTransaction().commit();
		System.out.println(crudmodel);
		return crudmodel;
	}

	// This Main Method use to start Jetty Server
//	public static void main(String[] args) throws Exception {
//		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//		context.setContextPath("/api");
//
//		Server jettyServer = new Server(7077);
//		jettyServer.setHandler(context);
//
//		ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
//		jerseyServlet.setInitOrder(0);
//
//		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
//				CrudResource.class.getCanonicalName());
//
//		CrudService crudservice = new CrudService();
//		crudservice.setup();
//		// crudservice.create();
//		// crudservice.delete();
//		crudservice.exit();
//		System.out.println("Hello");
//		try {
//			jettyServer.start();
//			jettyServer.join();
//		} finally {
//			jettyServer.destroy();
//		}
//	}
}

//protected crudmodel getDataById(long id) throws NotFoundException  {
//	setup();
//	Session session = sessionFactory.openSession();
//	crudmodel crudmodel = session.get(crudmodel.class, id);
//	if (id == crudmodel.getPersonId()) {
//		return crudmodel;
//	}else {
//		throw new NotFoundException("Resource not found with Id :: " + id);
//	}
//}
