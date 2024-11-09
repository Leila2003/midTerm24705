package controller;

import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;

import org.hibernate.Transaction;
import model.ElocationType;
import model.Location;

import model.User;
import util.HibernateUtil;

public class UsersController {
	 // Create a new User account with hashed password
    public User createUser(User user) {
        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword); // Set the hashed password
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user); // Save user to the database
            transaction.commit();
        }
        return user; // Return the saved user with the ID set
    }

    // Hash the password using BCrypt
    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    
    public Location getLocationByCode(String locationCode) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("FROM Location WHERE locationCode = :code AND locationType = :type", Location.class)
                          .setParameter("code", locationCode)
                          .setParameter("type", ElocationType.VILLAGE)
                          .uniqueResult();
        }
    }
}
