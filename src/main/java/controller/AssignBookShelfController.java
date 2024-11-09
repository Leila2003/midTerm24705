package controller;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import model.Book;
import model.Shelf;

public class AssignBookShelfController {
	public void assignBookToShelf(Shelf shelf, Book book) {
	    book.setShelf(shelf); 
	    // Save the book to the database
	    try (Session session = HibernateUtil.getSession().openSession()) {
	        session.beginTransaction();
	        session.persist(book);
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
