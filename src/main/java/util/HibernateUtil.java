package util;

import java.util.Properties;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import model.MemberShip;
import model.Borrower;
import model.MemberShipType;
import model.Location;
import model.Book;
import model.Room;
import model.Shelf;
import model.User;
import model.Person;

public class HibernateUtil {
private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSession() {
		if(sessionFactory == null) {
		Configuration configuration = new Configuration();
		Properties property = new Properties();
		property.put(Environment.URL, "jdbc:postgresql://localhost:5432/auca_library_db");
		property.put(Environment.USER, "postgres");
		property.put(Environment.PASS, "mugeni@123");
		property.put(Environment.SHOW_SQL, "true");
		property.put(Environment.HBM2DDL_AUTO, "update");
		
		configuration.addProperties(property);
		configuration.addAnnotatedClass(Book.class);
		configuration.addAnnotatedClass(Borrower.class);
		configuration.addAnnotatedClass(Location.class);
		configuration.addAnnotatedClass(MemberShip.class);
		configuration.addAnnotatedClass(Person.class);
		configuration.addAnnotatedClass(MemberShipType.class);
		configuration.addAnnotatedClass(Room.class);
		configuration.addAnnotatedClass(Shelf.class);
		configuration.addAnnotatedClass(User.class);
		
		
		
		sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
		}
		else {
			return sessionFactory;
		}
	}
}
