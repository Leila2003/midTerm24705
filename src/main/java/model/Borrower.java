package model;

import java.util.Date;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
public class Borrower {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "borrower_id", nullable = false)
    private UUID borrowerId;

    @Column(name = "due_date", nullable = false)
    private Date dueDate;

    @Column(name = "pickup_date", nullable = false)
    private Date pickupDate;

    @Column(name = "return_date", nullable = true)
    private Date returnDate;

    @Column(name = "fine", nullable = false)
    private int fine;

    // Many-to-one relationship with User (reader)
    @ManyToOne
    @JoinColumn(name = "reader_id")
    private User user;

    // Many-to-one relationship with Book
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    // Default Constructor
    public Borrower() {}

    // Parameterized Constructor
    public Borrower(UUID borrowerId, Date dueDate, Date pickupDate, Date returnDate, int fine, User user, Book book) {
        this.borrowerId = borrowerId;
        this.dueDate = dueDate;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.fine = fine;
        this.user = user;
        this.book = book;
    }

    // Getters and Setters
    public UUID getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(UUID borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "borrowerId=" + borrowerId +
                ", dueDate=" + dueDate +
                ", pickupDate=" + pickupDate +
                ", returnDate=" + returnDate +
                ", fine=" + fine +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
