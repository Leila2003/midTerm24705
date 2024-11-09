package controller;

import java.util.UUID;

import org.hibernate.Session;

import model.MemberShip;
import util.HibernateUtil;

public class UserBorrowerController {
	public String UserBorrowingLimit(UUID userId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            // Fetch the user's membership and check if it exists
            MemberShip membership = session.createQuery("FROM MemberShip m WHERE m.user.user_id = :userId", MemberShip.class)
                                           .setParameter("userId", userId)
                                           .uniqueResult();

            if (membership == null) {
                return "Membership not found for the user.";
            }

            //  the user's current borrowed book count
            Long borrowedBookCount = session.createQuery("SELECT COUNT(b) FROM Borrower b WHERE b.user.user_id = :userId", Long.class)
                                            .setParameter("userId", userId)
                                            .uniqueResult();

            //  the maximum number of books allowed by the membership type
            int maxBooksAllowed = membership.getMembershipType().getMaxBooks();

            // Check if the borrowed book count exceeds the membership limit
            if (borrowedBookCount >= maxBooksAllowed) {
                return "User  reached the borrowing limit.";
            } else {
                return "User is still allowed to borrow.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error validating user's borrowing limit.";
        }
    }
}
