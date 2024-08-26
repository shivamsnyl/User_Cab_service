package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.entities.Cab;
import com.company.repository.CabRepository;

@Service
public class CabServiceImpl implements CabService{

	@Autowired
	CabRepository repository;
	
	@Override
	public Cab getCabById(int id) {
		return repository.findById(id).isPresent()? repository.findById(id).get():null;
	}

	@Override
	public List<Cab> getAllCabs() {
		return (List<Cab>) repository.findAll();
	}

	@Override
	public void saveAll(List<Cab> cab) {
		repository.saveAll(cab);
	}
	
	

}
