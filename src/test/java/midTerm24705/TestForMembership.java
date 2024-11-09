package midTerm24705;

import static org.junit.Assert.*;
import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import controller.MembershipController;
import model.Estatus;
import model.MemberShip;
import model.MemberShipType;
import model.User;
import util.HibernateUtil;
import org.hibernate.Session;


import org.junit.Test;

public class TestForMembership {

	private MembershipController memberShipController;

    @Before
    public void setUp() {
        memberShipController = new MembershipController();
    }

    @Test
    public void testSaveMembership() {
        // Create and save a MembershipType
        MemberShipType membershipType = new MemberShipType();
        membershipType.setMembershipName("Striver");
        membershipType.setMaxBooks(2);
        membershipType.setPrice(10);
        
        // Save MembershipType
        try (Session session = HibernateUtil.getSession().openSession()) {
            session.beginTransaction();
            session.persist(membershipType);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check if a User exists in the database
        User user = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            user = session.get(User.class, UUID.fromString("7aeec487-8f7f-445d-9423-a682ebffd904")); // Replace with actual user ID
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull("User should exist in the database.", user);

        // Create a new Membership
        MemberShip membership = new MemberShip();
        membership.setExpiringTime(new Date(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000)); // 1 year from now
        membership.setMembershipCode("ME01");
        membership.setRegistrationDate(new Date());
        membership.setMembershipStatus(Estatus.APPROVED);
        membership.setUser(user); // Assign the existing user to the membership
        membership.setMembershipType(membershipType); // Assign the created MembershipType

        // Save Membership
        membership = memberShipController.saveMembership(membership);
        assertNotNull("Membership ID should not be null after saving.", membership.getMembershipId());
    }

}
