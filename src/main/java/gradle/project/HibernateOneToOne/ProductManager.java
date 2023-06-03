package gradle.project.HibernateOneToOne;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ProductManager {

	protected SessionFactory sessionFactory;

	protected void setup() {
		// A SessionFactory is set up once for an Application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure("/gradle/project/HibernateOneToOne/hibernate.cfg.xml") // configures settings from
																					// hibernate.cfg.xml
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
	}

	protected void exit() {
		sessionFactory.close();
	}

	protected void create() {

		// creates a new product
		Product product = new Product();
		product.setName("Civic");
		product.setDescription("Comfortable, fuel-saving car");
		product.setPrice(20000);

		// creates product detail
		ProductDetail detail = new ProductDetail();
		detail.setPartNumber("ABCDEFGHIJKL");
		detail.setDimension("2,5m x 1,4m x 1,2m");
		detail.setWeight(1000);
		detail.setManufacturer("Honda Automobile");
		detail.setOrigin("Japan");

		// sets the bi-directional association
		product.setProductDetail(detail);
		detail.setProduct(product);
		// obtains the session

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// persists the product
		session.save(product);

		// queries all products
		@SuppressWarnings("unchecked")
		List<Product> listProducts = session.createQuery("from Product").list();
		for (Product aProd : listProducts) {
			String info = "Product: " + aProd.getName() + "\n";
			info += "\tDescription: " + aProd.getDescription() + "\n";
			info += "\tPrice: $" + aProd.getPrice() + "\n";

			ProductDetail aDetail = aProd.getProductDetail();
			info += "\tPart number: " + aDetail.getPartNumber() + "\n";
			info += "\tDimension: " + aDetail.getDimension() + "\n";
			info += "\tWeight: " + aDetail.getWeight() + "\n";
			info += "\tManufacturer: " + aDetail.getManufacturer() + "\n";
			info += "\tOrigin: " + aDetail.getOrigin() + "\n";

			System.out.println(info);
		}

		session.getTransaction().commit();
		session.close();
	}

	protected void read() {

	}

	protected void update() {

	}

	protected void delete() {
		Session session = sessionFactory.openSession();
		// creates a new product
		Product product = new Product();
		// creates product detail
		ProductDetail detail = new ProductDetail();
		product.setProductId(2);
		// sets the bi-directional association
		product.setProductDetail(detail);
		detail.setProduct(product);

		session.delete(product);
		session.beginTransaction();
		session.getTransaction().commit();
		System.out.println("Delete Entry Successflly!");
		session.close();

	}

	public static void main(String[] args) {
		ProductManager manager = new ProductManager();
		manager.setup();
		manager.create();
		// manager.delete();
		manager.exit();
	}

}
