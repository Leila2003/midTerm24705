package model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.*;

import jakarta.persistence.*;


@Entity
public class Room {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	private UUID room_id;
	
	@Column(name = "room_code", nullable = false)
	private String roomCode;
	
	// One-to-many relationship with Shelf
    @OneToMany(mappedBy = "room",fetch = FetchType.EAGER)
    private List<Shelf> shelves;

    // Default Constructor
    public Room() {
    	this.shelves = new ArrayList<>();
    }

    // Parameterized Constructor
    public Room(UUID room_id, String roomCode, List<Shelf> shelves) {
        this.room_id = room_id;
        this.roomCode = roomCode;
        this.shelves = shelves;
    }

    // Getters and Setters
    public UUID getRoomId() {
        return room_id;
    }

    public void setRoomId(UUID room_id) {
        this.room_id = room_id;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + room_id +
                ", roomCode='" + roomCode + '\'' +
                ", shelves=" + shelves +
                '}';
    }
    
    public void assignShelf(Shelf shelf) {
        // Set this room as the shelf's room
        shelf.setRoom(this);

        // Add the shelf to this room's shelves list if not already present
        if (!this.shelves.contains(shelf)) {
            this.shelves.add(shelf);
        }
    }
}
