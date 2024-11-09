package controller;

import org.hibernate.Session;

import model.Room;
import model.Shelf;
import util.HibernateUtil;

public class RoomBookController {
	 public int countBooksInRoom(Room room) {
	        int totalBooks = 0;

	        try (Session session = HibernateUtil.getSession().openSession()) {
	      
	            Room managedRoom = session.get(Room.class, room.getRoomId());
	            if (managedRoom != null) {
	               
	                for (Shelf shelf : managedRoom.getShelves()) {
	                    
	                    totalBooks += shelf.getBooks() != null ? shelf.getBooks().size() : 0;
	                }
	            } else {
	                System.out.println("Room not found.");
	            }
	        }

	        return totalBooks;
	    }

}
