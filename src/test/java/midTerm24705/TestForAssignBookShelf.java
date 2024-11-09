package midTerm24705;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.UUID;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import controller.AssignBookShelfController;
import model.Book;
import model.Ebook;
import model.Shelf;
import util.HibernateUtil;

public class TestForAssignBookShelf {

	private AssignBookShelfController assignBookToShelf;
    private Shelf testShelf;

    @Before
    public void setUp() {
    	assignBookToShelf = new AssignBookShelfController();

        // Retrieve an existing Shelf from the database
        try (Session session = HibernateUtil.getSession().openSession()) {
            // Replace with the actual Shelf ID you want to use
            UUID shelfId = UUID.fromString("da232f1f-2943-47d4-9520-eec742c19cd5");
            testShelf = session.get(Shelf.class, shelfId);

            //when shelf is not found
            if (testShelf == null) {
                System.out.println("Shelf with ID " + shelfId + " not found. Please choose another one.");
                fail("Shelf not found");
            }
        }
    }

    @Test
    public void testAssignBookToShelf() {
        // Create a Book 
        Book testBook = new Book();
        testBook.setTitle("Introduction to science");
        testBook.setEdition(40);
        testBook.setISBNCode("12345");
        testBook.setPublisherName("Leila Ahadi");
        testBook.setPublicationYear(new Date());
        testBook.setBookStatus(Ebook.AVAILABLE); // Assume Ebook is an enum

        // Assign the Book to the Shelf
        assignBookToShelf.assignBookToShelf(testShelf, testBook);

        // Verify that the Book is saved correctly with the Shelf id
        try (Session session = HibernateUtil.getSession().openSession()) {
            Book savedBook = session.get(Book.class, testBook.getBookId());
            assertNotNull(savedBook);
            assertEquals(testShelf.getStockId(), savedBook.getShelf().getStockId()); // Ensure the book has the correct shelf_id
        }
    }
}
