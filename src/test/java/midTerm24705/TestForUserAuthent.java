package midTerm24705;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.UserAuthentController;

import org.junit.Test;

public class TestForUserAuthent {

	private UserAuthentController authController;

    @Before
    public void setUp() {
        authController = new UserAuthentController();
    }

    @Test
    public void testAuthenticateUser() {
        String userName = "leila"; // Use the existing user in the database
        String correctPassword = "123"; // Use the correct password for the existing user

        // Test successful authentication
        System.out.println("Testing with correct credentials: " + userName + " / " + correctPassword);
        String successMessage = authController.authenticateUser(userName, correctPassword);
        assertEquals("Authentication successful", successMessage);

        // Test failed authentication with wrong password
        String wrongPassword = "wrongpassword"; // Use an incorrect password
        System.out.println("Testing with incorrect password: " + userName + " / " + wrongPassword);
        String wrongPasswordMessage = authController.authenticateUser(userName, wrongPassword);
        assertEquals("Wrong credentials", wrongPasswordMessage);

        // Test failed authentication with wrong username
        String wrongUserName = "wronguser";
        System.out.println("Testing with incorrect username: " + wrongUserName + " / " + correctPassword);
        String wrongUserMessage = authController.authenticateUser(wrongUserName, correctPassword);
        assertEquals("Wrong credentials", wrongUserMessage);
    }
}
