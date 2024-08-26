package com.company.controller;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.entities.Cab;
import com.company.service.CabService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/cab")
public class CabServiceController {
	
	@Autowired
	CabService service;
	
	@GetMapping("/{id}")
	ResponseEntity<Cab> findCabById(@PathVariable int id){
		return new ResponseEntity<>(service.getCabById(id),HttpStatus.OK);
	}
	
	@GetMapping
	ResponseEntity<List<Cab>> findAllCabs(){
		return new ResponseEntity<>(service.getAllCabs(),HttpStatus.OK);
	}
	
	@PostConstruct
	public void loadCabData() {
		
		List<Cab> list = Stream.of(new Cab(1000, "Swift DXI", "Shyam", 4),new Cab(1001, "Swift Dzire", "RamBabu", 4), 
				new Cab(1002, "Waganor", "Raju", 4), new Cab(1003, "Ertiga", "Munshi", 6)).toList();
		
		service.saveAll(list);
	}

}
