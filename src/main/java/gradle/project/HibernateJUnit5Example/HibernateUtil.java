package gradle.project.HibernateJUnit5Example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	public static SessionFactory getSessionFactory() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure("/gradle/project/HibernateJUnit5Test/hibernate.cfg.xml") // configures settings from
																					// hibernate.cfg.xml
				.build();

		SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

		return factory;
	}
}