package model;

import java.util.UUID;
import org.hibernate.annotations.*;

import jakarta.persistence.*;


@MappedSuperclass
public abstract class Person {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator
	(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	private UUID user_id;
	private String first_name;
	private String last_name;
	@Enumerated(EnumType.STRING)
	private Egender Gender;
	private String phone_number;
	
	public Person() {
		super();
	}

	public Person(UUID user_id, String first_name, String last_name, Egender gender, String phone_number) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		Gender = gender;
		this.phone_number = phone_number;
	}

	public UUID getUser_id() {
		return user_id;
	}

	public void setUser_id(UUID user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Egender getGender() {
		return Gender;
	}

	public void setGender(Egender gender) {
		Gender = gender;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	

}