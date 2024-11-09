package midTerm24705;

import static org.junit.Assert.*;

import java.util.UUID;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import controller.BorrowerController;
import model.Book;
import model.User;
import util.HibernateUtil;

public class TestForBorrowingBook {

	private BorrowerController BorrowingController;
    private UUID testUserId;
    private UUID testBookId;

    @Before
    public void setUp() {
        BorrowingController = new BorrowerController();

        // Use existing User and Book from the database
        try (Session session = HibernateUtil.getSession().openSession()) {
            session.beginTransaction();

            // Assuming you have a method to fetch a user and a book
            User user = session.createQuery("FROM User", User.class).setMaxResults(1).uniqueResult();
            Book book = session.createQuery("FROM Book", Book.class).setMaxResults(1).uniqueResult();

            if (user != null) {
                testUserId = user.getUser_id();
            }

            if (book != null) {
                testBookId = book.getBookId();
            }

            session.getTransaction().commit();
        }
    }

    @Test
    public void testBorrowBook() {
        assertNotNull("User ID should not be null", testUserId);
        assertNotNull("Book ID should not be null", testBookId);

        // Borrow the book
        BorrowingController.borrowBook(testUserId, testBookId);

        // Verify that the borrow record is created successfully
        try (Session session = HibernateUtil.getSession().openSession()) {
            session.beginTransaction();

            // Fetch the user and book entities for verification
            long count = (long) session.createQuery(
                    "SELECT COUNT(b) FROM Borrower b WHERE b.user.user_id = :userId AND b.book.book_id = :bookId", Long.class)
                .setParameter("userId", testUserId)
                .setParameter("bookId", testBookId)
                .uniqueResult();

            session.getTransaction().commit();

            assertEquals("The book should be borrowed successfully", 1, count);
        }
    }

}
