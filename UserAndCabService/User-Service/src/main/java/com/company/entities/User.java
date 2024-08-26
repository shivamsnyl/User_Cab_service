package com.company.entities;

import java.util.List;

import com.company.dto.Cab;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
	
	@Id
	private int id;
	private String name;
	private String address;
	private int rating;
	
	@Transient
	private List<Cab> cabs;

	public User(int id, String name, String address, int rating) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.rating = rating;
	}
	
	

}
