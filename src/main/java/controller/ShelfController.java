package controller;

import org.hibernate.Session;

import org.hibernate.Transaction;

import model.Room;
import model.Shelf;
import util.HibernateUtil;

public class ShelfController {
	//  ShelfController
		public void assignShelfToRoom(Room room, Shelf shelf) {
		    if (room != null && shelf != null) {
		        room.assignShelf(shelf);
		        ShelfController shelfController = new ShelfController();
		        shelfController.saveShelf(shelf); 
		    } else {
		        throw new IllegalArgumentException("Room and Shelf cannot be null");
		    }
		}

		public Shelf saveShelf(Shelf shelf) {
		    try (Session session = HibernateUtil.getSession().openSession()) {
		        Transaction transaction = session.beginTransaction();
		        session.persist(shelf); 
		        transaction.commit();
		    } catch (Exception e) {
		        e.printStackTrace(); 
		    }
		    return shelf; 
		}

}
