//package gradle.project.HibernateJUnit5TestExample;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import gradle.project.HibernateJUnit5Example.HibernateUtil;
//import gradle.project.HibernateJUnit5Example.Product;
//
//public class HibernateUtilTest {
//
//	private static SessionFactory sessionFactory;
//	private Session session;
//
//	@BeforeAll
//	public static void setupBeforeClass() {
//		sessionFactory = HibernateUtil.getSessionFactory();
//		System.out.println("SessionFactory created");
//	}
//
//	@BeforeEach
//	public void openSession() {
//		session = sessionFactory.openSession();
//		System.out.println("Session created");
//	}
//
//	@Test
//	public void testCreate() {
//		System.out.println("Running testCreate...");
//
//		session.beginTransaction();
//		Product product = new Product("Dell XPS 9360", 1200);
//		Integer id = (Integer) session.save(product);
//
//		session.getTransaction().commit();
//
//		Assertions.assertTrue(id > 0);
//	}
//
//	@Test
//	public void testUpdate() {
//		System.out.println("Running testUpdate...");
//
//		Integer id = 3;
//		Product product = new Product(id, "Dell XPS 9365", 1250);
//
//		session.beginTransaction();
//		session.update(product);
//		session.getTransaction().commit();
//
//		Product updatedProduct = session.find(Product.class, id);
//
//		assertEquals("iPhone 11", updatedProduct.getName());
//	}
//
//	@Test
//	public void testGet() {
//		System.out.println("Running testGet...");
//
//		Integer id = 3;
//
//		Product product = session.find(Product.class, id);
//
//		assertEquals("Dell XPS 9365", product.getName());
//	}
//
//	@Test
//	public void testList() {
//		System.out.println("Running testList...");
//
//		org.hibernate.query.Query<Product> query = session.createQuery("from Product", Product.class);
//		List<Product> resultList = query.getResultList();
//
//		Assertions.assertFalse(resultList.isEmpty());
//	}
//
//	@Test
//	public void testDelete() {
//		System.out.println("Running testDelete...");
//
//		Integer id = 2;
//		Product product = session.find(Product.class, id);
//
//		session.beginTransaction();
//		session.delete(product);
//		session.getTransaction().commit();
//
//		Product deletedProduct = session.find(Product.class, id);
//
//		Assertions.assertNull(deletedProduct);
//	}
//
//	@AfterEach
//	public void closeSession() {
//		if (session != null)
//			session.close();
//		System.out.println("Session closed\n");
//	}
//
//	@AfterAll
//	public static void tearDown() {
//		if (sessionFactory != null)
//			sessionFactory.close();
//		System.out.println("SessionFactory destroyed");
//	}
//}