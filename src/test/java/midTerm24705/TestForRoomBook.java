package midTerm24705;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import controller.RoomBookController;
import controller.RoomController;
import controller.ShelfController;
import model.Book;
import model.Room;
import model.Shelf;
import util.HibernateUtil;

public class TestForRoomBook {

	private RoomBookController roomBook;
    private Room testRoom;
    private Shelf testShelf;
    private List<Book> testBooks; 

    @Before
    public void setUp() {
        roomBook = new RoomBookController();
        
        // Retrieve an existing Room
        try (Session session = HibernateUtil.getSession().openSession()) {
            List<Room> rooms = session.createQuery("FROM Room WHERE roomCode = :roomCode", Room.class)
                                      .setParameter("roomCode", "ROOM01")
                                      .getResultList(); 

            // If the Room does not exist, create and save it
            if (!rooms.isEmpty()) {
                testRoom = rooms.get(0); 
            } else {
                testRoom = new Room();
                testRoom.setRoomCode("ROOM02");
                
                new RoomController().saveRoom(testRoom);
            }
        }

        // Retrieve an existing Shelf
        try (Session session = HibernateUtil.getSession().openSession()) {
            List<Shelf> shelves = session.createQuery("FROM Shelf WHERE bookCategory = :bookCategory", Shelf.class)
                                          .setParameter("bookCategory", "Science")
                                          .getResultList();

            // If the Shelf does not exist, create and save it
            if (!shelves.isEmpty()) {
                testShelf = shelves.get(0); 
            } else {
                testShelf = new Shelf();
                testShelf.setBookCategory("JAVA");
                testShelf.setAvailableStock(10);
                testShelf.setInitialStock(20);
                testShelf.setBorrowedNumber(5);
                testShelf.setRoom(testRoom);
                
                new ShelfController().saveShelf(testShelf);
            }
        }

        // Retrieve books associated with the testShelf for testing
        try (Session session = HibernateUtil.getSession().openSession()) {
            testBooks = session.createQuery("FROM Book WHERE shelf.id = :shelfId", Book.class) 
                               .setParameter("shelfId", testShelf.getStockId()) 
                               .getResultList();
        }
    }

    @Test
    public void testCountBooksInRoom() {
        // Verify that testBooks has been initialized and contains data
        assertNotNull(testBooks);
        assertTrue("Expected at least one book in testBooks", testBooks.size() > 0); 
        
        // Count the books in the room 
        int bookCount = roomBook.countBooksInRoom(testRoom);
        System.out.println("Room Code: " + testRoom.getRoomCode() + " Total books are: " + bookCount);
        
       
        assertEquals(testBooks.size(), bookCount); 
    }

}
