package midTerm24705;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import controller.RoomFewBooks;
import model.Room;
import util.HibernateUtil;

public class TestForRoomWithFewBooks {

	 @Before
	    public void setUp() {
	        new RoomFewBooks();
	    }

	    @Test
	    public void testGetRoomWithShelfHavingFewestBooks() {
	        try (Session session = HibernateUtil.getSession().openSession()) {
	            session.beginTransaction();

	            RoomFewBooks controller = new RoomFewBooks();
	            Room roomWithFewBooks = controller.getRoomWithShelfHavingFewBooks();

	            // Assertions
	            assertNotNull(roomWithFewBooks);

	            // Display the room code
	            System.out.println("Room  with a Few Books: " + roomWithFewBooks.getRoomCode()); 

	            session.getTransaction().commit();
	        }
	    }

}
