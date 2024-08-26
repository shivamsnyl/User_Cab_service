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

import com.company.dto.Cab;
import com.company.entities.User;
import com.company.service.CabServiceFeign;
import com.company.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
public class UserServiceController {
	
	@Autowired
	UserService service;
	
	@Autowired
	CabServiceFeign feign;
	
	
	 @GetMapping("/all/cabs") 
	 @CircuitBreaker(name="cabCB", fallbackMethod = "defaultCabs")
	 //@Retry(name="cabCB", fallbackMethod = "defaultCabs")
	 ResponseEntity<List<Cab>> getAllCabs(){ 
		 System.out.println("Retry called");
		 return new ResponseEntity<>(feign.getAllCabs(),HttpStatus.OK); 
		 }
	 
	 ResponseEntity<List<Cab>> defaultCabs(Exception e){
			 List<Cab> list = Stream.of(new Cab(1000, "Swift", "Totla", 4)).toList();
			 return new ResponseEntity<>(list,HttpStatus.OK);
		}
	 
	
	
	@GetMapping("/{id}")
	ResponseEntity<User> getUserById(@PathVariable int id){
		return new ResponseEntity<>(service.findUserById(id),HttpStatus.OK);
	}
	
	@GetMapping()
	ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<>(service.findAllUser(),HttpStatus.OK);
	}
	
	@PostConstruct
	public void loadUserData() {
		List<User> list = Stream.of(new User(100,"Shivam","Bihar",5), new User(101,"Sunil","Bihar",4),
				new User(103,"Somen","WB",4), new User(105,"Swapnil","WB",4)).toList();
		service.saveAll(list);
	}

}
