package model;


import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID book_id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "edition", nullable = false)
    private int edition;

    @Column(name = "isbncode", nullable = false)
    private String ISBNCode;

    @Column(name = "publisher_name", nullable = false)
    private String publisherName;

    @Column(name = "publication_year", nullable = false)
    private Date publicationYear;

    @Column(name = "book_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Ebook bookStatus;

    // One-to-many relationship with Borrower
    @OneToMany(mappedBy = "book")
    private List<Borrower> borrowers;

    // Many-to-one relationship with Shelf
    @ManyToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;

    // Default Constructor
    public Book() {}

    // Parameterized Constructor
    public Book(UUID book_id, String title, int edition, String ISBNCode, String publisherName, Date publicationYear, Ebook bookStatus, List<Borrower> borrowers, Shelf shelf) {
        this.book_id = book_id;
        this.title = title;
        this.edition = edition;
        this.ISBNCode = ISBNCode;
        this.publisherName = publisherName;
        this.publicationYear = publicationYear;
        this.bookStatus = bookStatus;
        this.borrowers = borrowers;
        this.shelf = shelf;
    }

    // Getters and Setters
    public UUID getBookId() {
        return book_id;
    }

    public void setBookId(UUID book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getISBNCode() {
        return ISBNCode;
    }

    public void setISBNCode(String ISBNCode) {
        this.ISBNCode = ISBNCode;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Date getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Date publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Ebook getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Ebook bookStatus) {
        this.bookStatus = bookStatus;
    }

    public List<Borrower> getBorrowers() {
        return borrowers;
    }

    public void setBorrowers(List<Borrower> borrowers) {
        this.borrowers = borrowers;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", edition=" + edition +
                ", ISBNCode='" + ISBNCode + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", publicationYear=" + publicationYear +
                ", bookStatus=" + bookStatus +
                ", borrowers=" + borrowers +
                ", shelf=" + shelf +
                '}';
    }
}

