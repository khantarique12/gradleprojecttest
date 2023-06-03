package gradle.project.HibernateExample;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BookManager {

	protected SessionFactory sessionFactory;

	protected void setup() {
		// A SessionFactory is set up once for an Application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure("/gradle/project/HibernateExample/hibernate.cfg.xml") // configures settings from
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
		Book book = new Book();
		book.setTitle("Core Java");
		book.setAuthor("Gary Cornell");
		book.setPrice(30.00f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(book);

		session.getTransaction().commit();

		System.out.println("Inserted Data!");
		session.close();
	}

	protected void update() {
		Book book = new Book();
		book.setId(1);
		book.setTitle("Core Java");
		book.setAuthor("Gary Cornell");
		book.setPrice(36.50f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.update(book);

		session.getTransaction().commit();
		session.close();
	}

	protected void read() {

		Session session = sessionFactory.openSession();
		long bookId = 3;
		Book book = session.get(Book.class, bookId);

		if (book != null) {
			System.out.println("Title:" + book.getTitle());
			System.out.println("Author:" + book.getAuthor());
			System.out.println("Price:" + book.getPrice());
		} else {
			System.out.println("Book Not Found");
		}

		session.close();
	}

	protected void delete() {

		Session session = sessionFactory.openSession();
		Book book = new Book();
		book.setId(3);
		session.delete(book);
		session.beginTransaction();
		session.getTransaction().commit();
		System.out.println("Delete Entry Successflly!");
		session.close();
	}

	public static void main(String[] args) {
		BookManager manager = new BookManager();
		manager.setup();
		// manager.create();
		// manager.read();
		// manager.update();
		manager.delete();
		manager.exit();
	}

}
