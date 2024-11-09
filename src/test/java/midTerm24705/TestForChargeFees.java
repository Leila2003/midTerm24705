package midTerm24705;

import static org.junit.Assert.*;
import java.util.UUID;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import model.Book;
import model.User;
import model.Borrower;
import util.HibernateUtil;

import org.junit.Test;

import controller.ChargeFeesController;

public class TestForChargeFees {

	 private ChargeFeesController lateFeeController;
	    private UUID testUserId;
	    private UUID testBookId;
	    private UUID testBorrowerId;

	    @Before
	    public void setUp() {
	        lateFeeController = new ChargeFeesController();

	        // Use existing User and Book from the database
	        try (Session session = HibernateUtil.getSession().openSession()) {
	            session.beginTransaction();

	            // Fetch a user and a book
	            User user = session.createQuery("FROM User", User.class).setMaxResults(1).uniqueResult();
	            Book book = session.createQuery("FROM Book", Book.class).setMaxResults(1).uniqueResult();

	            if (user != null) {
	                testUserId = user.getUser_id();
	            }

	            if (book != null) {
	                testBookId = book.getBookId();
	            }

	            // Borrow the book to create a Borrower entity
	            Borrower borrower = new Borrower();
	            borrower.setUser(user);
	            borrower.setBook(book);
	            borrower.setPickupDate(new java.util.Date());
	            borrower.setDueDate(new java.util.Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)); // 7 days from now
	            borrower.setReturnDate(new java.util.Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000)); // 14 days from now
	            session.persist(borrower);
	            testBorrowerId = borrower.getBorrowerId();

	            session.getTransaction().commit();
	        }
	    }

	    @Test
	    public void testCalculateLateFees() {
	        assertNotNull("Borrower ID should not be null", testBorrowerId);

	        // Simulate a late return (e.g., 3 days late)
	        try {
	            double lateFee = lateFeeController.calculateLateFees(testBorrowerId);

	            assertTrue("Late fee should be greater than 0 if the book is late", lateFee > 0);
	            assertEquals("The late fee should be $6.0 for 3 days overdue", 6.0, lateFee, 0.01);
	        } catch (IllegalArgumentException e) {
	            fail("Failed to calculate late fees: " + e.getMessage());
	        }
	    }

	    @Test
	    public void testNoLateFeesIfReturnedOnTime() {
	        assertNotNull("Borrower ID should not be null", testBorrowerId);

	        // Simulate an on-time return (returning on the due date)
	        try {
	            Borrower borrower = HibernateUtil.getSession().get(Borrower.class, testBorrowerId);
	            borrower.setReturnDate(borrower.getDueDate()); // Set return date to be exactly due date

	            double lateFee = lateFeeController.calculateLateFees(testBorrowerId);
	            assertEquals("There should be no late fee if returned on time", 0.0, lateFee, 0.01);
	        } catch (IllegalArgumentException e) {
	            fail("Failed to calculate late fees: " + e.getMessage());
	        }
	    }

}
