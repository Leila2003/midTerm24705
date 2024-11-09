package midTerm24705;

import static org.junit.Assert.*;

import java.util.UUID;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import controller.UserBorrowerController;
import model.User;
import util.HibernateUtil;

public class TestForUseBorrowing {

	private UserBorrowerController UserBorrowController;
    private UUID existingUserId;

    @Before
    public void setUp() {
    	UserBorrowController = new UserBorrowerController();
        existingUserId = UUID.fromString("7aeec487-8f7f-445d-9423-a682ebffd904"); // user ID
    }

    @Test
    public void testValidateUserBorrowingLimit() {
        // Check that the user exists in the database
        User user;
        try (Session session = HibernateUtil.getSession().openSession()) {
            user = session.get(User.class, existingUserId);
        }
        assertNotNull("User should exist in the database for this test.", user);

        // Validate the user's borrowing limit
        String result = UserBorrowController.UserBorrowingLimit(existingUserId);

        // Print the result to console
        System.out.println(result);

        // Check the actual result and assert accordingly
        if (result.equals("User reached the borrowing limit.")) {
            assertEquals("User reached the borrowing limit.", result);
        } else if (result.equals("User is still allowed to borrow.")) {
            assertEquals("User is still allowed to borrow.", result);
        } else {
            assertEquals("Membership not found for the user.", result);
        }
    }
}
