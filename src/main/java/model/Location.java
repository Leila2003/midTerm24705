package model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.*;

import jakarta.persistence.*;
import model.ElocationType;

@Entity
@Table(name = "location")
public class Location {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "location_id")
    private UUID locationId;
    
    @Column(name = "location_code", nullable = false)
    private String locationCode;
    
    @Column(name = "location_name", nullable = false)
    private String locationName;
    
    @Column(name = "location_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ElocationType locationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_location_id", nullable = true)
    private Location parentLocation;

    @OneToMany(mappedBy = "parentLocation", fetch = FetchType.LAZY)
    private Set<Location> childLocations = new HashSet<>();
	
    // One-to-many relationship with User
	@OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

	public Location(UUID locationId, String locationCode, String locationName, ElocationType locationType,
			Location parentLocation, Set<Location> childLocations, Set<User> users) {
		super();
		this.locationId = locationId;
		this.locationCode = locationCode;
		this.locationName = locationName;
		this.locationType = locationType;
		this.parentLocation = parentLocation;
		this.childLocations = childLocations;
		this.users = users;
	}


	public Location() {
		super();
	}

	
	public UUID getLocationId() {
		return locationId;
	}


	public void setLocationId(UUID locationId) {
		this.locationId = locationId;
	}


	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public ElocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(ElocationType locationType) {
		this.locationType = locationType;
	}

	public Location getParentLocation() {
		return parentLocation;
	}

	public void setParentLocation(Location parentLocation) {
        this.parentLocation = parentLocation;
        if (parentLocation != null) {
            parentLocation.getChildLocations().add(this);
        }
    }

	public Set<Location> getChildLocations() {
		return childLocations;
	}

	public void setChildLocations(Set<Location> childLocations) {
		this.childLocations = childLocations;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}