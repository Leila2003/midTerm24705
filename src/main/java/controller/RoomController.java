package controller;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Room;
import util.HibernateUtil;

public class RoomController {
	// Save a Room
	  public Room saveRoom(Room room) {
	        try (Session session = HibernateUtil.getSession().openSession()) {
	            Transaction transaction = session.beginTransaction();
	            session.persist(room); 
	            transaction.commit();
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        }
	        return room; 
	    }

}
