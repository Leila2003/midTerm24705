package controller;
import java.util.Date;
import java.util.UUID;
import model.Borrower;
import util.HibernateUtil;
import org.hibernate.Session;

public class ChargeFeesController {
	// Method to calculate late fee for a borrowed book
    public double calculateLateFees(UUID borrowerId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Borrower borrower = session.get(Borrower.class, borrowerId);
            if (borrower == null) {
                throw new IllegalArgumentException("Borrower with ID " + borrowerId + " not found");
            }

            Date returnDate = borrower.getReturnDate();
            Date actualReturnDate = new Date(); // For this example, we assume the book is returned now
            long overdueDays = (actualReturnDate.getTime() - returnDate.getTime()) / (1000 * 60 * 60 * 24); // Convert milliseconds to days

            if (overdueDays > 0) {
                double lateFeePerDay = 2.0; // Assume the fine is $2 per day
                return overdueDays * lateFeePerDay;
            }

            return 0.0; // No fine if the book is returned on time
        }
    }
}
