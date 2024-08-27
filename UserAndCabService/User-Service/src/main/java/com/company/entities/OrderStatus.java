package com.company.entities;

import java.util.List;

import com.company.dto.Cab;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {
	
	private List<Cab> cabs;
	private String status;
	private String message;

}
