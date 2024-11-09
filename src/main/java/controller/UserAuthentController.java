package controller;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;
import util.HibernateUtil;
import model.User;

public class UserAuthentController {
	// Method to authenticate user with a username and password
    public String authenticateUser(String userName, String password) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            // Fetch the user from the database
            Query<User> query = session.createQuery("FROM User WHERE userName = :userName", User.class);
            query.setParameter("userName", userName);
            User user = query.uniqueResult();

            // Check if user exists and validate the password
            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                return "Authentication successful"; // Successful authentication
            } else {
                return "Wrong credentials"; // Generic error message for failure
            }
        }
    }
}
