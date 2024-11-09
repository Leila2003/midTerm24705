package controller;

import org.hibernate.Session;

import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.ElocationType;
import model.Location;
import util.HibernateUtil;

public class LocationController {
	 //Save a Location
    public String saveLocation(Location location) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(location);
            transaction.commit();
            return "Location saved successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Failed to save Location";
        }
        
    }
//    public Location getProvinceFromVillage(Location village) {
//        if (village == null || village.getLocationType() != ElocationType.VILLAGE) {
//            return null; // or throw an IllegalArgumentException based on your design choice
//        }
//
//        Location currentLocation = village;
//        while (currentLocation.getParentLocation() != null) {
//            if (currentLocation.getParentLocation().getLocationType() == ElocationType.PROVINCE) {
//                return currentLocation.getParentLocation();
//            }
//            currentLocation = currentLocation.getParentLocation();
//        }
//
//        return null; // No province found
//    }
//    public Location findByLocationCode(String locationCode) {
//        Location location = null;
//        try (Session session = HibernateUtil.getSession().openSession()) {
//            String hql = "FROM Location l WHERE l.locationCode = :locationCode";
//            Query<Location> query = session.createQuery(hql, Location.class);
//            query.setParameter("locationCode", locationCode);
//            location = (Location) ((org.hibernate.query.Query<?>) query).uniqueResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return location;
//    }
}
