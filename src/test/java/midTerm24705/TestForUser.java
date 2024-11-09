package midTerm24705;

import static org.junit.Assert.*;
import model.User;
import model.Egender;
import model.Erole;
import model.Location; 
import org.junit.Before;
import org.junit.Test;

import controller.UsersController;

import org.junit.Test;

public class TestForUser {

	 private UsersController userController;

	    @Before
	    public void setUp() {
	        userController = new UsersController();
	    }


	    @Test
	    public void testCreateUser() {
	        // Attempt to fetch the specified Village Location
	        Location location = userController.getLocationByCode("VI01");

	        //  If the Village is not found
	        if (location == null) {
	            System.out.println("The specified village (VI01) was not found in the database. Please specify an existing village.");
	            return; // Exit the test as the specific village is not available
	        }

	        //  Create a User and assign the found Location
	        User user = new User();
	        user.setFirst_name("Leila");
	        user.setLast_name("Ahadi");
	        user.setGender(Egender.FEMALE);
	        user.setPhone_number("0789139940");
	        user.setUserName("leila");
	        user.setPassword("123");
	        user.setRole(Erole.STUDENT);
	        user.setLocation(location); // Assign the existing village location

	        // Step 4: Save the User and assert that it has an ID
	        User savedUser = userController.createUser(user);
	        assertNotNull("User ID should not be null after saving", savedUser.getUser_id());
	    }

}
