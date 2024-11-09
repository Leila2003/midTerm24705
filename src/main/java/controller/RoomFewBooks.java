package controller;

import java.util.List;

import org.hibernate.Session;

import model.Room;
import model.Shelf;
import util.HibernateUtil;

public class RoomFewBooks {
	public Room getRoomWithShelfHavingFewBooks() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            
            List<Shelf> shelves = session.createQuery(
                    "SELECT s FROM Shelf s JOIN FETCH s.room LEFT JOIN FETCH s.books", Shelf.class)
                    .list();

            Shelf shelfWithFewestBooks = null;
            int minBookCount = Integer.MAX_VALUE;

            
            for (Shelf shelf : shelves) {
                int bookCount = shelf.getBooks().size();
                if (bookCount < minBookCount) {
                    minBookCount = bookCount;
                    shelfWithFewestBooks = shelf;
                }
            }

            
            return (shelfWithFewestBooks != null) ? shelfWithFewestBooks.getRoom() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
