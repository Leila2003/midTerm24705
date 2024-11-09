package model;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.*;

import jakarta.persistence.*;


@Entity
public class Shelf {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	private UUID stock_id;
	
	@Column(name = "book_category", nullable = false)
	private String bookCategory;
	
	@Column(name = "available_stock", nullable = false)
	private int availableStock;
	
	@Column(name = "initial_stock", nullable = false)
	private int initialStock;
	
	@Column(name = "borrowed_number", nullable = false)
	private int borrowedNumber;
	
	// One-to-many relationship with Book
    @OneToMany(mappedBy = "shelf")
    private List<Book> books;
    
    // Many-to-one relationship with Room
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    // Default Constructor
    public Shelf() {}

    // Parameterized Constructor
    public Shelf(UUID stock_id, String bookCategory, int availableStock, int initialStock, int borrowedNumber, List<Book> books, Room room) {
        this.stock_id = stock_id;
        this.bookCategory = bookCategory;
        this.availableStock = availableStock;
        this.initialStock = initialStock;
        this.borrowedNumber = borrowedNumber;
        this.books = books;
        this.room = room;
    }

    // Getters and Setters
    public UUID getStockId() {
        return stock_id;
    }

    public void setStockId(UUID stock_id) {
        this.stock_id = stock_id;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public int getInitialStock() {
        return initialStock;
    }

    public void setInitialStock(int initialStock) {
        this.initialStock = initialStock;
    }

    public int getBorrowedNumber() {
        return borrowedNumber;
    }

    public void setBorrowedNumber(int borrowedNumber) {
        this.borrowedNumber = borrowedNumber;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "stock_id=" + stock_id +
                ", bookCategory='" + bookCategory + '\'' +
                ", availableStock=" + availableStock +
                ", initialStock=" + initialStock +
                ", borrowedNumber=" + borrowedNumber +
                ", books=" + books +
                ", room=" + room +
                '}';
    }
}
