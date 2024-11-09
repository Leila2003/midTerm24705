package model;


import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends Person {
	
	@Column(name = "user_name", nullable = false)
	private String userName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Erole role;
	
	// Many-to-one relationship with Location
    @ManyToOne
    @JoinColumn(name = "village_id")
    private Location location;

    // One-to-many relationship with Borrower
    @OneToMany(mappedBy = "user")
    private List<Borrower> borrowers;

    // One-to-many relationship with Membership
    @OneToMany(mappedBy = "user")
    private List<MemberShip> memberships;

    // Default Constructor
    public User() {}

    // Parameterized Constructor
    public User(String userName, String password, Erole role, Location location, List<Borrower> borrowers, List<MemberShip> memberships) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.location = location;
        this.borrowers = borrowers;
        this.memberships = memberships;
    }

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Erole getRole() {
        return role;
    }

    public void setRole(Erole role) {
        this.role = role;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Borrower> getBorrowers() {
        return borrowers;
    }

    public void setBorrowers(List<Borrower> borrowers) {
        this.borrowers = borrowers;
    }

    public List<MemberShip> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<MemberShip> memberships) {
        this.memberships = memberships;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", location=" + location +
                ", borrowers=" + borrowers +
                ", memberships=" + memberships +
                '}';
    }
}
