package com.company.service;

import java.util.List;

import com.company.entities.Cab;

public interface CabService {
	
	Cab getCabById(int id);
	List<Cab> getAllCabs();
	void saveAll(List<Cab> cab);

}
