package midTerm24705;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import controller.RoomController;
import controller.ShelfController;
import model.Room;
import model.Shelf;
import util.HibernateUtil;

public class TestForShelf {

	 private ShelfController shelfController;
	    private RoomController roomController;
	    private Room testRoom;

	    @Before
	    public void setUp() {
	        shelfController = new ShelfController();
	        roomController = new RoomController();
	        
	        // Create and save a Room for testing
	        testRoom = new Room();
	        testRoom.setRoomCode("ROOM01");
	        testRoom = roomController.saveRoom(testRoom); 
	    }

	    @Test
	    public void testAssignShelfToRoom() {
	        // Create a Shelf instance
	        Shelf testShelf = new Shelf();
	        testShelf.setBookCategory("Science");
	        testShelf.setAvailableStock(20);
	        testShelf.setInitialStock(30);
	        testShelf.setBorrowedNumber(5);

	        // Assign the Shelf to the Room and save it
	        shelfController.assignShelfToRoom(testRoom, testShelf);

	        // Verify that the Shelf is saved correctly with the Room id
	        try (Session session = HibernateUtil.getSession().openSession()) {
	            Shelf savedShelf = session.get(Shelf.class, testShelf.getStockId());
	            assertNotNull(savedShelf);
	            assertEquals(testRoom.getRoomId(), savedShelf.getRoom().getRoomId()); // Ensure the shelf has the correct room_id
	        }
	    }

}
