package controller;

import java.util.Date;
import java.util.UUID;

import org.hibernate.Session;

import org.hibernate.Transaction;

import model.Book;
import model.Borrower;
import model.User;
import util.HibernateUtil;

public class BorrowerController {
	public void borrowBook(UUID userId, UUID bookId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            session.beginTransaction();

            
            User user = session.get(User.class, userId);
            if (user == null) {
                throw new IllegalArgumentException("User with ID " + userId + " not found");
            }

            // Check if Book exists
            Book book = session.get(Book.class, bookId);
            if (book == null) {
                throw new IllegalArgumentException("Book with ID " + bookId + " not found");
            }

            // Create and save Borrower instance if both User and Book exist
            Borrower borrower = new Borrower();
            borrower.setUser(user);
            borrower.setBook(book);
            borrower.setPickupDate(new Date());
            // Setting due date to 14 days from pickup
            borrower.setDueDate(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000));
            borrower.setReturnDate(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000));
            borrower.setFine(20); 

            session.persist(borrower);
            session.getTransaction().commit();
        }
    }
}
