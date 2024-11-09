package model;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
public class MemberShip {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "membership_id", nullable = false)
    private UUID membershipId;

    @Column(name = "expiring_time", nullable = false)
    private Date expiringTime;

    @Column(name = "membership_code", nullable = false)
    private String membershipCode;

    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @Column(name = "membership_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Estatus membershipStatus;

    // Many Memberships can belong to one User over time
    @ManyToOne
    @JoinColumn(name = "reader_id")
    private User user;

    // Relationship with MembershipType (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "membership_type_id")
    private MemberShipType membershipType;

    // Default constructor
    public MemberShip() {
        super();
    }

    // Parameterized constructor
    public MemberShip(UUID membershipId, Date expiringTime, String membershipCode, Date registrationDate, Estatus membershipStatus, User user, MemberShipType membershipType) {
        this.membershipId = membershipId;
        this.expiringTime = expiringTime;
        this.membershipCode = membershipCode;
        this.registrationDate = registrationDate;
        this.membershipStatus = membershipStatus;
        this.user = user;
        this.membershipType = membershipType;
    }

    // Getters and setters
    public UUID getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(UUID membershipId) {
        this.membershipId = membershipId;
    }

    public Date getExpiringTime() {
        return expiringTime;
    }

    public void setExpiringTime(Date expiringTime) {
        this.expiringTime = expiringTime;
    }

    public String getMembershipCode() {
        return membershipCode;
    }

    public void setMembershipCode(String membershipCode) {
        this.membershipCode = membershipCode;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Estatus getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(Estatus membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MemberShipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MemberShipType membershipType) {
        this.membershipType = membershipType;
    }
}

