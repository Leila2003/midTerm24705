package controller;
import model.User;
import model.ElocationType;
import model.Location;

import java.util.UUID;
import org.hibernate.Session;
import util.HibernateUtil;

public class LocatioUserController {
	// Method to get the province name by user ID
    public String getProvinceByUserId(UUID userId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            // Fetch the User by UUID
            User user = session.get(User.class, userId);
            if (user == null || user.getLocation() == null) {
                throw new IllegalArgumentException("User or location not found for ID: " + userId);
            }

            // Start from the user's location and move up the hierarchy to find the Province
            Location location = user.getLocation();
            while (location.getLocationType() != ElocationType.PROVINCE) {
                location = location.getParentLocation();
                if (location == null) {
                    throw new IllegalArgumentException("Province not found for the user's location.");
                }
            }

            // Return the province name
            return location.getLocationName();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching province for user ID: " + userId, e);
        }
    }
}
