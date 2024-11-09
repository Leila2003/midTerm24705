package midTerm24705;
import controller.LocatioUserController;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TestForUserLocation {
	private LocatioUserController userLocationController;

    @Before
    public void setUp() {
        userLocationController = new LocatioUserController();
    }

    @Test
    public void testGetProvinceByUserId() {
        UUID existingUserId = UUID.fromString("7aeec487-8f7f-445d-9423-a682ebffd904");

        // Retrieve the province name using UserLocationController
        String provinceName = userLocationController.getProvinceByUserId(existingUserId);

        // Print the retrieved province name to the console
        System.out.println("Retrieved Province Name: " + provinceName);

        // Assert that the province name matches the expected value
        assertEquals("Kigali", provinceName); // Replace with actual expected name
    }
}
