package com.company.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cab {

	private int id;
	private String model;
	private String driverName;
	private int capacity;

}
