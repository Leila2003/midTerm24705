package model;


import java.util.*;
import org.hibernate.annotations.*;

import jakarta.persistence.*;

@Entity
public class MemberShipType {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID membership_type_id;

    @Column(name = "membership_name", nullable = false)
    private String membershipName;

    @Column(name = "max_books", nullable = false)
    private int maxBooks;

    @Column(name = "price", nullable = false)
    private int price;

    // One MembershipType can have many Memberships
    @OneToMany(mappedBy = "membershipType")
    private List<MemberShip> memberships;

    // Default Constructor
    public MemberShipType() {}

    // Parameterized Constructor
    public MemberShipType(UUID membership_type_id, String membershipName, int maxBooks, int price, List<MemberShip> memberships) {
        this.membership_type_id = membership_type_id;
        this.membershipName = membershipName;
        this.maxBooks = maxBooks;
        this.price = price;
        this.memberships = memberships;
    }

    // Getters and Setters
    public UUID getMembershipTypeId() {
        return membership_type_id;
    }

    public void setMembershipTypeId(UUID membership_type_id) {
        this.membership_type_id = membership_type_id;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public int getMaxBooks() {
        return maxBooks;
    }

    public void setMaxBooks(int maxBooks) {
        this.maxBooks = maxBooks;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<MemberShip> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<MemberShip> memberships) {
        this.memberships = memberships;
    }

    @Override
    public String toString() {
        return "MemberShipType{" +
                "membership_type_id=" + membership_type_id +
                ", membershipName='" + membershipName + '\'' +
                ", maxBooks=" + maxBooks +
                ", price=" + price +
                ", memberships=" + memberships +
                '}';
    }
}
