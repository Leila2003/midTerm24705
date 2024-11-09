package controller;

import org.hibernate.Session;

import org.hibernate.Transaction;

import model.MemberShip;
import util.HibernateUtil;

public class MembershipController {
	// Save a MemberShip
    public MemberShip saveMembership(MemberShip membership) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(membership); // Save membership to the database
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace(); // Handle any exceptions that occur during the process
        }
        return membership; // Return the saved membership with the ID set
    }
}
