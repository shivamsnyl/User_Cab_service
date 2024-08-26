package com.company.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.company.dto.Cab;

@FeignClient(name="CAB-SERVICE")
public interface CabServiceFeign {
	
	@GetMapping("/cab")
	List<Cab> getAllCabs();

}
